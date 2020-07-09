package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.BusRoute;

import java.util.List;

public interface BusRouteService {
    List<BusRoute> listAllBusRoutes() throws JsonProcessingException;

    void updateAllBusRoutes() throws JsonProcessingException;
}
