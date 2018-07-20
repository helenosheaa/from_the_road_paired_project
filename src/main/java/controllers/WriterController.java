package controllers;

import db.DBHelper;
import db.helpers.DBWriter;
import models.Article;
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

        post ("/admin/writers/:id/delete", (req, res) -> {

            int writerId = Integer.parseInt(req.params(":id"));

            Writer writer = DBWriter.find(writerId);

            DBHelper.delete(writer);

            res.redirect("/admin/writers");
            return null;
        }, new VelocityTemplateEngine());


    }
}
