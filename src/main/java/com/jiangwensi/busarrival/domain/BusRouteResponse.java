package com.jiangwensi.busarrival.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusRouteResponse {
    @JsonProperty("odata.metadata")
    private String odataMetadata;

    @JsonProperty("value")
    private List<BusRoute> value;
}
