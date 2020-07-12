package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.dto.BusRouteDto;
import com.jiangwensi.busarrival.domain.entity.BusRoute;

import java.util.List;

public interface BusRouteService {
    List<BusRouteDto> listAllBusRoutes() throws JsonProcessingException;

    List<BusRoute> syncBusRoutes() throws JsonProcessingException;

    List<BusRoute> findByBusStopCode(String busStopCode);

    List<BusRouteDto> findByServiceNo(String serviceNo);

}
