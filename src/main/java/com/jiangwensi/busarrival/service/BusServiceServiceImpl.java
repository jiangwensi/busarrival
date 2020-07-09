package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.BusRoute;
import com.jiangwensi.busarrival.domain.BusRouteResponse;
import com.jiangwensi.busarrival.domain.BusServiceItem;
import com.jiangwensi.busarrival.domain.BusServiceItemResponse;
import com.jiangwensi.busarrival.repository.BusServiceRepository;
import com.jiangwensi.busarrival.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        ResponseEntity<String> response = new HttpUtils().getResponse(url,apiKey);
        ObjectMapper mapper = new ObjectMapper();
        BusServiceItemResponse busServiceItemResponse = (BusServiceItemResponse) mapper.readValue(response.getBody(),
                BusServiceItemResponse.class);
        List<BusServiceItem> busServices = busServiceItemResponse.getValue();
        log.info("number of bus services:{}",busServices.size());
        return busServices;
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

        log.info("going to update {} bus services in database",busServiceItems.size());
        busServiceRepository.saveAll(busServiceItems);
    }
}
