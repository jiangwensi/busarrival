package com.jiangwensi.busarrival.mapper;

import com.jiangwensi.busarrival.dto.BusArrivalDto;
import com.jiangwensi.busarrival.entity.BusArrival;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Mapper(componentModel = "spring")
public interface BusArrivalMapper {

    BusArrivalMapper INSTANCE = Mappers.getMapper(BusArrivalMapper.class);

    BusArrival toBusArrival(BusArrivalDto busRouteDto);
    BusArrivalDto toBusArrivalDto(BusArrival busRoute);
}
