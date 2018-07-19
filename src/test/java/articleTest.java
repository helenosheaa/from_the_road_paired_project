import models.Article;
import models.Writer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class articleTest {

    private Article article;
    private Writer writer;


    @Before
    public void before(){
        writer = new Writer("Helen");
        article = new Article("Travelling",writer, "content", "summary" );
    }

    @Test
    public void testGetName(){
        assertEquals("Travelling", article.getTitle());
    }

    @Test
    public void testGetAuthor(){
        assertEquals(writer, article.getAuthor());
    }

    @Test
    public void testGetContent(){
        assertEquals("content", article.getContent());
    }

    @Test
    public void testGetSummary(){
        assertEquals("summary", article.getSummary());
    }

}
