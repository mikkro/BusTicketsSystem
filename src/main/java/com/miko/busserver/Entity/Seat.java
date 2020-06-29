package com.miko.busserver.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Data
@Table(name="seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="number")
    private int number;
    @Column(name="available")
    private boolean avaiable;
    @ManyToOne
    @JsonIgnoreProperties(value = "seats", allowSetters = true)
    private Bus bus;
    @ManyToOne
    @JsonIgnoreProperties(value = "seats", allowSetters = true)
    private Ticket ticket;

}
