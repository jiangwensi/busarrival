package com.jiangwensi.busarrival.domain.mapper;

import com.jiangwensi.busarrival.domain.dto.BusRouteDto;
import com.jiangwensi.busarrival.domain.entity.BusRoute;
import com.jiangwensi.busarrival.domain.mapper.custom.TranslateBusStopCode;
import com.jiangwensi.busarrival.domain.mapper.custom.Translator;
import com.jiangwensi.busarrival.domain.mapper.custom.TranslatorType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = Translator.class, componentModel = "spring")
public interface BusRouteMapper {

    BusRouteMapper INSTANCE = Mappers.getMapper(BusRouteMapper.class);

    BusRoute toBusRoute(BusRouteDto busRouteDto);
    @Mappings({
            @Mapping(source = "busStopCode", target = "busStopName",
                    qualifiedBy =
                            {TranslatorType.class,
                                    TranslateBusStopCode.class})}
    )
    BusRouteDto toBusRouteDto(BusRoute busRoute);
}
