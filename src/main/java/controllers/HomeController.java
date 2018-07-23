package controllers;

import db.Seeds;
import db.helpers.DBArticle;
import db.helpers.DBCategory;
import models.Article;
import models.Category;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class HomeController {

    public static void main(String[] args) {

        staticFileLocation("/public");

        Seeds.seedData();

        AdminController adminController = new AdminController();
        ArticleController articleController = new ArticleController();
        CategoryController categoryController = new CategoryController();
        TagController tagController = new TagController();
        VisitorController visitorController = new VisitorController();
        WriterController writerController = new WriterController();


        get("/", (req, res) -> {
            Map<String, Object> model= new HashMap();
            model.put("template", "templates/visitor/visitorHomeTemplate/index.vtl");

            List<Category> navBarCategories = DBCategory.getAll();
            model.put("navBarCategories", navBarCategories);

            List<Article> topThreeArticles = DBArticle.getArticlesByVisit().subList(0, 3);
            model.put("topThreeArticles", topThreeArticles);

            return new ModelAndView(model, "templates/visitor_layout.vtl");
        }, new VelocityTemplateEngine());


    }
}
