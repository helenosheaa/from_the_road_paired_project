package db.helpers;

import db.DBHelper;
import models.Visitor;

import java.util.List;

public class DBVisitor extends DBHelper {

    public void deleteAll(){
        deleteAll(Visitor.class);
    }

    public List<Visitor> getAll(){
        return getAll(Visitor.class);
    }

    public Visitor find(int id){
        return find(id, Visitor.class);
    }

}
