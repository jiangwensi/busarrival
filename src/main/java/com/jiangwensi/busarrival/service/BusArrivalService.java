package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.dto.BusArrivalDto;
import com.jiangwensi.busarrival.exception.NotFoundException;

import java.util.List;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
public interface BusArrivalService {

    List<BusArrivalDto> searchBusArrival(String busStop) throws JsonProcessingException;
}
