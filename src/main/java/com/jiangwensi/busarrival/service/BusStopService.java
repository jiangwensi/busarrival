package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.BusStop;
import com.jiangwensi.busarrival.domain.BusStopResponse;

import java.util.List;

public interface BusStopService {
    List<BusStop> listAllBusStops() throws JsonProcessingException;
}
