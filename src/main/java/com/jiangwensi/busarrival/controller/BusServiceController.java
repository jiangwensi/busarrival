package com.jiangwensi.busarrival.controller;

import com.jiangwensi.busarrival.domain.dto.BusRouteDto;
import com.jiangwensi.busarrival.domain.dto.BusServiceItemDto;
import com.jiangwensi.busarrival.domain.dto.BusServiceStopArrivalDto;
import com.jiangwensi.busarrival.domain.entity.BusRoute;
import com.jiangwensi.busarrival.service.BusRouteService;
import com.jiangwensi.busarrival.service.BusServiceArrivalService;
import com.jiangwensi.busarrival.service.BusServiceService;
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
public class BusServiceController {

    private BusServiceService busServiceService;

    private BusServiceArrivalService busServiceArrivalService;

    public BusServiceController(BusServiceService busServiceService, BusServiceArrivalService busServiceArrivalService) {
        this.busServiceService = busServiceService;
        this.busServiceArrivalService = busServiceArrivalService;
    }

    @GetMapping("/searchBusService")
    public String searchBusService(@RequestParam String busNo, Model model){
        log.info("searchBusService serviceNo: {}",busNo);
        List<BusServiceItemDto> busServiceItemsDtos = busServiceService.searchByServiceNo(busNo);
        busServiceItemsDtos.forEach(
                e->log.info("service no: {}, origin code: {}, destination code: {}",
                e.getServiceNo(),
                e.getOriginCode(),
                e.getDestinationCode()));

        Map<String, List<BusServiceStopArrivalDto>> busRouteDirections =
                busServiceArrivalService.getBusServiceStopArrivalDtoByServiceNo(busNo,-1);

        model.addAttribute("busServices",busServiceItemsDtos);
        model.addAttribute("serviceNo",busNo);
        model.addAttribute("busRouteDirections",busRouteDirections);

        return "showBusService";
    }
}
