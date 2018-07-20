package models;

import behaviours.IDB;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<Visitor> visitorSaves;

    public Article() {
    }

    public Article(String title, Writer author, String content, String summary) {
        this.title = title;
        this.author = author;
        this.date = Calendar.getInstance ();
        this.content = content;
        this.summary = summary;
        this.visitCounter = 0;
        this.tags = new ArrayList<>();
        this.categories = new ArrayList<>();
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
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

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "articles_categories",
    joinColumns = {@JoinColumn (name = "article_id", nullable = false, updatable = false)},
    inverseJoinColumns = {@JoinColumn(name = "category_id", nullable = false, updatable = false)})
    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public void addCategory(Category category){
        this.categories.add(category);
    }

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "articles_tags",
            joinColumns = {@JoinColumn (name = "article_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", nullable = false, updatable = false)})
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    @Column(name = "visit_counter")
    public int getVisitCounter() {
        return visitCounter;
    }
    public void setVisitCounter(int visitCounter) {
        this.visitCounter = visitCounter;
    }
    public void indexVisitCounter(){
        this.visitCounter += 1;
    }

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "articles_visitors",
            joinColumns = {@JoinColumn (name = "article_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "visitor_id", nullable = false, updatable = false)})
    public List<Visitor> getVisitorSaves() {
        return visitorSaves;
    }
    public void setVisitorSaves(List<Visitor> visitorSaves) {
        this.visitorSaves = visitorSaves;
    }
}
