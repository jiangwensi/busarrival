package com.jiangwensi.busarrival.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Data
public class NextBusDto {

    private String originCode;
    private String destinationCode;
    private String estimatedArrival;
    private String latitude;
    private String longitude;
    private String visitNumber;
    private String load;
    private String feature;
    private String type;

    private String originName;
    private String destinationName;
}
