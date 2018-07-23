package controllers;

import db.DBHelper;
import db.helpers.DBCategory;
import models.Article;
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


        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();


//      SHOW
        get("/category/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitor/categoryTemplates/show.vtl");

            int categoryId = Integer.parseInt(req.params(":id"));
            Category category = DBCategory.find(categoryId);
            model.put("category", category);

            List<Article> articles = DBCategory.getArticlesForCategory(category);
            model.put("articles", articles);

            return new ModelAndView(model, "templates/visitor_layout.vtl");
            },velocityTemplateEngine);

//      ADMIN--------------------------------------------ADMIN-------------------------------------------ADMIN


//      INDEX
        get("/admin/categories", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/categoryTemplates/index.vtl");

            List<Category> categories = DBCategory.getAll();
            model.put("categories", categories);

            Map<Integer, List<Article>> categoryArticles = DBCategory.getMapOfArticlesForCategories();
            model.put("categoryArticles", categoryArticles);

            return new ModelAndView(model, "templates/admin_layout.vtl");
        }, velocityTemplateEngine);

//      NEW
        get("/admin/category/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/categoryTemplates/create.vtl");

            return new ModelAndView(model, "templates/admin_layout.vtl");
        }, velocityTemplateEngine);

//      CREATE
        post("/admin/category", (req, res) -> {

            String name = req.queryParams("name");
            Category category = new Category(name);
            DBCategory.save(category);

            res.redirect("/admin/categories");
            return null;
    }, velocityTemplateEngine);

//      SHOW
        get("/admin/category/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/categoryTemplates/show.vtl");

            int categoryId = Integer.parseInt(req.params(":id"));
            Category category = DBCategory.find(categoryId);
            model.put("category", category);

            List<Article> articles = DBCategory.getArticlesForCategory(category);
            model.put("articles", articles);

            return new ModelAndView(model, "templates/admin_layout.vtl");
        }, velocityTemplateEngine);

//      EDIT
        get("/admin/category/:id/edit", (req, res) -> {
            int categoryId = Integer.parseInt(req.params(":id"));

            Category category = DBCategory.find(categoryId);

            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/categoryTemplates/edit.vtl");

            model.put("category", category);

            return new ModelAndView(model, "templates/admin_layout.vtl");

        }, velocityTemplateEngine);

//      UPDATE
        post("/admin/category/update/:id", (req, res) -> {
            Category category = new Category();

            category.setId(Integer.parseInt(req.params(":id")));
            category.setName(req.queryParams("name"));

            DBCategory.update(category);
            res.redirect("/admin/categories");
            return null;
        }, velocityTemplateEngine);


//      DELETE
        post ("/admin/category/:id/delete", (req, res) -> {

            int categoryId = Integer.parseInt(req.params(":id"));

            Category category = DBCategory.find(categoryId);

            DBHelper.delete(category);

            res.redirect("/admin/categories");
            return null;
        }, new VelocityTemplateEngine());

    }
}
