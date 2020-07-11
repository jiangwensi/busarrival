package com.jiangwensi.busarrival.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NextBus {

    @JsonProperty("OriginCode")
    private String originCode;
    @JsonProperty("DestinationCode")
    private String destinationCode;
    @JsonProperty("EstimatedArrival")
    private String estimatedArrival;
    @JsonProperty("Latitude")
    private String latitude;
    @JsonProperty("Longitude")
    private String longitude;
    @JsonProperty("VisitNumber")
    private String visitNumber;
    @JsonProperty("Load")
    private String load;
    @JsonProperty("Feature")
    private String feature;
    @JsonProperty("Type")
    private String type;
}
