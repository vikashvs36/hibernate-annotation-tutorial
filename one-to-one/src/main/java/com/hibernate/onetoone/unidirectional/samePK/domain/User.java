package com.hibernate.onetoone.unidirectional.samePK.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(generator= "custom_foreigngen")
    private Long id;
    private String username, password;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    public User() { }

    public User(String username, String password, Address address) {
        this.username = username;
        this.password = password;
        this.address = address;
    }
}
