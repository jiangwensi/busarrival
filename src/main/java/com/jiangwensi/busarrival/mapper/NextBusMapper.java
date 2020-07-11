package com.jiangwensi.busarrival.mapper;

import com.jiangwensi.busarrival.dto.NextBusDto;
import com.jiangwensi.busarrival.entity.NextBus;
import com.jiangwensi.busarrival.mapper.custom.Translator;
import com.jiangwensi.busarrival.mapper.custom.TranslateBusStopCode;
import com.jiangwensi.busarrival.mapper.custom.TranslatorType;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jiang Wensi on 11/7/2020
 */
@Mapper(uses = Translator.class, componentModel = "spring")
public interface NextBusMapper {

    NextBusMapper INSTANCE = Mappers.getMapper(NextBusMapper.class);

    NextBus toNextBus(NextBusDto busStopDto);

    @Mappings({
            @Mapping(source = "originCode", target = "originName", qualifiedBy = {TranslatorType.class, TranslateBusStopCode.class}),
            @Mapping(source = "destinationCode", target = "destinationName", qualifiedBy = {TranslatorType.class, TranslateBusStopCode.class}),
            @Mapping(target = "load", qualifiedByName = "translateLoad"),
            @Mapping(target = "feature", qualifiedByName = "translateFeature"),
            @Mapping(target = "type", qualifiedByName = "translateType"),
            @Mapping(target = "estimatedArrival", qualifiedByName = "translateETA")
    })
    NextBusDto toNextBusDto(NextBus busStop);

    @Named("translateLoad")
    default String translateLoad(String load) {
        if (load.equals("SEA")) {
            return "Seats Available";
        }
        if (load.equals("SDA")) {
            return "Standing Available";
        }
        if (load.equals("LSD")) {
            return "Limited Standing";
        }
        return load;
    }

    @Named("translateFeature")
    default String translateFeature(String feature) {
        if (feature.equals("WAB")) {
            return "Wheelchair Accessible Bus";
        }
        return feature;
    }

    @Named("translateType")
    default String translateType(String type) {
        if (type.equals("SD")) {
            return "Single Deck";
        }
        if (type.equals("DD")) {
            return "Double Deck";
        }
        if (type.equals("BD")) {
            return "Bendy";
        }
        return type;
    }

    @Named("translateETA")
    default String translateETA(String eta) throws ParseException {
        if (eta=="") {
            return "-";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date etaDate = sdf.parse(eta);
        long seconds = (etaDate.getTime() - new Date().getTime()) / 1000;
        if (seconds<0) {
            return "-";
        }
        String min = String.valueOf(seconds / 60);
//        String sec = String.valueOf(seconds % 60);
        return min + " min";
    }
}
