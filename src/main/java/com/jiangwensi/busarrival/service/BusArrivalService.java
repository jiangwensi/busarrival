package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.dto.BusArrivalDto;
import com.jiangwensi.busarrival.domain.dto.BusServiceStopArrivalDto;
import com.jiangwensi.busarrival.exception.NotFoundException;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
public interface BusArrivalService {

    Map<String, List<BusArrivalDto>> searchBusArrivalForBusStop(String busStopCode) throws JsonProcessingException;
    Map<String, List<BusServiceStopArrivalDto>> getBusServiceStopArrivalByServiceNo(String serviceNo);

    String getBusArrivalTimeByServiceNoAndBusStopCOde(String busNo, String busStopCode);
}
