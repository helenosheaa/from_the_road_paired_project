package models;

import behaviours.IDB;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "comments")
public class Comment implements IDB {

    private int id;
    private String comment;
    private Calendar date;
    private String username;
    private Article article;

    public Comment(){}

    public Comment(String comment, String username, Article article){
        this.comment = comment;
        this.username = username;
        this.date = Calendar.getInstance();
        this.article = article;
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

    @Column(name = "comment", columnDefinition = "TEXT")
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "date")
    public Calendar getDate() {
        return date;
    }
    public void setDate(Calendar date) {
        this.date = date;
    }

    @Column(name = "username")
    public String getUser() {
        return username;
    }
    public void setUser(String username) {
        this.username = username;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
