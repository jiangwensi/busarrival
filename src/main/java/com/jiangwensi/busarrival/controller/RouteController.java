package com.jiangwensi.busarrival.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.dto.BusRouteDto;
import com.jiangwensi.busarrival.entity.BusRoute;
import com.jiangwensi.busarrival.service.BusRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
