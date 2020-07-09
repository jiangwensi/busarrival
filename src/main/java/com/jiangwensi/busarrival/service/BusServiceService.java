package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangwensi.busarrival.domain.BusServiceItem;

import java.util.List;

public interface BusServiceService {
    List<BusServiceItem> listAllBusServices() throws JsonProcessingException;

    void syncBusServices() throws JsonProcessingException;
}
