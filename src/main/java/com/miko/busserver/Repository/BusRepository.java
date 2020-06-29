package com.miko.busserver.Repository;

import com.miko.busserver.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BusRepository extends JpaRepository<Bus, Long> {

    @Query(value="SELECT *  FROM bus inner join bus_stop as b1 inner join bus_stop as b2 where (b1.bus_id=bus.id and b2.bus_id=bus.id and b1.name = :origin and b2.name =:arrival )", nativeQuery = true)
    List<Bus> findBuses(@Param("origin")String origin, @Param("arrival")String arrival);

    Bus findBusById(Long id);

    List<Bus> findAll();
}
