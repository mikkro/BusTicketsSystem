package com.miko.busserver.Repository;

import com.miko.busserver.Entity.Bus;
import com.miko.busserver.Entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {

    @Query(value="select * FROM bus_stop  WHERE name = :origin", nativeQuery = true)
    BusStop findByName(@Param("origin") String origin);

    @Query(value="select distinct bus_stop.name FROM bus_stop", nativeQuery = true)
    List<String> findAllNames();

    List<BusStop> findAllByBus_Id(Long id);

}
