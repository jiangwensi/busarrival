package com.jiangwensi.busarrival.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.dto.BusServiceItemDto;
import com.jiangwensi.busarrival.dto.BusStopDto;
import com.jiangwensi.busarrival.entity.BusRoute;
import com.jiangwensi.busarrival.entity.BusServiceItem;
import com.jiangwensi.busarrival.entity.BusStop;
import com.jiangwensi.busarrival.service.BusRouteService;
import com.jiangwensi.busarrival.service.BusServiceService;
import com.jiangwensi.busarrival.service.BusStopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Controller
@Slf4j
public class IndexController {

    private BusStopService busStopService;
    private BusServiceService busServiceService;
    private BusRouteService busRouteService;

    public IndexController(BusStopService busStopService, BusServiceService busServiceService, BusRouteService busRouteService) {
        this.busStopService = busStopService;
        this.busServiceService = busServiceService;
        this.busRouteService = busRouteService;
    }

    @GetMapping({"","/","/index"})
    public String index(Model model){
        log.info("index()");

//        //testing
//        try {
//            busStopService.syncBusStops();
//            busRouteService.syncBusRoutes();
//            busServiceService.syncBusServices();
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        //testing

        List<BusStopDto> busStopDtos = new ArrayList<>();
        List<BusServiceItemDto> busServiceDtos = new ArrayList<>();
        List<BusRoute> busRoutes = new ArrayList<>();
        try {
            busStopDtos= busStopService.listAllBusStops();
            busServiceDtos = busServiceService.listAllBusServices();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //TODO error handling
        }

        Collections.sort(busStopDtos, new Comparator<BusStopDto>() {
            @Override
            public int compare(BusStopDto o1, BusStopDto o2) {
               return o1.getDescription().compareTo(o2.getDescription());
            }
        });

        Collections.sort(busServiceDtos, new Comparator<BusServiceItemDto>() {
            @Override
            public int compare(BusServiceItemDto o1, BusServiceItemDto o2) {
                return o1.getServiceNo().compareTo(o2.getServiceNo());
            }
        });

        model.addAttribute("busStops", busStopDtos);
        model.addAttribute("busServices", busServiceDtos);
        return "index";
    }
}
