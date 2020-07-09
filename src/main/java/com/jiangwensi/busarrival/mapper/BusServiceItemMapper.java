package com.jiangwensi.busarrival.mapper;

import com.jiangwensi.busarrival.dto.BusServiceItemDto;
import com.jiangwensi.busarrival.entity.BusServiceItem;
import com.jiangwensi.busarrival.mapper.custom.BusStopCodeMapper;
import com.jiangwensi.busarrival.mapper.custom.BusStopCodeToName;
import com.jiangwensi.busarrival.mapper.custom.BusStopCodeTranslater;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BusStopCodeMapper.class, componentModel = "spring")
public interface BusServiceItemMapper {

    BusServiceItemMapper INSTANCE = Mappers.getMapper(BusServiceItemMapper.class);

    BusServiceItem toBusServiceItem(BusServiceItemDto busServiceItemDto);

    @Mappings({
            @Mapping(source = "originCode", target = "originName",
                    qualifiedBy =
                            {BusStopCodeTranslater.class,
                                    BusStopCodeToName.class}),
            @Mapping(source = "destinationCode", target = "destinationName",
                    qualifiedBy =
                            {BusStopCodeTranslater.class,
                                    BusStopCodeToName.class})}
    )
    BusServiceItemDto toBusServiceItemDto(BusServiceItem busServiceItem);

}
