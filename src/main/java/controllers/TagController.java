package controllers;

import db.DBHelper;
import db.helpers.DBCategory;
import db.helpers.DBTag;
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

public class TagController {

    public TagController(){
        this.setupEndPoints();
    }

    public void setupEndPoints(){

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

//      SHOW
        get("/tag/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitor/tagTemplates/show.vtl");
            List<Category> navBarCategories = DBCategory.getAll();
            model.put("navBarCategories", navBarCategories);

            int tagId = Integer.parseInt(req.params(":id"));
            Tag tag = DBTag.find(tagId);
            model.put("tag", tag);

            List<Article> articles = DBTag.getArticlesForTag(tag);
            model.put("articles", articles);

            return new ModelAndView(model, "templates/visitor_layout.vtl");
        }, velocityTemplateEngine);

//      ADMIN--------------------------------------------ADMIN-------------------------------------------ADMIN

//      INDEX
        get("/admin/tags", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/tagTemplates/index.vtl");

            List<Tag> tags = DBTag.getAll();
            model.put("tags", tags);

            Map<Integer, List<Article>> tagArticles = DBTag.getMapOfArticlesForTags();
            model.put("tagArticles", tagArticles);

            return new ModelAndView(model, "templates/admin_layout.vtl");
        }, velocityTemplateEngine);

//      NEW
        get("/admin/tag/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/tagTemplates/create.vtl");

            return new ModelAndView(model, "templates/admin_layout.vtl");
        }, velocityTemplateEngine);

//      CREATE
        post("/admin/tag", (req, res) -> {

            String name = req.queryParams("name");
            Tag tag = new Tag(name);
            DBTag.save(tag);

            res.redirect("/admin/tags");
            return null;
        }, velocityTemplateEngine);


//      SHOW
        get("/admin/tag/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/tagTemplates/show.vtl");

            int tagId = Integer.parseInt(req.params(":id"));
            Tag tag = DBTag.find(tagId);
            model.put("tag", tag);

            List<Article> articles = DBTag.getArticlesForTag(tag);
            model.put("articles", articles);

            return new ModelAndView(model, "templates/admin_layout.vtl");
        }, velocityTemplateEngine);

//      EDIT
        get("/admin/tag/:id/edit", (req, res) -> {
            int tagId = Integer.parseInt(req.params(":id"));

            Tag tag = DBTag.find(tagId);

            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/tagTemplates/edit.vtl");

            model.put("tag", tag);

            return new ModelAndView(model, "templates/admin_layout.vtl");

        }, velocityTemplateEngine);

//      UPDATE
        post("/admin/tag/update/:id", (req, res) -> {
            Tag tag = new Tag();

            tag.setId(Integer.parseInt(req.params(":id")));
            tag.setName(req.queryParams("name"));

            DBTag.update(tag);
            res.redirect("/admin/tags");
            return null;
        }, velocityTemplateEngine);


//      DELETE
        post ("/admin/tag/:id/delete", (req, res) -> {

            int tagId = Integer.parseInt(req.params(":id"));

            Tag tag = DBTag.find(tagId);

            DBHelper.delete(tag);

            res.redirect("/admin/tags");
            return null;
        }, velocityTemplateEngine);


    }
}
