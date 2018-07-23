package controllers;

import db.DBHelper;
import db.helpers.DBArticle;
import models.Article;
import models.Comment;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.post;

public class CommentController {

    public CommentController(){
        this.setupEndPoints();
    }

    public void setupEndPoints(){

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

//      SAVE
        post("/comment/save/:id", (req, res) -> {

            String commentContent = req.queryParams("comment");
            String user = req.queryParams("user");
            int articleId = Integer.parseInt(req.params(":id"));
            Article article = DBArticle.find(articleId);

            Comment comment = new Comment(commentContent, user, article);
            DBHelper.save(comment);

            res.redirect("/article/" + articleId);
            return null;
        }, velocityTemplateEngine);

    }
}
