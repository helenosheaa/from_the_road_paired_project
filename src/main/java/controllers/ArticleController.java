package controllers;


import db.DBHelper;
import db.helpers.DBArticle;
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

public class ArticleController {

    public ArticleController() {
        this.setupEndPoints();
    }

    public void setupEndPoints() {

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

//      INDEX
        get("/articles", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitor/articleTemplates/index.vtl");

            List<Article> articles = DBArticle.getAll();
            model.put("articles", articles);

            Map<Integer, List<Tag>> articleTags = DBArticle.getMapOfTagsForArticles();
            model.put("articleTags", articleTags);

            Map<Integer, List<Category>> articleCategories = DBArticle.getMapOfCategoriesForArticles();

            model.put("articleCategories", articleCategories);

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);

//      SHOW
        get("/article/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitor/articleTemplates/show.vtl");

            int articleId = Integer.parseInt(req.params(":id"));
            Article article = DBArticle.find(articleId);
            model.put("article", article);

            List<Tag> tags = DBArticle.getTagsForArticle(article);
            model.put("tags", tags);

            List<Category> categories = DBArticle.getCategoriesForArticle(article);
            model.put("categories", categories);

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);

//      ADMIN--------------------------------------------ADMIN-------------------------------------------ADMIN

//      INDEX
        get("/admin/articles", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/articleTemplates/index.vtl");

            List<Article> articles = DBArticle.getAll();
            model.put("articles", articles);

            Map<Integer, List<Tag>> articleTags = DBArticle.getMapOfTagsForArticles();
            model.put("articleTags", articleTags);

            Map<Integer, List<Category>> articleCategories = DBArticle.getMapOfCategoriesForArticles();
            model.put("articleCategories", articleCategories);

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);

//      CREATE
        get("/admin/article/new", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/articleTemplates/create.vtl");

            List<Category> categories = DBCategory.getAll();
            model.put("categories", categories);

            List<Tag> tags = DBTag.getAll();
            model.put("tags", tags);

            List<Writer> writers = DBWriter.getAll();
            model.put("writers", writers);

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);

//      SAVE
        post("/admin/article/save", (req, res) ->{

            String title = req.queryParams("title");

            int authorId = Integer.parseInt(req.queryParams("authorId"));
            Writer author = DBWriter.find(authorId);

            String content = req.queryParams("content");

            String summary = req.queryParams("summary");



            Article article = new Article(title, author, content, summary);
            article.addCategory(category);

            DBHelper.save(article);

            res.redirect("/admin/articles");
            return null;
        }, velocityTemplateEngine);


//      SHOW
        get("/admin/article/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/articleTemplates/show.vtl");

            int articleId = Integer.parseInt(req.params(":id"));
            Article article = DBArticle.find(articleId);
            model.put("article", article);

            List<Tag> tags = DBArticle.getTagsForArticle(article);
            model.put("tags", tags);

            List<Category> categories = DBArticle.getCategoriesForArticle(article);
            model.put("categories", categories);

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);

//      EDIT
        get("/article/edit/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/articleTemplates/edit.vtl");

            int articleId = Integer.parseInt(req.params(":id"));
            Article article = DBArticle.find(articleId);
            model.put("article", article);

            List<Category> articleCategories = DBArticle.getCategoriesForArticle(article);
            model.put("articleCategories", articleCategories);

            List<Tag> articleTags = DBArticle.getTagsForArticle(article);
            model.put("articleTags", articleTags);

            List<Category> categories = DBCategory.getAll();
            model.put("categories", categories);

            List<Tag> tags = DBTag.getAll();
            model.put("tags", tags);

            List<Writer> writers = DBWriter.getAll();
            model.put("writers", writers);

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);

//      UPDATE
        post("/article/update/:id", (req, res) ->{
            String title = req.queryParams("title");

            int authorId = Integer.parseInt(req.queryParams("authorId"));
            Writer author = DBWriter.find(authorId);

            String content = req.queryParams("content");

            String summary = req.queryParams("summary");

            int categoryId = Integer.parseInt(req.queryParams("categoryId"));
            Category category = DBCategory.find(categoryId);

            Article article = new Article(title, author, content, summary);
            article.addCategory(category);

            int articleId = Integer.parseInt(req.params(":id"));
            article.setId(articleId);

//            TODO: need to deal with existing tag and extra category relationships

            DBHelper.update(article);

            res.redirect("/admin/articles");
            return null;
        }, velocityTemplateEngine);
    }

}
