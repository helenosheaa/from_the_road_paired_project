package db.helpers;

import db.DBHelper;
import models.Comment;

public abstract class DBComment extends DBHelper {

    public static void deleteAll(){
        deleteAll(Comment.class);
    }
}
