package models;

import behaviours.IDB;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "visitors")
public class Visitor implements IDB {

    private int id;
    private String name;
//    private List<Article> savedArticles;

    public Visitor(String name) {
        this.name = name;
    }

    public Visitor() {
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

//    public List<Article> getSavedArticles() {
//        return savedArticles;
//    }
//
//    public void setSavedArticles(List<Article> savedArticles) {
//        this.savedArticles = savedArticles;
//    }
}
