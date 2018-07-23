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
    private String user;
    private Article article;

    public Comment(){}

    public Comment(String comment, String user, Article article){
        this.comment = comment;
        this.user = user;
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

    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id", nullable = false)
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
