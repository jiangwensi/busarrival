package com.jiangwensi.busarrival.controller;

import com.jiangwensi.busarrival.dto.BusStopDto;
import com.jiangwensi.busarrival.service.BusStopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    public String listServicesAtBusStop(@RequestParam String busStop, Model model) {
        log.info("listServicesAtBusStop busStop:{}", busStop);
        return "showBusStop";
    }

    @GetMapping("searchBusStop")
    public String searchBusStop(@RequestParam String busStop, Model model, RedirectAttributes redirectAttributes){
        log.info("searchBusStop busStop:{}",busStop);
        List<BusStopDto> busStopDtos = busStopService.searchBusStop(busStop);
        if (busStopDtos==null || busStopDtos.size()==0) {
            return "redirect:/";
        }

        if (busStopDtos.size() ==1) {
            redirectAttributes.addAttribute("busStopCode",busStopDtos.get(0).getBusStopCode());
            return "redirect:/showBusArrival";
        }
        model.addAttribute("busStops",busStopDtos);
        return "searchBusStop";
    }

}
