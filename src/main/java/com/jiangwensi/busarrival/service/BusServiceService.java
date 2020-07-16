package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.dto.BusServiceItemDto;
import com.jiangwensi.busarrival.exception.NotFoundException;

import java.util.List;

public interface BusServiceService {
    List<BusServiceItemDto> listAllBusServices() throws JsonProcessingException;

    void syncBusServices() throws JsonProcessingException;

    List<BusServiceItemDto> searchByServiceNo(String serviceNo) throws NotFoundException;
}
