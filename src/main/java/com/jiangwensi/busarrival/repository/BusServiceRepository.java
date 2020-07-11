package com.jiangwensi.busarrival.repository;

import com.jiangwensi.busarrival.entity.BusServiceItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jiang Wensi on 9/7/2020
 */
public interface BusServiceRepository  extends CrudRepository<BusServiceItem,Long> {
    public List<BusServiceItem> findByServiceNo(String serviceNo);
}
