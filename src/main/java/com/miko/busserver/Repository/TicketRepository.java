package com.miko.busserver.Repository;

import com.miko.busserver.Entity.Ticket;
import com.miko.busserver.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket findTicketByTicketNumber(@Param("reservationNumber")String reservationNumber);

    List<Ticket> findAllByUser(User user);
}
