package com.jiangwensi.busarrival.service;

import com.jiangwensi.busarrival.domain.dto.BusServiceStopArrivalDto;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiang Wensi on 12/7/2020
 */
public interface BusServiceArrivalService {
    Map<String, List<BusServiceStopArrivalDto>> getBusServiceStopArrivalDtoWithoutArrivalDetailsByServiceNo(String serviceNo);

    String getBusArrivalTimeByServiceNoAndBusStopCOde(String busNo, String busStopCode);
}
