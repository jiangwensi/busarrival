package com.jiangwensi.busarrival.domain.mapper;

import com.jiangwensi.busarrival.domain.dto.BusServiceItemDto;
import com.jiangwensi.busarrival.domain.entity.BusServiceItem;
import com.jiangwensi.busarrival.domain.mapper.custom.Translator;
import com.jiangwensi.busarrival.domain.mapper.custom.TranslateBusStopCode;
import com.jiangwensi.busarrival.domain.mapper.custom.TranslatorType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = Translator.class, componentModel = "spring")
public interface BusServiceItemMapper {

    BusServiceItemMapper INSTANCE = Mappers.getMapper(BusServiceItemMapper.class);

    BusServiceItem toBusServiceItem(BusServiceItemDto busServiceItemDto);

    @Mappings({
            @Mapping(source = "originCode", target = "originName",
                    qualifiedBy =
                            {TranslatorType.class,
                                    TranslateBusStopCode.class}),
            @Mapping(source = "destinationCode", target = "destinationName",
                    qualifiedBy =
                            {TranslatorType.class,
                                    TranslateBusStopCode.class})}
    )
    BusServiceItemDto toBusServiceItemDto(BusServiceItem busServiceItem);

}
