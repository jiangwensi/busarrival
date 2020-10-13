package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.dto.BusServiceItemDto;
import com.jiangwensi.busarrival.domain.entity.BusServiceItem;
import com.jiangwensi.busarrival.domain.mapper.BusServiceItemMapper;
import com.jiangwensi.busarrival.domain.mapper.BusStopMapper;
import com.jiangwensi.busarrival.exception.NotFoundException;
import com.jiangwensi.busarrival.response.BusServiceItemResponse;
import com.jiangwensi.busarrival.repository.BusServiceRepository;
import com.jiangwensi.busarrival.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Service
@Slf4j
public class BusServiceServiceImpl implements BusServiceService {

    @Value("${api.account.key}")
    private String apiKey;

    @Value("${api.url.busservices}")
    private String url;


    private BusServiceRepository busServiceRepository;

    @Autowired
    private BusServiceItemMapper busServiceItemMapper;

    @Autowired
    private BusStopMapper busStopMapper;

    public BusServiceServiceImpl(BusServiceRepository busServiceRepository) {
        this.busServiceRepository = busServiceRepository;
    }

    @Override
    public List<BusServiceItemDto> listAllBusServices() throws JsonProcessingException {
        log.info("listAllBusServices() start");
        List<BusServiceItem> busServiceItems = (List<BusServiceItem>) busServiceRepository.findAll();
        List<BusServiceItemDto> dtos = new ArrayList<>();
        Set<String> uniqueBusServiceNo = new HashSet<>();
        Stream<BusServiceItem> uniqueBusServices = busServiceItems.stream().filter(e -> uniqueBusServiceNo.add(e.getServiceNo()));
        uniqueBusServices.forEach(e -> dtos.add(busServiceItemMapper.toBusServiceItemDto(e)));
        return dtos;
    }

    @Override
    public void syncBusServices() throws JsonProcessingException {
        log.info("syncBusServices()");
        int size = 500;
        int i = 0;
        List<BusServiceItem> busServiceItems = new ArrayList<>();
        while (size == 500) {
            ResponseEntity<String> response = new HttpUtils().getResponse(url + "?$skip=" + i * 500, apiKey);
            log.info("counter i: {}", i);
            i++;

            ObjectMapper mapper = new ObjectMapper();
            BusServiceItemResponse busServiceItemResponse = (BusServiceItemResponse) mapper.readValue(response.getBody(),
                    BusServiceItemResponse.class);
            busServiceItems.addAll(busServiceItemResponse.getValue());
            size = busServiceItemResponse.getValue().size();
            log.info("retrieved bus service size: {}", size);
        }

        log.info("retrieved {} bus services from API", busServiceItems.size());
        List<BusServiceItem> distinctBusService = busServiceItems.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toMap(
                                        c -> c.getServiceNo() + "+" + c.getDirection(),
                                        Function.identity(),
                                        (a, b) -> a,
                                        () -> new LinkedHashMap<String, BusServiceItem>()
                                ),
                                m -> new ArrayList<>(m.values())));

        log.info("after reducing duplicate services, going to update {} bus services in database",
                distinctBusService.size());
        busServiceRepository.deleteAll();
        busServiceRepository.saveAll(distinctBusService);
    }

    @Override
    public List<BusServiceItemDto> searchByServiceNo(String serviceNo){
        List<BusServiceItem> busServices = (List<BusServiceItem>) busServiceRepository.findByServiceNo(serviceNo);
        List<BusServiceItemDto> busServiceItemDtos = new ArrayList<>();
        if (busServices == null || busServices.size() == 0) {
           return busServiceItemDtos;
        }
        busServices.forEach(e -> busServiceItemDtos.add(busServiceItemMapper.toBusServiceItemDto(e)));
        return busServiceItemDtos;
    }




}
