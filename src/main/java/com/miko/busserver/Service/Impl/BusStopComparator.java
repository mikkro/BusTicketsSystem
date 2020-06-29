package com.miko.busserver.Service.Impl;

import com.miko.busserver.Entity.BusStop;

import java.util.Comparator;

public class BusStopComparator implements Comparator<BusStop> {
    @Override
    public int compare(BusStop o1, BusStop o2) {
        if(o1 == null)
            return 1;
        else if(o2 == null)
            return -1;
        else if(o1.getDistance() == o2.getDistance()){
            return 0;
        }
        else if(o1.getDistance()>o2.getDistance()){
            return -1;
        }
        else{
            return 1;
        }
    }
}
