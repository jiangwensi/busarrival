package com.jiangwensi.busarrival.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusServiceItem {

    @JsonProperty("ServiceNo")
    private String serviceNo;
    @JsonProperty("Operator")
    private String operator;
    @JsonProperty("Direction")
    private String direction;
    @JsonProperty("Category")
    private String category;
    @JsonProperty("OrignCode")
    private String orignCode;
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
