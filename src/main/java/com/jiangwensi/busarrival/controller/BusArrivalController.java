package com.jiangwensi.busarrival.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.dto.BusArrivalDto;
import com.jiangwensi.busarrival.service.BusArrivalService;
import com.jiangwensi.busarrival.service.BusStopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Controller
@Slf4j
public class BusArrivalController {

    private BusArrivalService busArrivalService;
    private BusStopService busStopService;

    public BusArrivalController(BusArrivalService busArrivalService, BusStopService busStopService) {
        this.busArrivalService = busArrivalService;
        this.busStopService = busStopService;
    }


    @GetMapping("/showBusArrival")
    public String getBusArrival(@RequestParam String busStopCode, Model model) throws JsonProcessingException {
        log.info("getBusArrival busStopCode:{}",busStopCode);
        Map<String,List<BusArrivalDto>> busArrivalDtos = busArrivalService.searchBusArrivalForBusStop(busStopCode);

        assert busArrivalDtos.size()==1;

        Map.Entry<String,List<BusArrivalDto>> entry = busArrivalDtos.entrySet().iterator().next();
        model.addAttribute("busStop", entry.getKey());
        model.addAttribute("busArrivals",entry.getValue());
        return "showBusArrival";
    }
}
