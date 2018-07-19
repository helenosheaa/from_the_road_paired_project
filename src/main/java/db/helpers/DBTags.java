package db.helpers;

import db.DBHelper;
import models.Tag;

import java.util.List;

public class DBTags extends DBHelper {

    public void deleteAll(){
        deleteAll(Tag.class);
    }

    public List<Tag> getAll(){
        return getAll(Tag.class);
    }

    public Tag find(int id){
        return find(id, Tag.class);
    }

}
