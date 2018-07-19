package Controllers;

import db.helpers.DBVisitor;
import db.helpers.DBWriter;
import models.Visitor;
import models.Writer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class VisitorController {

    public VisitorController (){
        this.setupEndPoints();
    }

    public void setupEndPoints(){

        get("/visitors", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitorTemplates/index.vtl");

            List<Visitor> visitors = DBVisitor.getAll();
            model.put("visitors", visitors);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


    }
}
