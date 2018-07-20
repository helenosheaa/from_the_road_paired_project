import models.Writer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WriterTest {

    private Writer writer;

    @Before
    public void before(){
        writer = new Writer("Helen", "blurb");
    }

    @Test
    public void testGetName(){
        assertEquals("Helen", writer.getName());
    }
}
