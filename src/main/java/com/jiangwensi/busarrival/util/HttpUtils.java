package com.jiangwensi.busarrival.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Slf4j
public class HttpUtils {

    public ResponseEntity<String> getResponse(String url, String apiKey) throws JsonProcessingException {
        log.info("getResponse()");
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("AccountKey",apiKey);
        headers.set("accept","application/json");

        HttpEntity entity = new HttpEntity("body",headers);
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET,entity,String.class);

//        if (response.getBody().length()>100) {
//            log.info(response.getBody().substring(0,100)+"......");
//        } else {
            log.info(response.getBody());
//        }
        return response;
    }
}
