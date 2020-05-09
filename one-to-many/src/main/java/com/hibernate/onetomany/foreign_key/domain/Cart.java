package com.hibernate.onetomany.foreign_key.domain;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;
    private String name;
    private BigDecimal total;

    @OneToMany
    @JoinColumn(name = "cart_id_fk")
    private Set<Item> items;

    public Cart() { }

    public Cart(String name, BigDecimal total) {
        this.name = name;
        this.total = total;
    }

    public Cart(String name, Set<Item> items) {
        this.name = name;
        this.items = items;
    }

    public Cart(String name, BigDecimal total, Set<Item> items) {
        this.name = name;
        this.total = total;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotal() {
        return this.getItems().stream()
                .map(item -> item.getIteamTotal().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", total=" + this.getTotal() +
//                ", items=" + this.getItems() +
                '}';
    }
}
