package com.jiangwensi.busarrival.domain.dto;

import lombok.Data;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Data
public class BusArrivalDto {

    private String serviceNo;
    private String busStopName;
    private String operator;
    private NextBusDto nextBus;
    private NextBusDto nextBus2;
    private NextBusDto nextBus3;

    public BusArrivalDto() {
    }

    public BusArrivalDto(String serviceNo, String busStopName, String operator) {
        this.serviceNo = serviceNo;
        this.busStopName = busStopName;
        this.operator = operator;
    }
}
