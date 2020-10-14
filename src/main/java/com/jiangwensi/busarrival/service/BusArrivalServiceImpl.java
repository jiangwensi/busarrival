package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.dto.BusArrivalDto;
import com.jiangwensi.busarrival.domain.dto.BusServiceStopArrivalDto;
import com.jiangwensi.busarrival.domain.entity.BusArrival;
import com.jiangwensi.busarrival.domain.entity.BusRoute;
import com.jiangwensi.busarrival.domain.mapper.BusArrivalMapper;
import com.jiangwensi.busarrival.domain.mapper.NextBusMapper;
import com.jiangwensi.busarrival.exception.NotFoundException;
import com.jiangwensi.busarrival.response.BusArrivalResponse;
import com.jiangwensi.busarrival.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Service
@Slf4j
public class BusArrivalServiceImpl implements BusArrivalService {

    private BusRouteService busRouteService;

    @Autowired
    private BusArrivalMapper busArrivalMapper;

    @Autowired
    private NextBusMapper nextBusMapper;

    @Value("${api.account.key}")
    private String apiKey;

    @Value("${api.url.busarrival}")
    private String url;

    public BusArrivalServiceImpl(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    @Override
    public List<BusArrivalDto> searchBusArrival(String busStopCode) throws JsonProcessingException {
        log.info("searchBusArrival busStop:{} ",busStopCode);
        List<BusArrivalDto> busArrivalDtos = new ArrayList<>();
        List<BusRoute> busRoutes = busRouteService.findByBusStopCode(busStopCode);

        Set<String> busServiceNos = new HashSet<>();
        busRoutes.forEach(e->busServiceNos.add(e.getServiceNo()));
        for (String busServiceNo : busServiceNos) {
            String requestURL = url+"?BusStopCode="+busStopCode+"&ServiceNo="+busServiceNo;
            log.info("sending request to "+requestURL);
            ResponseEntity<String> response =
                    new HttpUtils().getResponse(requestURL,apiKey);
            log.info("response.getBody()="+response.getBody());
            ObjectMapper mapper = new ObjectMapper();
            BusArrivalResponse busArrivalResponse = (BusArrivalResponse) mapper.readValue(response.getBody(),
                    BusArrivalResponse.class);
            for (BusArrival service : busArrivalResponse.getServices()) {
                BusArrivalDto busArrivalDto = busArrivalMapper.toBusArrivalDto(service);
                busArrivalDto.setNextBus(nextBusMapper.toNextBusDto(service.getNextBus()));
                busArrivalDto.setNextBus2(nextBusMapper.toNextBusDto(service.getNextBus2()));
                busArrivalDto.setNextBus3(nextBusMapper.toNextBusDto(service.getNextBus3()));
                busArrivalDtos.add(busArrivalDto);
            }
        }
        log.info("retrieved {} busarrivals", busArrivalDtos.size());
        Collections.sort(busArrivalDtos, new Comparator<BusArrivalDto>() {
            @Override
            public int compare(BusArrivalDto o1, BusArrivalDto o2) {
                Integer o1ServiceNoInt;
                Integer o2ServiceNoInt;
                String o1ServiceNoChar;
                String o2ServiceNoChar;
                o1ServiceNoInt = Integer.parseInt(o1.getServiceNo().replaceAll("[^0-9]",""));
                o2ServiceNoInt = Integer.parseInt(o2.getServiceNo().replaceAll("[^0-9]",""));
                o1ServiceNoChar = o1.getServiceNo().replaceAll("[0-9]","");
                o2ServiceNoChar = o2.getServiceNo().replaceAll("[0-9]","");
                if (o1ServiceNoInt==o2ServiceNoInt) {
                    return o1ServiceNoChar.compareTo(o2ServiceNoChar);
                }
                return o1ServiceNoInt - o2ServiceNoInt;
            }
        });
        return busArrivalDtos;
    }

    @Override
    public Map<String, List<BusServiceStopArrivalDto>> getBusArrivalsByServiceNo(String serviceNo) {

        return null;
    }
}
