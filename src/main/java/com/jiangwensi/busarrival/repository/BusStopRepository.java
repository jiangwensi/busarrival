package com.jiangwensi.busarrival.repository;

import com.jiangwensi.busarrival.domain.entity.BusStop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jiang Wensi on 9/7/2020
 */
//@Repository
public interface BusStopRepository extends CrudRepository<BusStop,Long> {
    Optional<BusStop> findByBusStopCode(String code);

    @Query("select b from BusStop b where " +
            "lower(b.description) like lower(concat('%',:searchKey,'%')) or " +
            "lower(b.roadName) like lower(concat('%',:searchKey,'%'))")
    Iterable<BusStop> findByDescriptionRoadNameIgnoreCaseContaining(@Param("searchKey")String searchKey);

    Iterable<BusStop> findByDescription(String description);

    @Query("select b from BusStop b")
    Iterable<BusStop> listAllBusStops();

    @Query(value = "select * from bus_stop  where latitude > :lat - :diff and latitude < :lat + :diff and " +
            "longitude>:lon-:diff and longitude < :lon + :diff",nativeQuery = true)
    Iterable<BusStop> nearBy(@Param("lat") String latitude, @Param("lon") String longitude, @Param("diff") Double diff);
}
