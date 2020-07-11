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

    @GetMapping({"","/","/index"})
    public String index(){
        log.info("index()");
        return "index";
    }
}
