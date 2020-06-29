package com.miko.busserver.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Set;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "ticketNumber",unique=true)
    private String ticketNumber;
    @Column(name = "price")
    private double price;
    @Column(name="seats")
    @OneToMany(mappedBy = "ticket")
    @JsonIgnoreProperties(value = "ticket", allowSetters = true)
    private List<Seat> seats;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(name="origin")
    private String origin;
    @Column(name="destination")
    private String destination;

    public Ticket(Ticket ticket){
        this.setUser(ticket.getUser());
        this.setDestination(ticket.getDestination());
        this.setOrigin(ticket.getOrigin());
        this.setSeats(ticket.getSeats());
        this.setTicketNumber(generateTicketNumber());
        this.setPrice(ticket.getPrice());
    }

    private String generateTicketNumber(){
        Random random = new Random();
        String generatedTicketNumber = "";
        for(int i=0;generatedTicketNumber.length()<6;i++){
            generatedTicketNumber += random.nextInt(9);
            generatedTicketNumber += (char) (random.nextInt(26)+ 'a');
        }
        return generatedTicketNumber.toUpperCase();
    }





}
