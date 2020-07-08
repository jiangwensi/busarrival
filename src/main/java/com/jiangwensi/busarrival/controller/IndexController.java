package com.jiangwensi.busarrival.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.BusStop;
import com.jiangwensi.busarrival.service.BusStopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Controller
@Slf4j
public class IndexController {

    private BusStopService busStopService;

    public IndexController(BusStopService busStopService) {
        this.busStopService = busStopService;
    }

    @GetMapping({"","/","/index"})
    public String index(Model model){
        log.info("index()");
        List<BusStop> busStops = new ArrayList<>();
        try {
            busStops = busStopService.listAllBusStops();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //TODO error handling
        }

        model.addAttribute("busStops", busStops);
        return "index";
    }
}
