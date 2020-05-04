package com.hibernate.onetoone.samePK.domain;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String state;
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private User user;

    public Address() { }

    public Address(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", state='" + state + '\'' +
                '}';
    }
}
