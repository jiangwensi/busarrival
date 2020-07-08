package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.BusStop;
import com.jiangwensi.busarrival.domain.BusStopResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("AccountKey",apiKey);
        headers.set("accept","application/json");
        HttpEntity entity = new HttpEntity("body",headers);
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET,entity,String.class);
        log.info(response.getBody());



        ObjectMapper mapper = new ObjectMapper();
        BusStopResponse busStopResponse = mapper.readValue(response.getBody(),
                BusStopResponse.class);


        log.info("busStopResponse.getOdataMetadata()="+busStopResponse.getOdataMetadata());
        log.info("busStopResponse.getValue()="+busStopResponse.getValue());
        log.info("listAllBusStops end");
        return busStopResponse.getValue();
    }
}
