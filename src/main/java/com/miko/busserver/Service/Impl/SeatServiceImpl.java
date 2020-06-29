package com.miko.busserver.Service.Impl;

import com.miko.busserver.Entity.Seat;
import com.miko.busserver.Entity.Ticket;
import com.miko.busserver.Repository.SeatRepository;
import com.miko.busserver.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    SeatRepository seatRepository;

    @Override
    public void reserveSeats(List<Seat> seatList, Ticket ticket) {
        for (Seat seat:
             seatList) {
            Seat optionalSeat = seatRepository.findById(seat.getId()).get();
            optionalSeat.setAvaiable(false);
            optionalSeat.setTicket(ticket);
        }
    }

    @Override
    public Seat addNewSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Seat findById(Long lId) {
        return seatRepository.findById(lId).get();
    }

    @Override
    public void deleteSeat(Seat seat) {
        seatRepository.delete(seat);
    }

    @Override
    public void setSeatFree(Seat seat) {
        seatRepository.save(seat);
    }


    @Override
    public List<Seat> getSeats(String id) {
        return seatRepository.findFreeSeatsByBusId(id);
    }


}
