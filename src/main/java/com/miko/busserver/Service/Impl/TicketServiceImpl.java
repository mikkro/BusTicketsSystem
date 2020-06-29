package com.miko.busserver.Service.Impl;

import com.miko.busserver.Entity.Ticket;
import com.miko.busserver.Entity.User;
import com.miko.busserver.Repository.TicketRepository;
import com.miko.busserver.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket save(Ticket newTicket) {
        return ticketRepository.save(newTicket);
    }

    @Override
    public Ticket findTicketByReservationNumber(String reservationNumber) {
        return ticketRepository.findTicketByTicketNumber(reservationNumber);
    }

    @Override
    public Long getNumberOfTickets() {
        return ticketRepository.count();
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findAllByUser(User user) {
        return ticketRepository.findAllByUser(user);
    }

    @Override
    public Ticket findById(Long lId) {
        return ticketRepository.findById(lId).get();
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }
}
