package com.hibernate.onetoone.join_table.domain;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "category_article",
            joinColumns = {
                    @JoinColumn(name = "category_id_FK", referencedColumnName = "id", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "artical_id_FK", referencedColumnName = "id", unique = true, nullable = false)
            }
    )
    private Article article;

    public Category() { }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Article article) {
        this.name = name;
        this.article = article;
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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", article=" + article +
                '}';
    }
}
