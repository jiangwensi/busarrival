package com.jiangwensi.busarrival.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiangwensi.busarrival.entity.NextBus;
import lombok.Data;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Data
public class BusArrivalDto {

    private String serviceNo;
    private String operator;
    private NextBusDto nextBus;
    private NextBusDto nextBus2;
    private NextBusDto nextBus3;
}
