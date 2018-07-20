package controllers;

import db.helpers.DBCategory;
import models.Category;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class AdminController {

    public AdminController() {
        this.setupEndPoints();
    }

    public void setupEndPoints() {


    }
}
