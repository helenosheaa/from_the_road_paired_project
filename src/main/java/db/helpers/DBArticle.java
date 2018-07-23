package db.helpers;

import db.DBHelper;
import models.*;

import java.util.List;
import java.util.Map;

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

    public static void removeTagFromArticle(Article article, Tag removeTag){
        List<Tag> tags = DBArticle.getTagsForArticle(article);
        for(Tag tag : tags){
            if(tag.getId() == removeTag.getId()){
                removeTag = tag;
            }
        }
        tags.remove(removeTag);
        article.setTags(tags);
        update(article);
    }

    public static List<Category> getCategoriesForArticle(Article article){
        return getAssociationsForAnObject(article, Category.class, "articles");
    }

    public static void removeCategoryFromArticle(Article article, Category removeCategory){
        List<Category> categories = DBArticle.getCategoriesForArticle(article);
        for(Category category : categories){
            if(category.getId() == removeCategory.getId()){
                removeCategory = category;
            }
        }
        categories.remove(removeCategory);
        article.setCategories(categories);
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

    public static Writer getWriterForArticle(Article article){
        return getAnAssociationForAnObject(article, Writer.class, "articles");
    }

    public static Map<Integer, List<Tag>> getMapOfTagsForArticles(){
        return getMapOfAssociationsForObjects(Article.class, Tag.class, "articles");
    }

    public static Map<Integer, List<Category>> getMapOfCategoriesForArticles(){
        return getMapOfAssociationsForObjects(Article.class, Category.class, "articles");
    }

    public static List<Article> searchByTitle(String searchTerm){
        return searchForAll(searchTerm, Article.class, "title");
    }

}
