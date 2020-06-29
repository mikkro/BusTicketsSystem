package com.miko.busserver.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "country")
    private String country;
    @Column(name = "zipcode")
    private String zipCode;
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private User user;

}
