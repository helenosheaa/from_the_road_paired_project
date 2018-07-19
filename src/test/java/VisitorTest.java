import models.Visitor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VisitorTest {

    private Visitor visitor;

    @Before
    public void before(){
        visitor = new Visitor("Stuart");
    }

    @Test
    public void testGetName(){
        assertEquals("Stuart", visitor.getName());
    }
}
