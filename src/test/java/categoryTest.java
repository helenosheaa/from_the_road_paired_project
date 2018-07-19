import models.Category;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class categoryTest {

    private Category category;

    @Before
    public void before(){
        category = new Category("America");
    }

    @Test
    public void testGetName(){
        assertEquals("America", category.getName());
    }
}
