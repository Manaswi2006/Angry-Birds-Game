import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SimpleTest {

    @Test
    public void testAddition() {
        assertEquals("1 + 1 should equal 2", 1 + 1, 3);
    }
}
