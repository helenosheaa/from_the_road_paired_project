package db.helpers;

import db.DBHelper;
import models.Article;
import models.Tag;

import java.util.List;
import java.util.Map;

public class DBTag extends DBHelper {

    public static void deleteAll(){
        deleteAll(Tag.class);
    }

    public static List<Tag> getAll(){
        return getAll(Tag.class);
    }

    public static Tag find(int id){
        return find(id, Tag.class);
    }

    public static List<Article> getArticlesForTag(Tag tag){
        return getAssociationsForAnObject(tag, Article.class, "tags");
    }

    public static Map<Integer, List<Article>> getMapOfArticlesForTags(){
        return getMapOfAssociationsForObjects(Tag.class, Article.class, "tags");
    }

    public static List<Tag> findTagsInList(String[] arrayOfIds){
        return findAllObjectsInList(arrayOfIds, Tag.class);
    }

    public static List<Tag> searchByName(String searchTerm){
        return searchForAll(searchTerm, Tag.class, "name");
    }
}
