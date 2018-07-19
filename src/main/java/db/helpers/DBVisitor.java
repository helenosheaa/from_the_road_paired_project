package db.helpers;

import db.DBHelper;
import models.Article;
import models.Visitor;

import java.util.List;

public class DBVisitor extends DBHelper {

    public static void deleteAll(){
        deleteAll(Visitor.class);
    }

    public static List<Visitor> getAll(){
        return getAll(Visitor.class);
    }

    public static Visitor find(int id){
        return find(id, Visitor.class);
    }

    public static List<Article> getSavedArticlesForVisitor(Visitor visitor){
        return getAssociationsForAnObject(visitor, Article.class, "visitorSaves");
    }

}
