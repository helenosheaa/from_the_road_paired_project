package controllers;

import db.DBHelper;
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
import static spark.Spark.post;

public class CategoryController {

    public CategoryController (){
        this.setupEndPoints();
    }

    public void setupEndPoints(){

        get("/category/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitor/categoryTemplates/show.vtl");

            int categoryId = Integer.parseInt(req.params(":id"));

            Category category = DBCategory.find(categoryId);
            model.put("category", category);

            List<Article> articles = DBCategory.getArticlesForCategory(category);
            model.put("articles", articles);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post ("/categories/:id/delete", (req, res) -> {

            int categoryId = Integer.parseInt(req.params(":id"));

            Category category = DBCategory.find(categoryId);

            DBHelper.delete(category);

            res.redirect("/categories");
            return null;
        }, new VelocityTemplateEngine());

    }
}
