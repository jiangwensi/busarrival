package com.jiangwensi.busarrival.mapper.custom;

import com.jiangwensi.busarrival.service.BusStopService;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jiang Wensi on 9/7/2020
 */
@Service
@TranslatorType
public class Translator {
    BusStopService busStopService;

    public Translator(@Autowired BusStopService busStopService) {
        this.busStopService = busStopService;
    }

    @TranslateBusStopCode
    public String toBusName(String originCode) {
        return busStopService.translateBusStopCodeToName(originCode);
    }
//
//
//    @TranslateBusArrivalLoad
//    public String translateLoad(String load) {
//        if (load.equals("SEA")) {
//            return "Seats Available";
//        }
//        if (load.equals("SDA")) {
//            return "Standing Available";
//        }
//        if (load.equals("LSD")) {
//            return "Limited Standing";
//        }
//        return load;
//    }
}
