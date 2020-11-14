import org.junit.Test;


import static org.junit.Assert.*;
public class lab5tests {

    Break[] breakArr = {new Break(new BasicTerm(9,30), 5),
            new Break(new BasicTerm(11,5), 10),
            new Break(new BasicTerm(12,45),5),
            new Break(new BasicTerm(14,20),20),
            new Break(new BasicTerm(16,10),5),
            new Break(new BasicTerm(17,45),5)};

    String[] arr = {"t+", "t+", "t-", "d+"};
    Action[] actArr = ActionParser.parse(arr);


    @Test
    public void testItimetable1() {
        ITimetable timetable1 = new Timetable1();

        Lesson l1 = new Lesson(timetable1,new Term(8,0,Day.TUE),"Angielski","Nowak",1);
        Lesson l2 = new Lesson(timetable1,new Term(9,30,Day.MON),"JTP","Kowalski",3);

        timetable1.put(l1);
        timetable1.put(l2);



        Action[] actArr = ActionParser.parse(arr);

        timetable1.perform(actArr);
        
        assertTrue(l1.getTerm().equals(new Term(8,0,Day.TUE)));
        assertTrue(l2.getTerm().equals(new Term(11,0,Day.TUE)));
    }

    @Test
    public void testItimetable2() {
        ITimetable timetable2 = new Timetable2(breakArr);

        Lesson l1 = new Lesson(timetable2,new Term(8,0,Day.TUE),"Angielski","Nowak",1);
        Lesson l2 = new Lesson(timetable2,new Term(9,35,Day.MON),"JTP","Kowalski",3);

        timetable2.put(l1);
        timetable2.put(l2);



        Action[] actArr = ActionParser.parse(arr);

        timetable2.perform(actArr);

        assertTrue(l1.getTerm().equals(new Term(8,0,Day.TUE)));
        assertTrue(l2.getTerm().equals(new Term(11,15,Day.TUE)));
    }


}
