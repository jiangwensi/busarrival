package com.jiangwensi.busarrival.repository;

import com.jiangwensi.busarrival.domain.dto.BusServiceStopArrivalDto;
import com.jiangwensi.busarrival.domain.entity.BusRoute;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Jiang Wensi on 14/10/2020
 */
public interface BusServiceStopArrivalRepository extends CrudRepository<BusRoute, Long> {

//    @Query(nativeQuery = true,
//            value = "select br.service_no, br.direction, br.stop_sequence, bs.bus_stop_code, bs.description, bs.road_name" +
//                    "from bus_route br " +
//                    "join bus_stop bs on br.bus_stop_code = bs.bus_stop_code " +
//                    "and br.service_no = :busNo" +
//                    "order by br.direction, br.stop_sequence;")

    @Query("select new com.jiangwensi.busarrival.domain.dto.BusServiceStopArrivalDto(br.serviceNo, br.direction, br" +
            ".stopSequence, bs.busStopCode, bs.description, bs.roadName)" +
            "from BusRoute br join BusStop bs on br.busStopCode = bs.busStopCode " +
                    "and br.serviceNo = :busNo")
    List<BusServiceStopArrivalDto> findBusServiceStopArrivalDto(@Param("busNo") String serviceNo);
}
