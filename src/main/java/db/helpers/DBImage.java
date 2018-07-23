package db.helpers;

import db.DBHelper;
import models.Image;

import java.util.List;

public class DBImage extends DBHelper {

    public static Image find(int id){
        return find(id,Image.class);
    }

    public static List<Image> getAll(){
        return getAll(Image.class);
    }

    public static void deleteAll(){
        deleteAll(Image.class);
    }
}
