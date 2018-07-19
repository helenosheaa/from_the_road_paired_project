package db.helpers;

import db.DBHelper;
import models.Article;
import models.Category;

import java.util.List;

public class DBCategory extends DBHelper {

    public static void deleteAll(){
        deleteAll(Category.class);
    }

    public static List<Category> getAll(){
        return getAll(Category.class);
    }

    public static Category find(int id){
        return find(id, Category.class);
    }

    public static List<Article> getArticlesForCategory(Category category){
        return getAssociationsForAnObject(category, Article.class, "categories");
    }

}
