package com.jiangwensi.busarrival.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusArrival {

    @JsonProperty("ServiceNo")
    private String serviceNo;
    @JsonProperty("Operator")
    private String operator;
    @JsonProperty("NextBus")
    private NextBus nextBus;
    @JsonProperty("NextBus2")
    private NextBus nextBus2;
    @JsonProperty("NextBus3")
    private NextBus nextBus3;

}
