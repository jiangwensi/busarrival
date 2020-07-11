package com.jiangwensi.busarrival.domain.dto;

import lombok.Data;

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
