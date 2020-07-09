package com.jiangwensi.busarrival.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.service.BusRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Slf4j
@Component
public class BusRouteScheduler {

    private BusRouteService busRouteService;

    public BusRouteScheduler(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    @Scheduled(cron = "0 0 10 * * *", zone="Asia/Singapore")
    public void syncBusRoutes() throws JsonProcessingException {
        log.info("syncBusRoutes()");
        busRouteService.syncBusRoutes();
    }
}
