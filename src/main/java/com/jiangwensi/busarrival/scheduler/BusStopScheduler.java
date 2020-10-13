package com.jiangwensi.busarrival.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.service.BusStopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Jiang Wensi on 9/7/2020
 */
@Slf4j
@Component
public class BusStopScheduler {

    private BusStopService busStopService;

    public BusStopScheduler(BusStopService busStopService) {
        this.busStopService = busStopService;
    }

    @Scheduled(cron = "${cronExpression.busStop}", zone="Asia/Singapore")
    public void syncBusStops() throws JsonProcessingException {
        log.info("syncBusStops()");
        busStopService.syncBusStops();
    }
}
