package com.hibernate.onetoone.join_table.domain;

import javax.persistence.*;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title, discreption;

    @OneToOne(mappedBy = "article", cascade = CascadeType.ALL)
    private Category category;

    public Article() { }

    public Article(String title, String discreption) {
        this.title = title;
        this.discreption = discreption;
    }

    public Article(String title, String discreption, Category category) {
        this.title = title;
        this.discreption = discreption;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscreption() {
        return discreption;
    }

    public void setDiscreption(String discreption) {
        this.discreption = discreption;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", discreption='" + discreption + '\'' +
                ", category=" + category +
                '}';
    }
}
