package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.dto.BusArrivalDto;
import com.jiangwensi.busarrival.domain.dto.BusServiceStopArrivalDto;
import com.jiangwensi.busarrival.domain.entity.BusArrival;
import com.jiangwensi.busarrival.domain.mapper.NextBusMapper;
import com.jiangwensi.busarrival.repository.BusServiceStopArrivalRepository;
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

    private BusServiceStopArrivalRepository busServiceStopArrivalRepository;

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

    public BusArrivalServiceImpl(BusServiceStopArrivalRepository busServiceStopArrivalRepository) {
        this.busServiceStopArrivalRepository = busServiceStopArrivalRepository;
    }

    @Override
    public Map<String, List<BusArrivalDto>> searchBusArrivalForBusStop(String busStopCode) throws JsonProcessingException {
        List<BusArrivalDto> busArrivalDtos = busServiceStopArrivalRepository.searchBusArrival(busStopCode);

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
                busArrivalDto.setNextBus(nextBusMapper.toNextBusDto(service.getNextBus()));
                busArrivalDto.setNextBus2(nextBusMapper.toNextBusDto(service.getNextBus2()));
                busArrivalDto.setNextBus3(nextBusMapper.toNextBusDto(service.getNextBus3()));
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
        return min + " min";
    }
}
