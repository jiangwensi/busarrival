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
public class BusServiceItemDto {

    private Long id;
    private String serviceNo;
    private String operator;
    private String direction;
    private String category;
    private String orignCode;
    private String destinationCode;
    private String amPeakFreq;
    private String amOffPeakFreq;
    private String pmPeakFreq;
    private String pmOffPeakFreq;
    private String loopDesc;
}
