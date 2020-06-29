package com.miko.busserver.Service;

import com.miko.busserver.Entity.Bus;
import com.miko.busserver.Entity.BusStop;
import com.miko.busserver.Entity.Seat;

import java.util.List;
import java.util.Set;

public interface BusStopService {

    BusStop findByName(String name);

    List<String> findAllBusStopsNames();

    double getBusStopDistanceByName(String name, List<BusStop> busStopList);

    List<BusStop> getBusStopsByBusId(Long lId);

    BusStop addNewBusStop(BusStop newBusStop);

    BusStop findById(Long lId);
}
