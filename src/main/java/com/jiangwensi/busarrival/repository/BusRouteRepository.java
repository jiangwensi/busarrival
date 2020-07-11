package com.jiangwensi.busarrival.repository;

import com.jiangwensi.busarrival.domain.entity.BusRoute;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
public interface BusRouteRepository extends CrudRepository<BusRoute,Long> {

    List<BusRoute> findByBusStopCode(String destionationCode);
}
