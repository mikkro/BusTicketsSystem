package com.miko.busserver.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Data
@Entity
@Table(name="bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "number")
    private int number;
    @OneToMany(mappedBy = "bus")
    @JsonIgnoreProperties(value = "bus", allowSetters = true)
    private List<BusStop> busStops;
    @OneToMany(mappedBy = "bus")
    @JsonIgnoreProperties(value = "bus", allowSetters = true)
    private List<Seat> seats;
    @Transient
    private double price;



}
