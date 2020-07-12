package com.jiangwensi.busarrival.repository;

import com.jiangwensi.busarrival.domain.entity.BusStop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jiang Wensi on 9/7/2020
 */
//@Repository
public interface BusStopRepository extends CrudRepository<BusStop,Long> {
    Optional<BusStop> findByBusStopCode(String code);

    @Query("select b from BusStop b where b.description like %?1 or b.roadName like %?2")
    Iterable<BusStop> findByLikeBusStopDescriptionAndLikeRoadLike(String description, String roadName);

    Iterable<BusStop> findByDescriptionIgnoreCaseContaining(String busStop);


    Iterable<BusStop> findByDescription(String description);
}
