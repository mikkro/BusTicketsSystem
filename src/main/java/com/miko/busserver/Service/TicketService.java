package com.miko.busserver.Service;

import com.miko.busserver.Entity.Ticket;
import com.miko.busserver.Entity.User;

import java.util.List;

public interface TicketService  {

    Ticket save(Ticket newTicket);

    Ticket findTicketByReservationNumber(String reservationNumber);

    Long getNumberOfTickets();

    List<Ticket> findAll();


    List<Ticket> findAllByUser(User user);

    Ticket findById(Long lId);

    void deleteTicket(Ticket ticket);
}
