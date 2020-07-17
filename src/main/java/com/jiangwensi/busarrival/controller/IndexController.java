package com.jiangwensi.busarrival.controller;

import com.jiangwensi.busarrival.service.BusStopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Controller
@Slf4j
public class IndexController {

    @Autowired
    private BusStopService busStopService;

    @GetMapping({"","/","/index"})
    public String index(Model model){
        log.info("index()");
        List<String> allBusStopsAndRoads = busStopService.listAllBusStopAndRoads();
        model.addAttribute("busStopList",allBusStopsAndRoads);
        return "index";
    }
}
