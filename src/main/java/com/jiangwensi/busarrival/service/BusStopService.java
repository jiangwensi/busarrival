package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.dto.BusStopDto;

import java.util.List;

public interface BusStopService {
    List<BusStopDto> listAllBusStops() throws JsonProcessingException;

    void syncBusStops() throws JsonProcessingException;

    String translateBusStopCodeToName(String code);

    List<BusStopDto> searchBusStopByDescriptionRoadNameContaining(String busStop);

    BusStopDto searchBusStopByDescription(String description);

    String translateBusStopCodeToRoad(String code);

    List<String> listAllBusStopAndRoads();
}
