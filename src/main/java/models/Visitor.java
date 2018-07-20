package models;

import behaviours.IDB;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "visitors")
public class Visitor implements IDB {

    private int id;
    private String name;
    private List<Article> savedArticles;

    public Visitor(String name) {
        this.name = name;
        this.savedArticles = new ArrayList<>();
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

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "articles_visitors",
            joinColumns = {@JoinColumn (name = "visitor_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "article_id", nullable = false, updatable = false)})
    public List<Article> getSavedArticles() {
        return savedArticles;
    }
    public void setSavedArticles(List<Article> savedArticles) {
        this.savedArticles = savedArticles;
    }
    public void saveArticle(Article article){
        this.savedArticles.add(article);
    }
}
