package db.helpers;

import db.DBHelper;
import models.Article;

import java.util.List;

public class DBArticle extends DBHelper {

    public void deleteAll(){
        deleteAll(Article.class);
    }

    public List<Article> getAll(){
        return getAll(Article.class);
    }

    public Article find(int id){
        return find(id, Article.class);
    }
}
