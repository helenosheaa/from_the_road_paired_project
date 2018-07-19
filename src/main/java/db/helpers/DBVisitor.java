package db.helpers;

import db.DBHelper;
import db.HibernateUtil;
import models.Article;
import models.Visitor;
import models.Writer;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBVisitor extends DBHelper {

    public static void deleteAll(){
        deleteAll(Visitor.class);
    }

    public static List<Visitor> getAll(){
        return getAll(Visitor.class);
    }

    public static Visitor find(int id){
        return find(id, Visitor.class);
    }

    public static List<Article> getSavedArticlesForVisitor(Visitor visitor){
        return getAssociationsForAnObject(visitor, Article.class, "visitorSaves");
    }

    public static List<Writer> getFavouriteAuthorForVisitor(Visitor visitor){
        List<Article> vistorsSavedArticles = getSavedArticlesForVisitor(visitor);
        // Might need to create a collection of article IDs
        session = HibernateUtil.getSessionFactory().openSession();
        List<Writer> authors = null;

        try{
            Criteria writerCrieria = session.createCriteria(Writer.class);
            writerCrieria.createAlias("articles", "article");
            writerCrieria.add(Restrictions.in("article", vistorsSavedArticles));
            writerCrieria.setProjection(Projections.alias(Projections.count("name"), "author_freq"));
            writerCrieria.addOrder(Order.desc("author_freq"));
            authors = writerCrieria.list();
        }catch (Throwable e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return authors;
    }

}
