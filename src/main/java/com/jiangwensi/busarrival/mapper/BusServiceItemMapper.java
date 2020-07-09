package com.jiangwensi.busarrival.mapper;

import com.jiangwensi.busarrival.dto.BusServiceItemDto;
import com.jiangwensi.busarrival.entity.BusServiceItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BusServiceItemMapper {

    BusServiceItemMapper INSTANCE = Mappers.getMapper(BusServiceItemMapper.class);

    BusServiceItem toBusServiceItem(BusServiceItemDto busServiceItemDto);
    BusServiceItemDto toBusServiceItemDto(BusServiceItem busServiceItem);
}
