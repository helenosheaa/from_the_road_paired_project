package controllers;

import db.DBHelper;
import db.helpers.DBCategory;
import db.helpers.DBTag;
import db.helpers.DBWriter;
import models.Article;
import models.Category;
import models.Tag;
import models.Writer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WriterController {

    public WriterController (){
        this.setupEndPoints();
    }

    public void setupEndPoints(){

//      SHOW
        get("/writer/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitor/writerTemplates/show.vtl");

            int writerId = Integer.parseInt(req.params(":id"));
            Writer writer = DBWriter.find(writerId);
            model.put("writer", writer);

            List<Article> articles = DBWriter.getArticlesForWriter(writer);
            model.put("articles", articles);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

//      ADMIN--------------------------------------------ADMIN-------------------------------------------ADMIN

//      INDEX
        get("admin/writers", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/writerTemplates/index.vtl");

            List<Writer> writers = DBWriter.getAll();
            model.put("writers", writers);

            Map<Integer, List<Article>> writerArticles = DBWriter.getMapOfArticlesForWriters();
            model.put("writerArticles", writerArticles);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

//      NEW
        get("/admin/writer/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/writerTemplates/create.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

//      CREATE
        post("/admin/writer", (req, res) -> {

            String name = req.queryParams("name");
            String blurb = req.queryParams("blurb");
            Writer writer = new Writer(name, blurb);
            DBWriter.save(writer);

            res.redirect("/admin/writers");
            return null;
        }, new VelocityTemplateEngine());


//      SHOW
        get("/admin/writer/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/writerTemplates/show.vtl");

            int writerId = Integer.parseInt(req.params(":id"));
            Writer writer = DBWriter.find(writerId);
            model.put("writer", writer);

            List<Article> articles = DBWriter.getArticlesForWriter(writer);
            model.put("articles", articles);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //      EDIT
        get("/admin/writer/:id/edit", (req, res) -> {
            int writerId = Integer.parseInt(req.params(":id"));

            Writer writer = DBWriter.find(writerId);

            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/writerTemplates/edit.vtl");

            model.put("writer", writer);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

//      UPDATE
        post("/admin/writer/update/:id", (req, res) -> {
            Writer writer = new Writer();

            writer.setId(Integer.parseInt(req.params(":id")));
            writer.setName(req.queryParams("name"));
            writer.setBlurb(req.queryParams("blurb"));

            DBWriter.update(writer);
            res.redirect("/admin/writers");
            return null;
        }, new VelocityTemplateEngine());

//      DELETE
        post ("/admin/writer/:id/delete", (req, res) -> {

            int writerId = Integer.parseInt(req.params(":id"));

            Writer writer = DBWriter.find(writerId);

            DBHelper.delete(writer);

            res.redirect("/admin/writers");
            return null;
        }, new VelocityTemplateEngine());


    }
}
