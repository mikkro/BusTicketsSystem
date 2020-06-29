package com.miko.busserver.Service.Impl;

import com.miko.busserver.Entity.Bus;
import com.miko.busserver.Entity.BusStop;
import com.miko.busserver.Entity.Seat;
import com.miko.busserver.Repository.BusRepository;
import com.miko.busserver.Repository.BusStopRepository;
import com.miko.busserver.Repository.SeatRepository;
import com.miko.busserver.Service.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BusStopServiceImpl implements BusStopService {

    @Autowired
    private BusStopRepository busStopRepository;

    public double getBusStopDistanceByName(String name, List<BusStop> busStopList){
        double distance = 0.0;
        for (BusStop busStop: busStopList) {
            if(busStop.getName().equals(name)){
                distance =  busStop.getDistance();
                break;
            }
        }
        return distance;
    }

    @Override
    public BusStop findByName(String name) {
        return busStopRepository.findByName(name);
    }

    @Override
    public List<String> findAllBusStopsNames() {
        List<String> allBusStopsNames = busStopRepository.findAllNames();
        Collections.sort(allBusStopsNames);
        return allBusStopsNames;
    }

    @Override
    public List<BusStop> getBusStopsByBusId(Long lId) {
        return busStopRepository.findAllByBus_Id(lId);
    }

    @Override
    public BusStop addNewBusStop(BusStop newBusStop) {
        return busStopRepository.save(newBusStop);
    }

    @Override
    public BusStop findById(Long lId) {
        return busStopRepository.findById(lId).get();
    }


}
