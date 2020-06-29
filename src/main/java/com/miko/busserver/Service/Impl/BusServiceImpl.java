package com.miko.busserver.Service.Impl;

import com.miko.busserver.Entity.Bus;
import com.miko.busserver.Entity.BusStop;
import com.miko.busserver.Entity.Seat;
import com.miko.busserver.Repository.BusRepository;
import com.miko.busserver.Service.BusService;
import com.miko.busserver.Service.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusServiceImpl implements BusService {

    final double ticketPricePerKm = 2.13;

    @Autowired
    BusRepository busRepository;

    @Autowired
    BusStopService busStopService;

    @Override
    public Bus findBusById(Long lId) {
        return busRepository.findBusById(lId);
    }

    @Override
    public double calculatePrice(String origin, String destination, Bus selectedBus) {

        double destinationDistnce = busStopService.getBusStopDistanceByName(destination, selectedBus.getBusStops());
        double originDistnce = busStopService.getBusStopDistanceByName(origin, selectedBus.getBusStops());
        return ((destinationDistnce-originDistnce)*ticketPricePerKm);
    }

    @Override
    public List<Bus> getBusses(String origin, String arrival) {
        List<Bus> busList = busRepository.findBuses(origin, arrival);

        List<Bus> checkedBusList = new ArrayList<>();
        for (Bus b:busList) {
            List<BusStop> busStopList = b.getBusStops();
            double originDistance = 0;
            double destinationDistance = 0;
            for (BusStop busStop: busStopList) {
                if(busStop.getName().equals(origin)) originDistance = busStop.getDistance();
                if(busStop.getName().equals(arrival)) destinationDistance = busStop.getDistance();
            }
            if(destinationDistance>originDistance) {
                List<Seat> seatsInBus = b.getSeats();
                for (Seat seat : seatsInBus) {
                    if (seat.isAvaiable() == true) {
                        checkedBusList.add(b);
                        break;
                    }
                }
                b.setPrice(calculatePrice(origin, arrival, b));
            }
        }


        return checkedBusList;
    }

    @Override
    public Long getNumberOfBusses() {
        return busRepository.count();
    }

    @Override
    public List<Bus> findAll() {
        return busRepository.findAll();
    }

    @Override
    public Bus getBusDetails(Long lId) {
        return busRepository.findById(lId).get();
    }

    @Override
    public void deleteBus(Bus bus) {
        busRepository.delete(bus);
    }

    @Override
    public void addNewBus(Bus bus) {
        busRepository.save(bus);
    }


}
