package controllers;

import db.DBHelper;
import db.helpers.DBCategory;
import models.Category;
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

        get("/categories", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/categoryTemplates/index.vtl");

            List<Category> categories = DBCategory.getAll();
            model.put("categories", categories);

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
