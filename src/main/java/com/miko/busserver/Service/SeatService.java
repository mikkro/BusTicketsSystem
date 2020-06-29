package com.miko.busserver.Service;

import com.miko.busserver.Entity.Seat;
import com.miko.busserver.Entity.Ticket;

import java.util.List;
import java.util.Set;

public interface SeatService {
    void reserveSeats(List<Seat> seatList, Ticket Ticket);

    List<Seat> getSeats(String id);

    Seat addNewSeat(Seat seat);

    Seat findById(Long lId);

    void deleteSeat(Seat seat);

    void setSeatFree(Seat seat);
}
