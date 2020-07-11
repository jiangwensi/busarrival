package com.jiangwensi.busarrival.controller;

import com.jiangwensi.busarrival.service.BusStopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Controller
@Slf4j
public class BusStopController {

    private final BusStopService busStopService;

    public BusStopController(BusStopService busStopService) {
        this.busStopService = busStopService;
    }

    private String listServicesAtBusStop(@RequestParam String busStop, Model model) {
        log.info("listServicesAtBusStop busStop:{}", busStop);


        return "showBusStop";
    }
}
