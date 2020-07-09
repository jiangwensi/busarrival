package com.jiangwensi.busarrival.mapper.custom;

import com.jiangwensi.busarrival.service.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jiang Wensi on 9/7/2020
 */
@Service
@BusStopCodeTranslater
public class BusStopCodeMapper {
    BusStopService busStopService;

    public BusStopCodeMapper(@Autowired BusStopService busStopService) {
        this.busStopService = busStopService;
    }

    @BusStopCodeToName
    public String toBusName(String originCode) {
        return busStopService.translateBusStopCodeToName(originCode);
    }
}
