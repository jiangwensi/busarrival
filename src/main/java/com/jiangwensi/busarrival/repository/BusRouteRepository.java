package com.jiangwensi.busarrival.repository;

import com.jiangwensi.busarrival.domain.entity.BusRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
//@Repository
public interface BusRouteRepository extends CrudRepository<BusRoute,Long> {

    Iterable<BusRoute> findByBusStopCode(String destionationCode);
}
