package com.jiangwensi.busarrival.repository;

import com.jiangwensi.busarrival.domain.entity.BusServiceItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jiang Wensi on 9/7/2020
 */
//@Repository
public interface BusServiceRepository  extends CrudRepository<BusServiceItem,Long> {
    Iterable<BusServiceItem> findByServiceNo(String serviceNo);

}
