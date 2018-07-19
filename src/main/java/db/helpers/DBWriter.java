package db.helpers;

import db.DBHelper;
import models.Writer;

import java.util.List;

public class DBWriter extends DBHelper {

    public void deleteAll(){
        deleteAll(Writer.class);
    }

    public List<Writer> getAll(){
        return getAll(Writer.class);
    }

    public Writer find(int id){
        return find(id, Writer.class);
    }

}
