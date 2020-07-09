package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.BusServiceItem;
import com.jiangwensi.busarrival.domain.BusServiceItemResponse;
import com.jiangwensi.busarrival.repository.BusServiceRepository;
import com.jiangwensi.busarrival.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public BusServiceServiceImpl(BusServiceRepository busServiceRepository) {
        this.busServiceRepository = busServiceRepository;
    }

    @Override
    public List<BusServiceItem> listAllBusServices() throws JsonProcessingException {
        log.info("listAllBusServices() start");
        return (List<BusServiceItem>) busServiceRepository.findAll();
    }

    @Override
    public void syncBusServices() throws JsonProcessingException {
        log.info("syncBusServices()");
        int size = 500;
        int i = 0;
        List<BusServiceItem> busServiceItems = new ArrayList<>();
        while (size == 500) {
            ResponseEntity<String> response = new HttpUtils().getResponse(url + "?$skip=" + i * 500, apiKey);
            log.info("counter i: {}",i);
            i++;

            ObjectMapper mapper = new ObjectMapper();
            BusServiceItemResponse busServiceItemResponse = (BusServiceItemResponse) mapper.readValue(response.getBody(),
                    BusServiceItemResponse.class);
            busServiceItems.addAll(busServiceItemResponse.getValue());
            size = busServiceItemResponse.getValue().size();
            log.info("retrieved bus service size: {}",size);
        }

        log.info("retrieved {} bus services from API",busServiceItems.size());
        List<BusServiceItem> distinctBusService = busServiceItems.stream()
                .collect(
                        Collectors.collectingAndThen(
                            Collectors.toMap(
                                c ->  c.getServiceNo()+"+"+c.getDirection(),
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
}
