package com.jiangwensi.busarrival.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusRoute {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @JsonProperty("ServiceNo")
    private String serviceNo;
    @JsonProperty("Operator")
    private String operator;
    @JsonProperty("Direction")
    private String direction;
    @JsonProperty("StopSequence")
    private String stopSequence;
    @JsonProperty("BusStopCode")
    private String busStopCode;
    @JsonProperty("Distance")
    private String distance;
    @JsonProperty("WD_FirstBus")
    private String wdFirstBus;
    @JsonProperty("WD_LastBus")
    private String wdLastBus;
    @JsonProperty("SAT_FirstBus")
    private String satFirstBus;
    @JsonProperty("SAT_LastBus")
    private String satLastBus;
    @JsonProperty("SUN_FirstBus")
    private String sunFirstBus;
    @JsonProperty("SUN_LastBus")
    private String sunLastBus;
}
