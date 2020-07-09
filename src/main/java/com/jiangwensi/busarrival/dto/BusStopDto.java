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
public class BusStopDto {

    private Long id;
    private String busStopCode;
    private String roadName;
    private String description;
    private String latitude;
    private String longitude;
}
