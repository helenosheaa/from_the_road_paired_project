package controllers;

import db.helpers.DBArticle;
import models.Article;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class AdminController {

    public AdminController() {
        this.setupEndPoints();
    }

    public void setupEndPoints() {

        get("/admin", (req, res) -> {
            Map<String, Object> model= new HashMap();
            model.put("template", "templates/admin/adminHomeTemplate/index.vtl");

            List<Article> articles = DBArticle.getArticlesByDate();
            model.put("articles", articles);

            return new ModelAndView(model, "templates/admin_layout.vtl");
        }, new VelocityTemplateEngine());


    }
}
