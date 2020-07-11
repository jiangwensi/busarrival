package com.jiangwensi.busarrival.domain.mapper.custom;

import com.jiangwensi.busarrival.service.BusStopService;
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
}
