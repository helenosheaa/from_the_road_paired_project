package db.helpers;

import db.DBHelper;
import models.Category;

import java.util.List;

public class DBCategory extends DBHelper {

    public void deleteAll(){
        deleteAll(Category.class);
    }

    public List<Category> getAll(){
        return getAll(Category.class);
    }

    public Category find(int id){
        return find(id, Category.class);
    }

}
