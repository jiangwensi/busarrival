package com.jiangwensi.busarrival.controller;

import com.jiangwensi.busarrival.service.BusRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Controller
@Slf4j
public class RouteController {
    private BusRouteService busRouteService;

    public RouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    @GetMapping("/route/{serviceNo}")
    public String showRoute(@PathVariable String serviceNo, Model model){
        log.info("showRoute() serviceNo="+serviceNo);


        return "showRoute";
    }
}
