package com.jiangwensi.busarrival.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.dto.BusArrivalDto;
import com.jiangwensi.busarrival.service.BusArrivalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Controller
@Slf4j
public class BusArrivalController {

    private BusArrivalService busArrivalService;

    public BusArrivalController(BusArrivalService busArrivalService) {
        this.busArrivalService = busArrivalService;
    }

    @GetMapping("/showBusArrival")
    public String getBusArrival(@RequestParam String busStopCode, Model model) throws JsonProcessingException {
        log.info("getBusArrival busStopCode:{}",busStopCode);
        List<BusArrivalDto> busArrivalDtos = busArrivalService.searchBusArrival(busStopCode);
        model.addAttribute("busArrivals",busArrivalDtos);
        return "showBusArrival";
    }
}
