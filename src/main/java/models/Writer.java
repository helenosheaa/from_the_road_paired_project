package models;

import behaviours.IDB;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "writers")
public class Writer implements IDB {

    private int id;
    private String name;
//    private List<Article> articles;

    public Writer(){}

    public Writer(String name){
        this.name = name;
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


}
