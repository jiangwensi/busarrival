package com.jiangwensi.busarrival.repository;

import com.jiangwensi.busarrival.domain.entity.BusRoute;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
//@Repository
public interface BusRouteRepository extends CrudRepository<BusRoute,Long> {

    Iterable<BusRoute> findByBusStopCode(String destionationCode);
    Iterable<BusRoute> findByServiceNo(String serviceNo);

//    @Query("select b from BusRoute b where b.serviceNo = ?1 limit ?2")
    Iterable<BusRoute> findByServiceNoAndStopSequenceLessThan(String serviceNo,
                                                   Integer maxStopSequence);

}
