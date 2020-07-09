package com.jiangwensi.busarrival.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.service.BusServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Jiang Wensi on 9/7/2020
 */
@Slf4j
@Component
public class BusServiceScheduler {

    private BusServiceService busServiceService;

    public BusServiceScheduler(BusServiceService busServiceService) {
        this.busServiceService = busServiceService;
    }

    @Scheduled(cron = "0 10 10 1 * ?", zone="Asia/Singapore")
    public void syncBusRoutes() throws JsonProcessingException {
        log.info("syncBusRoutes()");
        busServiceService.syncBusServices();
    }
}
