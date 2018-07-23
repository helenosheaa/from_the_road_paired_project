package models;

import behaviours.IDB;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image implements IDB {

    private int id;
    private String route;
    private String atlText;

    public Image(){}

    public Image(String route, String atlText){
        this.route = route;
        this.atlText = atlText;
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

    @Column(name = "route")
    public String getRoute() {
        return route;
    }
    public void setRoute(String route) {
        this.route = route;
    }

    @Column(name = "alt_text")
    public String getAtlText() {
        return atlText;
    }
    public void setAtlText(String atlText) {
        this.atlText = atlText;
    }
}
