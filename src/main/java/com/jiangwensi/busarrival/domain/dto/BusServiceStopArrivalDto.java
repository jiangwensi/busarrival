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
    private String direction;
    private String nextBusArrival;
}
