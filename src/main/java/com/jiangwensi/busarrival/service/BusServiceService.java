package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.dto.BusServiceItemDto;

import java.util.List;

public interface BusServiceService {

    void syncBusServices() throws JsonProcessingException;

    List<BusServiceItemDto> searchByServiceNo(String serviceNo);
}
