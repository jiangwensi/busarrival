package com.jiangwensi.busarrival.controller;

import com.jiangwensi.busarrival.domain.dto.BusStopDto;
import com.jiangwensi.busarrival.service.BusStopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

    @GetMapping("searchBusStopByDescriptionContaining")
    public String searchBusStopByDescriptionContaining(@RequestParam String searchKey, Model model,RedirectAttributes redirectAttributes) {
        log.info("searchBusStopByDescriptionContaining busStop:{}", searchKey);
        List<BusStopDto> busStopDtos = busStopService.searchBusStopByDescriptionRoadNameContaining(searchKey);
        if (busStopDtos == null || busStopDtos.size() == 0) {
            model.addAttribute("busStopError","Unable to find bus stop "+searchKey);
            return "index";
        }

        if (busStopDtos.size() == 1) {
            redirectAttributes.addAttribute("busStopCode", busStopDtos.get(0).getBusStopCode());
            return "redirect:/showBusArrival";
        }
        model.addAttribute("busStops", busStopDtos);
        model.addAttribute("searchKey", searchKey);
        return "searchBusStop";
    }


    @GetMapping("nearbyBusStops")
    public String showNearByBusStops( Model model, @RequestParam(required = false) String latitude,
                                      @RequestParam(required = false) String longitude,
                                      RedirectAttributes redirectAttributes) {
//        modelMap = new ModelMap();
        List<BusStopDto> busStopDtos = busStopService.nearBy(latitude,longitude);
        if (busStopDtos == null || busStopDtos.size() == 0) {
            model.addAttribute("busStopError","Unable to find nearby bus stops");
            return "index";
        }

        if (busStopDtos.size() == 1) {
            redirectAttributes.addAttribute("busStopCode", busStopDtos.get(0).getBusStopCode());
            return "redirect:/showBusArrival";
        }
        model.addAttribute("busStops",busStopDtos);
        String latlong = "("+
                latitude.substring(0,latitude.indexOf('.')+5)+","+
                longitude.substring(0,longitude.indexOf('.')+5)+")";
        model.addAttribute("searchKey", latlong);
        return "searchBusStop";
    }

    @GetMapping("searchBusStopByDescription")
    public String searchBusStopByDescription(@RequestParam String description, RedirectAttributes redirectAttributes) {
        log.info("searchBusStop searchBusStopByDescription:{}", description);
        BusStopDto busStopDto = busStopService.searchBusStopByDescription(description);
        redirectAttributes.addAttribute("busStopCode", busStopDto.getBusStopCode());
        return "redirect:/showBusArrival";
    }


}
