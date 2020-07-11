package com.jiangwensi.busarrival.mapper;

import com.jiangwensi.busarrival.dto.BusStopDto;
import com.jiangwensi.busarrival.entity.BusStop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BusStopMapper {

    BusStopMapper INSTANCE = Mappers.getMapper(BusStopMapper.class);

    BusStop toBusStopItem(BusStopDto busStopDto);
    BusStopDto toBusStopDto(BusStop busStop);
}
