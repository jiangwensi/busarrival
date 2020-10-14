package com.jiangwensi.busarrival.domain.mapper;

import com.jiangwensi.busarrival.domain.dto.BusStopDto;
import com.jiangwensi.busarrival.domain.entity.BusStop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BusStopMapper {
    BusStopDto toBusStopDto(BusStop busStop);
}
