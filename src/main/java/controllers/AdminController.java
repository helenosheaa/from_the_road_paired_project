package controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
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

            return new ModelAndView(model, "templates/admin_layout.vtl");
        }, new VelocityTemplateEngine());


    }
}
