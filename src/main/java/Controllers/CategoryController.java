package Controllers;

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


    }
}
