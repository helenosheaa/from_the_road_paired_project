import models.Tag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TagTest {

    private Tag tag;

    @Before
    public void before(){
        tag = new Tag("food");
    }

    @Test
    public void testGetName(){
        assertEquals("food", tag.getName());
    }
}
