package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.dto.BusArrivalDto;
import com.jiangwensi.busarrival.domain.dto.BusServiceStopArrivalDto;
import com.jiangwensi.busarrival.domain.entity.BusArrival;
import com.jiangwensi.busarrival.domain.mapper.BusArrivalMapper;
import com.jiangwensi.busarrival.domain.mapper.NextBusMapper;
import com.jiangwensi.busarrival.repository.BusServiceStopArrivalRepository;
import com.jiangwensi.busarrival.repository.BusStopRepository;
import com.jiangwensi.busarrival.response.BusArrivalResponse;
import com.jiangwensi.busarrival.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Service
@Slf4j
public class BusArrivalServiceImpl implements BusArrivalService {

    private BusRouteService busRouteService;
    private BusStopService busStopService;
    private BusServiceStopArrivalRepository busServiceStopArrivalRepository;
    private BusStopRepository busStopRepository;

    @Autowired
    private BusArrivalMapper busArrivalMapper;

    @Autowired
    private NextBusMapper nextBusMapper;

    @Value("${api.account.key}")
    private String apiKey;

    @Value("${api.url.busarrival}")
    private String url;


    @Value("${api.url.busservices}")
    private String busServiceUrl;

    @Value("${api.url.busarrival}")
    private String busArrivalUrl;

    public BusArrivalServiceImpl(BusRouteService busRouteService, BusStopService busStopService, BusServiceStopArrivalRepository busServiceStopArrivalRepository, BusStopRepository busStopRepository) {
        this.busRouteService = busRouteService;
        this.busStopService = busStopService;
        this.busServiceStopArrivalRepository = busServiceStopArrivalRepository;
        this.busStopRepository = busStopRepository;
    }


    @Override
    public Map<String, List<BusArrivalDto>> searchBusArrivalForBusStop(String busStopCode) throws JsonProcessingException {
//        List<String> busServiceNos = busStopRepository.findServiceByBusStopCode(busStopCode);
        List<BusArrivalDto> busArrivalDtos = busServiceStopArrivalRepository.searchBusArrival(busStopCode);
//        List<BusArrivalDto> busArrivalDtos = new LinkedList<>();

        for (BusArrivalDto busArrivalDto : busArrivalDtos) {
            String requestURL = url+"?BusStopCode="+busStopCode+"&ServiceNo="+busArrivalDto.getServiceNo();
            log.info("sending request to "+requestURL);
            ResponseEntity<String> response =
                    new HttpUtils().getResponse(requestURL,apiKey);
            log.info("response.getBody()="+response.getBody());
            ObjectMapper mapper = new ObjectMapper();
            BusArrivalResponse busArrivalResponse = (BusArrivalResponse) mapper.readValue(response.getBody(),
                    BusArrivalResponse.class);
            for (BusArrival service : busArrivalResponse.getServices()) {
//                BusArrivalDto busArrivalDto = busArrivalMapper.toBusArrivalDto(service);

                busArrivalDto.setNextBus(nextBusMapper.toNextBusDto(service.getNextBus()));
                busArrivalDto.setNextBus2(nextBusMapper.toNextBusDto(service.getNextBus2()));
                busArrivalDto.setNextBus3(nextBusMapper.toNextBusDto(service.getNextBus3()));
//                busArrivalDtos.add(busArrivalDto);
            }
        }
        log.info("retrieved {} busarrivals", busArrivalDtos.size());
        Map<String, List<BusArrivalDto>> collect =
                busArrivalDtos.stream().collect(Collectors.groupingBy(e -> e.getBusStopName(), toList()));

        return collect;
    }

    @Override
    public Map<String, List<BusServiceStopArrivalDto>> getBusServiceStopArrivalByServiceNo(String serviceNo){
        List<BusServiceStopArrivalDto> busServiceStopArrivalDtos =
                busServiceStopArrivalRepository.findBusServiceStopArrivalDto(serviceNo);

        Map<String, List<BusServiceStopArrivalDto>> collect = busServiceStopArrivalDtos.stream().collect(Collectors.groupingBy(e -> e.getDirection(), toList()));

        return collect;
    }

    @Override
    public String getBusArrivalTimeByServiceNoAndBusStopCOde(String busNo, String busStopCode) {
        String requestURL = busArrivalUrl + "?BusStopCode=" + busStopCode + "&ServiceNo=" + busNo;
        log.info("sending request to " + requestURL);
        ResponseEntity<String> response = null;
        BusArrivalResponse busArrivalResponse = null;
        try {
            response = new HttpUtils().getResponse(requestURL, apiKey);
            ObjectMapper mapper = new ObjectMapper();
            busArrivalResponse = (BusArrivalResponse) mapper.readValue(response.getBody(), BusArrivalResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (busArrivalResponse.getServices()==null || busArrivalResponse.getServices().size()==0) {
            return null;
        }
        BusArrival busArrival = busArrivalResponse.getServices().get(0);
        return translateETA(busArrival.getNextBus().getEstimatedArrival());
    }

    private BusServiceStopArrivalDto prefillArrivalByBusServiceAndStop(String direction, String serviceNo,
                                                                       String busStopCode) {

        BusServiceStopArrivalDto result = new BusServiceStopArrivalDto();
        result.setDirection(direction);
        result.setBusServiceNo(serviceNo);
        result.setBusStopName(busStopService.translateBusStopCodeToName(busStopCode));
        result.setBusStopRoad(busStopService.translateBusStopCodeToRoad(busStopCode));
        result.setBusStopCode(busStopCode);
        return result;
    }

    private BusServiceStopArrivalDto searchArrivalByBusServiceAndStop(String direction, String serviceNo,
                                                                      String busStopCode) {
        List<BusArrivalDto> busArrivalDtos = new ArrayList<>();
        String requestURL = busArrivalUrl + "?BusStopCode=" + busStopCode + "&ServiceNo=" + serviceNo;
        log.info("sending request to " + requestURL);
        ResponseEntity<String> response = null;
        BusArrivalResponse busArrivalResponse = null;
        try {
            response = new HttpUtils().getResponse(requestURL, apiKey);
            ObjectMapper mapper = new ObjectMapper();
            busArrivalResponse = (BusArrivalResponse) mapper.readValue(response.getBody(), BusArrivalResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (busArrivalResponse.getServices()==null || busArrivalResponse.getServices().size()==0) {
            return null;
        }
        BusArrival busArrival = busArrivalResponse.getServices().get(0);
        BusServiceStopArrivalDto result = new BusServiceStopArrivalDto();
        result.setDirection(direction);
        result.setBusServiceNo(serviceNo);
        result.setBusStopName(busStopService.translateBusStopCodeToName(busStopCode));
        result.setBusStopRoad(busStopService.translateBusStopCodeToRoad(busStopCode));
        result.setNextBusArrival(busArrival.getNextBus() == null ? null :
                translateETA(busArrival.getNextBus().getEstimatedArrival()));
        return result;
    }

    private String translateETA(String eta){
        if (eta=="") {
            return "-";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date etaDate = null;
        try {
            etaDate = sdf.parse(eta);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long seconds = (etaDate.getTime() - new Date().getTime()) / 1000;
        if (seconds<0) {
            return "-";
        }
        String min = String.valueOf(seconds / 60);
//        String sec = String.valueOf(seconds % 60);
        return min + " min";
    }
}
