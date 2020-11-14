import org.junit.Test;

import static org.junit.Assert.*;

public class lab3Tests {
    Lesson lesson = new Lesson(new Term(9,35,Day.TUE),
            "Programowanie w języku Ruby","Stanisław Polak",
            2);
    @Test
    public void testToString() {
        assertEquals("Programowanie w języku Ruby (Wtorek 9:35-11:05)\n" +
                "2 rok studiow stacjonarnych\n" +
                "Prowadzacy: Stanisław Polak",
                lesson.toString(true));
    }

    @Test
    public void testEarlierDay() {
        boolean condition = lesson.isFullTime(lesson.getTerm());
        for (int i = 0 ; i < 7 ; i++){
            lesson.earlierDay();
            assertTrue(lesson.isFullTime(lesson.getTerm()) == condition);
        }

    }

    @Test
    public void testLaterDay() {
        boolean condition = lesson.isFullTime(lesson.getTerm());
        for (int i = 0 ; i < 7 ; i++){
            lesson.laterDay();
            assertTrue(lesson.isFullTime(lesson.getTerm()) == condition);
        }

    }

    @Test
    public void testEarlierTime() {
        BasicTerm[][] arr = lesson.getArr();
        int ind = lesson.getTerm().getDay().ordinal();
        int diff = 60 * (lesson.getTerm().getHour() - arr[ind][0].getHour()) +
                lesson.getTerm().getMinute() - arr[ind][0].getMinute();

        assertFalse(lesson.earlierTime(diff));
    }

    @Test
    public void testLaterTime() {
        BasicTerm[][] arr = lesson.getArr();
        int ind = lesson.getTerm().getDay().ordinal();
        int diff = 60 * (arr[ind][1].getHour() - lesson.getTerm().getHour()) +
                arr[ind][1].getMinute() - lesson.getTerm().getMinute();

        assertFalse(lesson.laterTime(diff));
    }

    @Test
    public void testActionParser() {
        String[] arr ={"t+","t+","d-","d-"};
        Action[] actArr = ActionParser.parse(arr);
        for(Action act : actArr) {
            switch (act){
                case DAY_LATER -> assertEquals(act.toString(), Action.DAY_LATER.toString());
                case DAY_EARLIER -> assertEquals(act.toString(), Action.DAY_EARLIER.toString());
                case TIME_LATER -> assertEquals(act.toString(), Action.TIME_LATER.toString());
                case TIME_EARLIER -> assertEquals(act.toString(), Action.TIME_EARLIER.toString());
                default -> assertFalse(true);
            }
        }
    }
}
