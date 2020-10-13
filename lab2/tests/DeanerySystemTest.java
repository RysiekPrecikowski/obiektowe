import org.junit.Test;

import static org.junit.Assert.*;

public class DeanerySystemTest {

    @Test
    public void main() {
        Term term = new Term(9,15);
        assertEquals("9:15 [90]", term.toString());

    }
}