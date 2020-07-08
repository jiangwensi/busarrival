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

    private String serviceNo;
    private String operator;
    private String direction;
    private String category;
    private String orignCode;
    private String destinationCode;
    @JsonProperty("AM_Peak_Freq")
    private String amPeakFreq;
    @JsonProperty("AM_Offpeak_Freq")
    private String amOffPeakFreq;
    @JsonProperty("PM_Peak_Freq")
    private String pmPeakFreq;
    @JsonProperty("PM_Offpeak_Freq")
    private String pmOffPeakFreq;
    private String loopDesc;

}
