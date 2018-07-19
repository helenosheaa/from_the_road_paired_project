import db.DBHelper;
import db.helpers.*;
import models.*;

import java.util.List;

public class Runner {

    public static void main(String[] args) {

        DBWriter.deleteAll();
        DBArticle.deleteAll();
        DBCategory.deleteAll();
        DBTags.deleteAll();
        DBVisitor.deleteAll();

        Category category = new Category("America");
        DBHelper.save(category);

        Tag tag = new Tag("food");
        DBHelper.save(tag);

        Visitor visitor = new Visitor("Stuart");
        DBHelper.save(visitor);

        Writer writer = new Writer("Helen");
        DBHelper.save(writer);

        Article article1 = new Article("Travelling", writer, "content", "summary" );
        DBHelper.save(article1);

        Article article2 = new Article("Travelling", writer, "content", "summary" );
        DBHelper.save(article1);

        article1.addTag(tag);
        article1.addCategory(category);

        DBHelper.update(article1);

        List<Article> foundArticles = DBWriter.getArticlesForWriter(writer);

        Article foundArticle = DBArticle.find(article1.getId());
        Writer articleWriter = DBArticle.getWriterForArticle(foundArticle);

//        List<Tag> foundTags = DBArticle.getTagsForArticle(foundArticle);
//        List<Category> foundCategories = DBArticle.getCategoriesForArticle(foundArticle);
        List<Article> foundCategoryArticles = DBCategory.getArticlesForCategory(category);
        List<Article> foundTagArticles = DBTags.getArticlesForTag(tag);

        DBArticle.removeCategoryFromArticle(foundArticle, category);
        DBArticle.removeTagFromArticle(foundArticle, tag);

        foundArticle = DBArticle.find(article1.getId());

        List<Category> foundCategories = DBArticle.getCategoriesForArticle(foundArticle);
        List<Tag> foundTags = DBArticle.getTagsForArticle(foundArticle);


        System.exit(0);
    }
}
