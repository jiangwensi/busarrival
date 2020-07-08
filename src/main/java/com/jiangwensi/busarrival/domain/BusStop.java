package com.jiangwensi.busarrival.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStop {
    @JsonProperty("BusStopCode")
    String busStopCode;
    @JsonProperty("RoadName")
    String roadName;
    @JsonProperty("Description")
    String description;
    @JsonProperty("Latitude")
    String latitude;
    @JsonProperty("Longitude")
    String longitude;
}
