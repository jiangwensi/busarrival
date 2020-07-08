package com.jiangwensi.busarrival.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Comparator;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStop implements Comparator<BusStop> {
    @JsonProperty("BusStopCode")
    private String busStopCode;
    @JsonProperty("RoadName")
    private String roadName;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Latitude")
    private String latitude;
    @JsonProperty("Longitude")
    private String longitude;

    @Override
    public int compare(BusStop o1, BusStop o2) {
        return o1.getDescription().compareTo(o2.getDescription());
    }
}
