package models;

import behaviours.IDB;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "writers")
public class Writer implements IDB {

    private int id;
    private String name;
    private String blurb;
    private List<Article> articles;

    public Writer(){}

    public Writer(String name, String blurb){
        this.name = name;
        this.blurb = blurb;
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

    @OneToMany(mappedBy = "author")
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Column(name = "blurb")
    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }
}
