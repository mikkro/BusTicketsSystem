package com.miko.busserver.Service;

import com.miko.busserver.Entity.Bus;

import java.util.List;

public interface BusService {
    Bus findBusById(Long lId);

    List<Bus> getBusses(String origin, String arrival);

    double calculatePrice(String origin, String destination, Bus selectedBus);

    Long getNumberOfBusses();

    List<Bus> findAll();

    Bus getBusDetails(Long lId);


    void deleteBus(Bus bus);

    void addNewBus(Bus bus);
}
