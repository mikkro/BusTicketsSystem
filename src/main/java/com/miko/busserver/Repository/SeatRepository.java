package com.miko.busserver.Repository;

import com.miko.busserver.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query(value="select * from seat where (seat.bus_id=:id and seat.available=1)",nativeQuery = true)
    List<Seat> findFreeSeatsByBusId(@Param("id") String id);
}
