import org.junit.Test;

import static org.junit.Assert.*;

public class DeanerySystemTest {
    Term term = new Term(9,45);
    @Test
    public void testToString() {

        assertEquals("9:45 [90]", term.toString());

    }

    @Test
    public void testNextDay(){
        assertEquals("Wtorek", Day.MON.nextDay().toString());
        assertEquals("Środa", Day.TUE.nextDay().toString());
        assertEquals("Czwartek", Day.WED.nextDay().toString());
        assertEquals("Piątek", Day.THU.nextDay().toString());
        assertEquals("Sobota", Day.FRI.nextDay().toString());
        assertEquals("Niedziela", Day.SAT.nextDay().toString());
        assertEquals("Poniedziałek", Day.SUN.nextDay().toString());


    }

    @Test
    public void testPrevDay(){
        assertEquals("Niedziela", Day.MON.prevDay().toString());
        assertEquals("Poniedziałek", Day.TUE.prevDay().toString());
        assertEquals("Wtorek", Day.WED.prevDay().toString());
        assertEquals("Środa", Day.THU.prevDay().toString());
        assertEquals("Czwartek", Day.FRI.prevDay().toString());
        assertEquals("Piątek", Day.SAT.prevDay().toString());
        assertEquals("Sobota", Day.SUN.prevDay().toString());


    }

    @Test
    public void testEarlierThan() {
        Term term2 = new Term(10,15);
        Term term3 = new Term(9,15);

        assertTrue(term.earlierThan(term2));

        assertFalse(term.earlierThan(term));

        assertFalse(term.earlierThan(term3));
    }

    @Test
    public void testLaterThan() {
        Term term2 = new Term(10,15);
        Term term3 = new Term(9,15);

        assertFalse(term.laterThan(term2));

        assertFalse(term.laterThan(term));

        assertTrue(term.laterThan(term3));
    }

    @Test
    public void testEndTermAnotherObject() {
        Term term2 = new Term(10,15);
        assertEquals("9:45 [30]", term.endTerm(term2).toString());
    }

    @Test
    public void testEndTerm() {
        assertEquals("11:15 [90]", term.endTerm().toString());
    }


}