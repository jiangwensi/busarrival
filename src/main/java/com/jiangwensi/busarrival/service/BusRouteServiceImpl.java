package com.jiangwensi.busarrival.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangwensi.busarrival.domain.dto.BusRouteDto;
import com.jiangwensi.busarrival.domain.entity.BusRoute;
import com.jiangwensi.busarrival.domain.mapper.BusRouteMapper;
import com.jiangwensi.busarrival.response.BusRouteResponse;
import com.jiangwensi.busarrival.repository.BusRouteRepository;
import com.jiangwensi.busarrival.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Service
@Slf4j
public class BusRouteServiceImpl implements BusRouteService {

    @Value("${api.account.key}")
    private String apiKey;

    @Value("${api.url.busroutes}")
    private String url;

    @Autowired
    private BusRouteMapper busRouteMapper;

    private BusRouteRepository busRouteRepository;

    public BusRouteServiceImpl(BusRouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
    }

    @Override
    public List<BusRouteDto> listAllBusRoutes() throws JsonProcessingException {
        log.info("listAllBusRoutes() start");
        List<BusRoute> busRoutes = (List<BusRoute>) busRouteRepository.findAll();
        List<BusRouteDto> dtos = new ArrayList<>();
        busRoutes.forEach(e->dtos.add(BusRouteMapper.INSTANCE.toBusRouteDto(e)));
        return dtos;
    }

    @Override
    public List<BusRoute> syncBusRoutes() throws JsonProcessingException {
        log.info("syncBusRoutes()");
        int size = 500;
        int i = 0;
        List<BusRoute> busRoutes = new ArrayList<>();
        while (size == 500) {
            ResponseEntity<String> response = new HttpUtils().getResponse(url + "?$skip=" + i * 500, apiKey);
            log.info("counter i: {}",i);
            i++;

            ObjectMapper mapper = new ObjectMapper();
            BusRouteResponse busRouteResponse = (BusRouteResponse) mapper.readValue(response.getBody(), BusRouteResponse.class);
            busRoutes.addAll(busRouteResponse.getValue());
            size = busRouteResponse.getValue().size();
            log.info("retrieved bus route size: {}",size);
        }

        log.info("going to update {} bus routes in database",busRoutes.size());
        busRouteRepository.deleteAll();
        busRouteRepository.saveAll(busRoutes);
        return busRoutes;
    }

    @Override
    public List<BusRoute> findByBusStopCode(String busStopCode) {
        Iterable<BusRoute> resultIterable = busRouteRepository.findByBusStopCode(busStopCode);
        List<BusRoute> result = new ArrayList<>();
        resultIterable.forEach(result::add);
        return result;
    }

//    @Override
//    public Map<String, List<BusRouteDto>> findByServiceNo(String serviceNo) {
//        log.debug("findByServiceNo serviceNo:{}",serviceNo);
//        Iterable<BusRoute> resultIterable = busRouteRepository.findByServiceNo(serviceNo);
//        List<BusRouteDto> resultDto = new ArrayList<>();
//        resultIterable.forEach(e-> resultDto.add(busRouteMapper.toBusRouteDto(e)));
//        Map<String, List<BusRouteDto>> collect = resultDto.stream().collect(Collectors.groupingBy(e -> e.getDirection(), toList()));
//        return collect;
//    }

    @Override
    public List<BusRouteDto> findByServiceNo(String serviceNo) {
        log.info("findByServiceNo serviceNo:{}",serviceNo);
        Iterable<BusRoute> resultIterable = busRouteRepository.findByServiceNo(serviceNo);
        List<BusRouteDto> resultDto = new ArrayList<>();
        resultIterable.forEach(e->resultDto.add(busRouteMapper.toBusRouteDto(e)));
        return resultDto;
    }

    @Override
    public List<BusRouteDto> findByServiceNoAndMaxStopSequence(String serviceNo, Integer stopSequence) {
        log.info("findByServiceNo serviceNo:{} stopSequence:{}",serviceNo,stopSequence);
        if (stopSequence<0) {
            return findByServiceNo(serviceNo);
        } else {

            Iterable<BusRoute> resultIterable = busRouteRepository.findByServiceNoAndStopSequenceLessThan(serviceNo,
                    stopSequence);
            List<BusRouteDto> resultDto = new ArrayList<>();
            resultIterable.forEach(e->resultDto.add(busRouteMapper.toBusRouteDto(e)));
            return resultDto;
        }
    }
}
