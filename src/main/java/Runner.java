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

        Article article = new Article("Travelling", writer, "content", "summary" );
        DBHelper.save(article);

        article.addTag(tag);
        article.addCategory(category);

        DBHelper.update(article);

        List<Article> foundArticles = DBWriter.getArticlesForWriter(writer);

        Article foundArticle = DBArticle.find(article.getId());
        List<Tag> foundTags = DBArticle.getTagsForArticle(foundArticle);
        List<Category> foundCategories = DBArticle.getCategoriesForArticle(foundArticle);
        List<Article> foundCategoryArticles = DBCategory.getArticlesForCategory(category);
        List<Article> foundTagArticles = DBTags.getArticlesForTag(tag);

        System.exit(0);
    }
}
