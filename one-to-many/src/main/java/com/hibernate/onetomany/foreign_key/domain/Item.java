package com.hibernate.onetomany.foreign_key.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;
    private String name;
    private BigDecimal iteamTotal;
    private Integer quantity;

    public Item() {
    }

    public Item(String name, BigDecimal iteamTotal) {
        this.name = name;
        this.iteamTotal = iteamTotal;
    }

    public Item(String name, BigDecimal iteamTotal, Integer quantity) {
        this.name = name;
        this.iteamTotal = iteamTotal;
        this.quantity = quantity;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIteamTotal() {
        return iteamTotal;
    }

    public void setIteamTotal(BigDecimal iteamTotal) {
        this.iteamTotal = iteamTotal;
    }

    public Integer getQuantity() {
        return (quantity != null && quantity > 0) ? quantity : 1;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + this.getItemId() +
                ", name='" + this.getName() + '\'' +
                ", iteamTotal=" + this.getIteamTotal() +
                ", quantity=" + this.getQuantity() +
                '}';
    }
}
