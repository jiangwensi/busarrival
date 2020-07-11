package com.jiangwensi.busarrival.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiangwensi.busarrival.entity.BusArrival;
import com.jiangwensi.busarrival.entity.BusRoute;
import lombok.Data;

import java.util.List;

/**
 * Created by Jiang Wensi on 11/7/2020
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusArrivalResponse {
    @JsonProperty("odata.metadata")
    private String odataMetadata;

    @JsonProperty("BusStopCode")
    private String busStopCode;

    @JsonProperty("Services")
    private List<BusArrival> services;
}
