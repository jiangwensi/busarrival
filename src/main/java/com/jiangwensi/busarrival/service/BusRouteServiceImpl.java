package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.BusRoute;
import com.jiangwensi.busarrival.domain.BusRouteResponse;
import com.jiangwensi.busarrival.repository.BusRouteRepository;
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
public class BusRouteServiceImpl implements BusRouteService {

    @Value("${api.account.key}")
    private String apiKey;

    @Value("${api.url.busroutes}")
    private String url;

    private BusRouteRepository busRouteRepository;

    public BusRouteServiceImpl(BusRouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
    }

    @Override
    public List<BusRoute> listAllBusRoutes() throws JsonProcessingException {
        log.info("listAllBusRoutes() start");
        ResponseEntity<String> response = new HttpUtils().getResponse(url, apiKey);
        log.info(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        BusRouteResponse busRouteResponse = (BusRouteResponse) mapper.readValue(response.getBody(),
                BusRouteResponse.class);
        List<BusRoute> busRoutes = busRouteResponse.getValue();
        log.info("bus routes size: {}",busRoutes.size());
        return busRouteResponse.getValue();
    }

    @Override
    public List<BusRoute> syncBusRoutes() throws JsonProcessingException {
        log.info("syncBusRoutes()");
        int size = 500;
        int i = 0;
        List<BusRoute> busRoutes = new ArrayList<>();
        while (size == 500) {
            ResponseEntity<String> response = new HttpUtils().getResponse(url + "?$skip=" + i * 500, apiKey);
            log.info("counter i: {}",i);
            i++;

            ObjectMapper mapper = new ObjectMapper();
            BusRouteResponse busRouteResponse = (BusRouteResponse) mapper.readValue(response.getBody(), BusRouteResponse.class);
            busRoutes.addAll(busRouteResponse.getValue());
            size = busRouteResponse.getValue().size();
            log.info("retrieved bus route size: {}",size);
        }

        log.info("going to update {} bus routes in database",busRoutes.size());
        busRouteRepository.saveAll(busRoutes);
        return busRoutes;
    }
}
