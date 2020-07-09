package com.jiangwensi.busarrival.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.BusRoute;
import com.jiangwensi.busarrival.domain.BusServiceItem;
import com.jiangwensi.busarrival.domain.BusStop;
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

        List<BusStop> busStops = new ArrayList<>();
        List<BusServiceItem> busServiceItems = new ArrayList<>();
        List<BusRoute> busRoutes = new ArrayList<>();
        try {
            busStops = busStopService.listAllBusStops();
            busServiceItems = busServiceService.listAllBusServices();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //TODO error handling
        }

        Collections.sort(busStops, new Comparator<BusStop>() {
            @Override
            public int compare(BusStop o1, BusStop o2) {
               return o1.getDescription().compareTo(o2.getDescription());
            }
        });

        Collections.sort(busServiceItems, new Comparator<BusServiceItem>() {
            @Override
            public int compare(BusServiceItem o1, BusServiceItem o2) {
                return o1.getServiceNo().compareTo(o2.getServiceNo());
            }
        });

        model.addAttribute("busStops", busStops);
        model.addAttribute("busServices", busServiceItems);
        return "index";
    }
}
