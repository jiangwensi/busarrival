package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.dto.BusArrivalDto;
import com.jiangwensi.busarrival.dto.NextBusDto;
import com.jiangwensi.busarrival.entity.BusArrival;
import com.jiangwensi.busarrival.entity.BusRoute;
import com.jiangwensi.busarrival.mapper.BusArrivalMapper;
import com.jiangwensi.busarrival.mapper.BusRouteMapper;
import com.jiangwensi.busarrival.mapper.NextBusMapper;
import com.jiangwensi.busarrival.response.BusArrivalResponse;
import com.jiangwensi.busarrival.response.BusRouteResponse;
import com.jiangwensi.busarrival.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        return busArrivalDtos;
    }
}
