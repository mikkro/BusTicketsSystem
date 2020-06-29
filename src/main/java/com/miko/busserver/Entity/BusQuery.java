package com.miko.busserver.Entity;

import lombok.Data;

import java.util.Date;
@Data
public class BusQuery {
    private String origin;
    private String arrival;
    private Date selectedDate;
}
