package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.dto.BusRouteDto;
import com.jiangwensi.busarrival.domain.entity.BusRoute;

import java.util.List;
import java.util.Map;

public interface BusRouteService {
    List<BusRouteDto> listAllBusRoutes() throws JsonProcessingException;

    List<BusRoute> syncBusRoutes() throws JsonProcessingException;

    List<BusRoute> findByBusStopCode(String busStopCode);

    Map<String, List<BusRouteDto>> findByServiceNo(String serviceNo);

}
