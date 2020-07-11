package com.jiangwensi.busarrival.domain.entity;

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
public class BusServiceItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @JsonProperty("ServiceNo")
    private String serviceNo;
    @JsonProperty("Operator")
    private String operator;
    @JsonProperty("Direction")
    private String direction;
    @JsonProperty("Category")
    private String category;
    @JsonProperty("OriginCode")
    private String originCode;
    @JsonProperty("DestinationCode")
    private String destinationCode;
    @JsonProperty("AM_Peak_Freq")
    private String amPeakFreq;
    @JsonProperty("AM_Offpeak_Freq")
    private String amOffPeakFreq;
    @JsonProperty("PM_Peak_Freq")
    private String pmPeakFreq;
    @JsonProperty("PM_Offpeak_Freq")
    private String pmOffPeakFreq;
    @JsonProperty("LoopDesc")
    private String loopDesc;

}
