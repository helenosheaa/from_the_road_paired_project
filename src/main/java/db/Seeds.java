package db;

import db.helpers.*;
import models.*;

public class Seeds {

    public static void seedData(){
        DBWriter.deleteAll();
        DBArticle.deleteAll();
        DBCategory.deleteAll();
        DBTag.deleteAll();
        DBVisitor.deleteAll();

        Category category = new Category("America");
        DBHelper.save(category);

        Tag tag = new Tag("food");
        DBHelper.save(tag);

        Visitor visitor = new Visitor("Stuart");
        DBHelper.save(visitor);

        Writer writer1 = new Writer("Helen", "blurb");
        DBHelper.save(writer1);

        Writer writer2 = new Writer("Ron", "blurb");
        DBHelper.save(writer2);

        Article article1 = new Article("Travelling", writer1, "content", "summary" );
        DBHelper.save(article1);

        Article article2 = new Article("Wandering", writer2, "content", "summary" );
        DBHelper.save(article2);

        Article article3 = new Article("Road Trip", writer1, "content", "summary" );
        DBHelper.save(article3);

        article1.addTag(tag);
        article1.addCategory(category);

        DBHelper.update(article1);

        DBVisitor.saveArticleForVisitor(visitor, article1);

    }


}
