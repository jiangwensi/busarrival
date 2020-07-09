package com.jiangwensi.busarrival.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jiangwensi.busarrival.entity.BusServiceItem;
import lombok.Data;

import java.util.List;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusServiceItemResponse {

    @JsonProperty("odata.metadata")
    private String odataMetadata;

    @JsonProperty("value")
    private List<BusServiceItem> value;
}
