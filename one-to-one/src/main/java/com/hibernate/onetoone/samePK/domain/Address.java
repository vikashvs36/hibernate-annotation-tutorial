package com.hibernate.onetoone.samePK.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String state;

    public Address() { }

    public Address(String state) {
        this.state = state;
    }
}
