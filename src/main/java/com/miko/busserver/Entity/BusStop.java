package com.miko.busserver.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="bus_stop")
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "distance")
    private Double distance;
    @Column(name = "departure_time")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date departureTime;
    @ManyToOne
    @JsonIgnoreProperties(value = "busStops", allowSetters = true)
    private Bus bus;

}
