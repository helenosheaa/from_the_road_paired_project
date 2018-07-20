package controllers;

import db.DBHelper;
import db.helpers.DBArticle;
import models.Article;
import models.Category;
import models.Tag;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class ArticleController {

    public ArticleController() {
        this.setupEndPoints();
    }

    public void setupEndPoints() {

        get("/articles", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitor/articleTemplates/index.vtl");

            List<Article> articles = DBArticle.getAll();
            model.put("articles", articles);

            Map<Integer, List<Tag>> articleTags = DBArticle.getMapOfTagsForArticles();
            model.put("articleTags", articleTags);

            Map<Integer, List<Category>> articleCategories = DBArticle.getMapOfCategoriesForArticles();

            model.put("articleCategories", articleCategories);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/article/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitor/articleTemplates/show.vtl");

            int articleId = Integer.parseInt(req.params(":id"));
            Article article = DBArticle.find(articleId);
            model.put("article", article);

            List<Tag> tags = DBArticle.getTagsForArticle(article);
            model.put("tags", tags);

            List<Category> categories = DBArticle.getCategoriesForArticle(article);
            model.put("categories", categories);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }

}
