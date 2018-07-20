package controllers;

import db.helpers.DBArticle;
import db.helpers.DBCategory;
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

            Map<Article, List<Tag>> articleTags = DBArticle.getMapOfTagsForAritcles();
            model.put("articleTags", articleTags);

            Map<Article, List<Category>> articleCategories = DBArticle.getMapOfCategoriesForAritcles();

            model.put("articleCategories", articleCategories);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }

}
