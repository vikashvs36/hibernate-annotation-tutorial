package com.hibernate.onetoone.foreignKey.domain;

import javax.persistence.*;

@Entity
@Table(name = "book_detail")
public class BookDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long id;
    private int numberOfPages;

    public BookDetail() { }

    public BookDetail(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "id=" + id +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
