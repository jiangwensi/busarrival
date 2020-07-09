package com.jiangwensi.busarrival.mapper;

import com.jiangwensi.busarrival.dto.BusRouteDto;
import com.jiangwensi.busarrival.entity.BusRoute;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BusRouteMapper {

    BusRouteMapper INSTANCE = Mappers.getMapper(BusRouteMapper.class);

    BusRoute toBusRoute(BusRouteDto busRouteDto);
    BusRouteDto toBusRouteDto(BusRoute busRoute);
}
