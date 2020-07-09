package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.BusStop;
import com.jiangwensi.busarrival.domain.BusStopResponse;
import com.jiangwensi.busarrival.repository.BusStopRepository;
import com.jiangwensi.busarrival.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Service
@Slf4j
public class BusStopServiceImpl implements BusStopService {

    @Value("${api.account.key}")
    private String apiKey;

    @Value("${api.url.busstops}")
    private String url;

    private BusStopRepository busStopRepository;

    public BusStopServiceImpl(BusStopRepository busStopRepository) {
        this.busStopRepository = busStopRepository;
    }

    @Override
    public List<BusStop> listAllBusStops() throws JsonProcessingException {
        log.info("listAllBusStops start");
        return (List<BusStop>) busStopRepository.findAll();
    }

    @Override
    public void syncBusStops() throws JsonProcessingException {
        log.info("syncBusStops()");
        int size = 500;
        int i = 0;
        List<BusStop> busStops = new ArrayList<>();
        while (size == 500) {
            ResponseEntity<String> response = new HttpUtils().getResponse(url + "?$skip=" + i * 500, apiKey);
            log.info("counter i: {}",i);
            i++;

            ObjectMapper mapper = new ObjectMapper();
            BusStopResponse busStopResponse = (BusStopResponse) mapper.readValue(response.getBody(),
                    BusStopResponse.class);
            busStops.addAll(busStopResponse.getValue());
            size = busStopResponse.getValue().size();
            log.info("retrieved bus stop size: {}",size);
        }

        log.info("going to update {} bus stop in database",busStops.size());
        busStopRepository.deleteAll();
        busStopRepository.saveAll(busStops);
    }
}
