package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.BusServiceItem;
import com.jiangwensi.busarrival.domain.BusServiceItemResponse;
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
public class BusServiceServiceImpl implements BusServiceService {

    @Value("${api.account.key}")
    private String apiKey;

    @Value("${api.url.busservices}")
    private String url;

    @Override
    public List<BusServiceItem> listAllBusServices() throws JsonProcessingException {
        log.info("listAllBusServices()");
        ResponseEntity<String> response = new HttpUtils().getResponse(url,apiKey);
        log.info(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        BusServiceItemResponse busServiceItemResponse = (BusServiceItemResponse) mapper.readValue(response.getBody(),
                BusServiceItemResponse.class);
        return busServiceItemResponse.getValue();
    }
}
