package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.BusStop;

import java.util.List;

public interface BusStopService {
    List<BusStop> listAllBusStops() throws JsonProcessingException;

    void syncBusStops() throws JsonProcessingException;
}
