package com.jiangwensi.busarrival.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.dto.BusServiceItemDto;
import com.jiangwensi.busarrival.domain.dto.BusServiceStopArrivalDto;
import com.jiangwensi.busarrival.exception.NotFoundException;
import com.jiangwensi.busarrival.service.BusServiceArrivalService;
import com.jiangwensi.busarrival.service.BusServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    public String searchBusService(@RequestParam String busNo, Model model) {
        log.info("searchBusService serviceNo: {}",busNo);
        List<BusServiceItemDto> busServiceItemsDtos = busServiceService.searchByServiceNo(busNo);

        if (busServiceItemsDtos==null || busServiceItemsDtos.size()==0) {
            model.addAttribute("busNoError","Unable to find bus "+busNo);
            return "index";
        }

        busServiceItemsDtos.forEach(
                e->log.info("service no: {}, origin code: {}, destination code: {}",
                e.getServiceNo(),
                e.getOriginCode(),
                e.getDestinationCode()));

        Map<String, List<BusServiceStopArrivalDto>> busRouteDirections = busServiceArrivalService.getBusServiceStopArrivalDtoByServiceNo(busNo);

        model.addAttribute("busServices",busServiceItemsDtos);
        model.addAttribute("serviceNo",busNo);
        model.addAttribute("busRouteDirections",busRouteDirections);

        return "showBusService";
    }

    @ExceptionHandler({NotFoundException.class, JsonProcessingException.class})
    public ModelAndView handleBusNotFoundException(HttpServletRequest request, Exception ex){
        log.error("Requested URL="+request.getRequestURL());
        log.error("Exception Raised="+ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", ex.getMessage());

        modelAndView.setViewName("apperror");
        return modelAndView;
    }
}
