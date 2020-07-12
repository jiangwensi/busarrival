package com.jiangwensi.busarrival.repository;

import com.jiangwensi.busarrival.domain.entity.BusServiceItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jiang Wensi on 9/7/2020
 */
//@Repository
public interface BusServiceRepository  extends CrudRepository<BusServiceItem,Long> {
    Iterable<BusServiceItem> findByServiceNo(String serviceNo);

}
