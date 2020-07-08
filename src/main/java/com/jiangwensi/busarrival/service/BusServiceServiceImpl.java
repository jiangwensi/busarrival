package com.jiangwensi.busarrival.service;

import com.jiangwensi.busarrival.domain.BusServiceItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    public List<BusServiceItem> listAllBusServices() {
        log.info("listAllBusServices()");
        return null;
    }
}
