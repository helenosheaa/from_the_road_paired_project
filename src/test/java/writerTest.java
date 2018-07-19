import models.Writer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class writerTest {

    private Writer writer;

    @Before
    public void before(){
        writer = new Writer("Helen");
    }

    @Test
    public void testGetName(){
        assertEquals("Helen", writer.getName());
    }
}
