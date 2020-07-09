package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.BusStop;
import com.jiangwensi.busarrival.domain.BusStopResponse;
import com.jiangwensi.busarrival.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<BusStop> listAllBusStops() throws JsonProcessingException {
        log.info("listAllBusStops start");
        ResponseEntity<String> response = new HttpUtils().getResponse(url,apiKey);
        log.info(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        BusStopResponse busStopResponse = (BusStopResponse) mapper.readValue(response.getBody(),
                BusStopResponse.class);
        return busStopResponse.getValue();
    }
}
