package com.jiangwensi.busarrival.repository;

import com.jiangwensi.busarrival.entity.BusStop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jiang Wensi on 9/7/2020
 */
public interface BusStopRepository extends CrudRepository<BusStop,Long> {
    BusStop findByBusStopCode(String code);

    @Query("select b from BusStop b where b.description like %?1 or b.roadName like %?2")
    List<BusStop> findByLikeBusStopDescriptionAndLikeRoadLike(String description, String roadName);

    List<BusStop> findByDescriptionIgnoreCaseContaining(String busStop);


    List<BusStop> findByDescription(String description);
}
