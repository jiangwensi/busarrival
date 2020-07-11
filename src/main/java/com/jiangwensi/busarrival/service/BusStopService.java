package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.dto.BusStopDto;

import java.util.List;

public interface BusStopService {
    List<BusStopDto> listAllBusStops() throws JsonProcessingException;

    void syncBusStops() throws JsonProcessingException;

    String translateBusStopCodeToName(String code);

    List<BusStopDto> searchBusStop(String busStop);

}
