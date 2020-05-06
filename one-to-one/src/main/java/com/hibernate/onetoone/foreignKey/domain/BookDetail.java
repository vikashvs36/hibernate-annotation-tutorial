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

    // name of mappedBy (bookDetails) is same as properties of BookDetails Object which is present in Book Object
    @OneToOne(mappedBy = "bookDetail")
    private Book book;


    public BookDetail() { }

    public BookDetail(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public BookDetail(int numberOfPages, Book book) {
        this.numberOfPages = numberOfPages;
        this.book = book;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "id=" + id +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
