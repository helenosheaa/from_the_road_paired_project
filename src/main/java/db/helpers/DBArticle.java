package db.helpers;

import db.DBHelper;
import models.Article;
import models.Category;
import models.Tag;
import models.Visitor;

import java.util.List;

public class DBArticle extends DBHelper {

    public static void deleteAll(){
        deleteAll(Article.class);
    }

    public static List<Article> getAll(){
        return getAll(Article.class);
    }

    public static Article find(int id){
        return find(id, Article.class);
    }

    public static List<Tag> getTagsForArticle(Article article){
        return getAssociationsForAnObject(article, Tag.class, "articles");
    }

    public static void removeTagFromArticle(Article article, Tag tag){
        article.removeTag(tag);
        update(article);
    }

    public static List<Category> getCategoriesForArticle(Article article){
        return getAssociationsForAnObject(article, Category.class, "articles");
    }

    public static void removeCategoryFromArticle(Article article, Category category){
        article.removeCategory(category);
        update(article);
    }

    public static List<Visitor> getVisitorsWhoHaveSavedAnArticle(Article article){
        return getAssociationsForAnObject(article, Visitor.class, "savedArticles");
    }

    public static List<Article> getArticlesByDate(){
        return orderByCriterion("date", Article.class, false);
    }

    public static List<Article> getArticlesByVisit(){
        return orderByCriterion("visitCounter", Article.class, false);
    }

}
