package com.jiangwensi.busarrival.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Jiang Wensi on 9/7/2020
 */
@Data
public class BusRouteDto {
    private Long id;
    private String serviceNo;
    private String operator;
    private String direction;
    private String stopSequence;
    private String busStopCode;
    private String distance;
    private String wdFirstBus;
    private String wdLastBus;
    private String satFirstBus;
    private String satLastBus;
    private String sunFirstBus;
    private String sunLastBus;
}
