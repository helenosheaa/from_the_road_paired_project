package models;

import behaviours.IDB;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article implements IDB {

    private int id;
    private String title;
    private Writer author;
    private Calendar date;
    private String content;
    private String summary;
    private List<Category> categories;
    private List<Tag> tags;
    private int visitCounter;

    public Article() {
    }

    public Article(String title, Writer author, String content, String summary) {
        this.title = title;
        this.author = author;
        this.date = Calendar.getInstance ();
        this.content = content;
        this.summary = summary;
        this.visitCounter = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Writer getAuthor() {
        return author;
    }

    public void setAuthor(Writer author) {
        this.author = author;
    }

    @Column(name = "date")
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Column(name = "visit_counter")
    public int getVisitCounter() {
        return visitCounter;
    }

    public void setVisitCounter(int visitCounter) {
        this.visitCounter = visitCounter;
    }
}
