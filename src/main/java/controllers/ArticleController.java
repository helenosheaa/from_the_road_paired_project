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
import tools.SparkDataHandler;

import java.util.ArrayList;
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
        get("/articles/:page", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/visitor/articleTemplates/index.vtl");

            List<Article> articles = DBArticle.getArticlesByDate();
            model.put("articles", articles);

            int numberOnAPage = 3;
            Map<Integer, Map<String, Integer>> pages = SparkDataHandler.getPagesForList(articles, numberOnAPage);

            int pageNumber = Integer.parseInt(req.params(":page"));
            Map<String, Integer> page = pages.get(pageNumber);
            int start = page.get("start");
            int end = page.get("end");

            boolean isntStartPage = !(pageNumber == 1);
            boolean isntEndPage = !(pageNumber == pages.size());

            if(isntStartPage){
                int previousPage = pageNumber - 1;
                model.put("previousPage", previousPage);
            }

            if(isntEndPage){
                int nextPage = pageNumber + 1;
                model.put("nextPage", nextPage);
            }

            model.put("isntStartPage", isntStartPage);
            model.put("isntEndPage", isntEndPage);
            model.put("pages", pages);
            model.put("start", start);
            model.put("end", end);

            Map<Integer, List<Tag>> articleTags = DBArticle.getMapOfTagsForArticles();
            model.put("articleTags", articleTags);

            Map<Integer, List<Category>> articleCategories = DBArticle.getMapOfCategoriesForArticles();

            model.put("articleCategories", articleCategories);

            return new ModelAndView(model, "templates/visitor_layout.vtl");
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

            return new ModelAndView(model, "templates/visitor_layout.vtl");
        }, velocityTemplateEngine);

//      CLAP BUTTON

        post("/article/clapped/:id", (req, res) ->{
            int articleId = Integer.parseInt(req.params(":id"));
            Article article = DBArticle.find(articleId);

            article.indexVisitCounter();
            DBArticle.update(article);

            res.redirect("/article/" + articleId);
            return null;
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

            return new ModelAndView(model, "templates/admin_layout.vtl");
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

            return new ModelAndView(model, "templates/admin_layout.vtl");
        }, velocityTemplateEngine);

//      SAVE
        post("/admin/article/save", (req, res) ->{

            String title = req.queryParams("title");

            int authorId = Integer.parseInt(req.queryParams("authorId"));
            Writer author = DBWriter.find(authorId);

            String content = req.queryParams("content");

            String summary = req.queryParams("summary");

            Article article = new Article(title, author, content, summary);

            try {
                String[] categoryIds = req.queryMap("categoryIds").values();
                List<Category> categories = DBCategory.findCategoriesInList(categoryIds);
                article.setCategories(categories);
            }catch (NullPointerException e){
                res.redirect("/admin/article/new");
            }

            try {
                String[] tagIds = req.queryMap("tagIds").values();
                List<Tag> tags = DBTag.findTagsInList(tagIds);
                article.setTags(tags);
            }catch (NullPointerException e){}

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

            return new ModelAndView(model, "templates/admin_layout.vtl");
        }, velocityTemplateEngine);

//      EDIT
        get("/admin/article/edit/:id", (req, res) -> {
            Map<String, Object> model = new HashMap();
            model.put("template", "templates/admin/articleTemplates/edit.vtl");

            int articleId = Integer.parseInt(req.params(":id"));
            Article article = DBArticle.find(articleId);
            model.put("article", article);

            List<Category> articleCategories = DBArticle.getCategoriesForArticle(article);
            List<Integer> articleCategoryIds = new ArrayList<>();
            for (Category articleCategory : articleCategories ){
                articleCategoryIds.add(articleCategory.getId());
            }
            model.put("articleCategoryIds", articleCategoryIds);

            List<Tag> articleTags = DBArticle.getTagsForArticle(article);
            List<Integer> articleTagIds = new ArrayList<>();
            for (Tag articleTag : articleTags ){
                articleTagIds.add(articleTag.getId());
            }
            model.put("articleTagIds", articleTagIds);

            List<Category> categories = DBCategory.getAll();
            model.put("categories", categories);

            List<Tag> tags = DBTag.getAll();
            model.put("tags", tags);

            List<Writer> writers = DBWriter.getAll();
            model.put("writers", writers);

            return new ModelAndView(model, "templates/admin_layout.vtl");
        }, velocityTemplateEngine);

//      UPDATE
        post("/admin/article/update/:id", (req, res) ->{
            String title = req.queryParams("title");

            int authorId = Integer.parseInt(req.queryParams("authorId"));
            Writer author = DBWriter.find(authorId);

            String content = req.queryParams("content");

            String summary = req.queryParams("summary");

            Article article = new Article(title, author, content, summary);

            int articleId = Integer.parseInt(req.params(":id"));
            article.setId(articleId);

            try {
                String[] categoryIds = req.queryMap("categoryIds").values();
                List<Category> categories = DBCategory.findCategoriesInList(categoryIds);
                article.setCategories(categories);
            }catch (NullPointerException e){
                res.redirect("/admin/article/edit/" + articleId);
            }

            try {
                String[] tagIds = req.queryMap("tagIds").values();
                List<Tag> tags = DBTag.findTagsInList(tagIds);
                article.setTags(tags);
            }catch (NullPointerException e){}

            DBHelper.update(article);

            res.redirect("/admin/articles");
            return null;
        }, velocityTemplateEngine);

//      DELETE
        post ("/admin/article/delete/:id", (req, res) -> {

            int articleId = Integer.parseInt(req.params(":id"));

            Article article = DBArticle.find(articleId);

            DBHelper.delete(article);

            res.redirect("/admin/articles");
            return null;
        }, velocityTemplateEngine);
    }

}
