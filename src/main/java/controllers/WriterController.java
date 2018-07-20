package controllers;

import db.DBHelper;
import db.helpers.DBWriter;
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

        get("/writers", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitor/writerTemplates/index.vtl");

            List<Writer> writers = DBWriter.getAll();
            model.put("writers", writers);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/writers/:id/delete", (req, res) -> {

            int writerId = Integer.parseInt(req.params(":id"));

            Writer writer = DBWriter.find(writerId);

            DBHelper.delete(writer);

            res.redirect("/writers");
            return null;
        }, new VelocityTemplateEngine());


    }
}
