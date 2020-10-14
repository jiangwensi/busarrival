package com.jiangwensi.busarrival.domain.dto;

import lombok.Data;

/**
 * Created by Jiang Wensi on 12/7/2020
 */
@Data
public class BusServiceStopArrivalDto {
    private String busServiceNo;
    private String busStopName;
    private String busStopRoad;
    private String busStopCode;
    private String direction;
    private String nextBusArrival;
    private int stopSequence;

    public BusServiceStopArrivalDto() {
    }

    public BusServiceStopArrivalDto(String busServiceNo, String direction,int stopSequence,
                                    String busStopCode, String busStopDescription,
                                    String busStopRoadName) {
        this.busServiceNo = busServiceNo;
        this.busStopName = busStopDescription;
        this.busStopRoad = busStopRoadName;
        this.busStopCode = busStopCode;
        this.direction = direction;
        this.stopSequence = stopSequence;
    }
}
