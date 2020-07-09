package com.jiangwensi.busarrival.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Comparator;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStop implements Comparator<BusStop> {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
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
