package com.jiangwensi.busarrival.domain.dto;

import lombok.Data;

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
    private String originCode;
    private String destinationCode;
    private String amPeakFreq;
    private String amOffPeakFreq;
    private String pmPeakFreq;
    private String pmOffPeakFreq;
    private String loopDesc;

    private String originName;
    private String destinationName;
}
