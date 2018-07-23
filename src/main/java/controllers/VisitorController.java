package controllers;

import db.DBHelper;
import db.helpers.DBCategory;
import db.helpers.DBVisitor;
import models.Category;
import models.Visitor;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class VisitorController {

    public VisitorController (){
        this.setupEndPoints();
    }

    public void setupEndPoints(){

        get("/visitors", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitorTemplates/index.vtl");
            List<Category> navBarCategories = DBCategory.getAll();
            model.put("navBarCategories", navBarCategories);

            List<Visitor> visitors = DBVisitor.getAll();
            model.put("visitors", visitors);

            return new ModelAndView(model, "templates/visitor_layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/visitors/:id/delete", (req, res) -> {

            int visitorId = Integer.parseInt(req.params(":id"));

            Visitor visitor = DBVisitor.find(visitorId);

            DBHelper.delete(visitor);

            res.redirect("/visitors");
            return null;
        }, new VelocityTemplateEngine());



    }
}
