package models;

import behaviours.IDB;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")

public class Tag implements IDB {

    private int id;
    private String name;
//    private List<Article> articles;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Article> getArticles() {
//        return articles;
//    }
//
//    public void setArticles(List<Article> articles) {
//        this.articles = articles;
//    }
}
