
public class DeanerySystem {
    public static void main(String[] args){


            BasicTerm term1 = new BasicTerm(9, 45);
            System.out.println(term1);                    //Ma się wypisać: "9:45 [90]"
            BasicTerm term2 = new BasicTerm(10, 15);
            System.out.println(term2);                    //Ma się wypisać: "10:15 [90]"
            System.out.println(term1.earlierThan(term2)); //Ma się wypisać: "true"
            System.out.println(term1.equals(term2));      //Ma się wypisać: "false"
            System.out.println(term1.endTerm(term2));
            System.out.println(term1.endTerm());          //Ma się wypisać: "11:15 [90]"

            Day day = Day.SUN;

            System.out.println(day.nextDay());


            Lesson lesson = new Lesson(new Term(16, 15, Day.THU), "Programowanie obiektowe", "Stanisław Polak", 2);
            System.out.println(lesson);

            lesson.earlierDay();

            System.out.println(lesson);

            lesson.earlierTime(40);

            System.out.println(lesson);

            String[] arr = {"t+", "d-", "d-"};
            Action[] actArr = ActionParser.parse(arr);
            int def = 90; //domyslnie o 90 minut?
            for (Action act : actArr) {
                switch (act) {
                    case DAY_LATER -> lesson.laterDay();
                    case DAY_EARLIER -> lesson.earlierDay();
                    case TIME_LATER -> lesson.laterTime(def);
                    case TIME_EARLIER -> lesson.earlierTime(def);

                }
            }

            System.out.println(lesson);


            ITimetable timetable = new Timetable1();
            Lesson l1 = new Lesson(timetable, new Term(8, 0, Day.TUE), "Angielski", "Nowak", 1);
            Lesson l2 = new Lesson(timetable, new Term(9, 30, Day.MON), "JTP", "Kowalski", 3);

            timetable.put(l1);
            timetable.put(l2);

            System.out.println(timetable);

            String[] arr1 = {"t+", "t+", "t-", "d+"};
            Action[] actArr1 = ActionParser.parse(arr1);

            timetable.perform(actArr1);

            System.out.println(timetable);


            System.out.println("222222222222222222222");

            Break breakArr[] = {new Break(new BasicTerm(9, 30), 5),
                    new Break(new BasicTerm(11, 5), 10),
                    new Break(new BasicTerm(12, 45), 5),
                    new Break(new BasicTerm(14, 20), 20),
                    new Break(new BasicTerm(16, 10), 5),
                    new Break(new BasicTerm(17, 45), 5)};


            ITimetable timetable2 = new Timetable2(breakArr);

            Lesson l11 = new Lesson(timetable2, new Term(8, 0, Day.TUE), "Angielski", "Nowak", 1);
            Lesson l22 = new Lesson(timetable2, new Term(9, 35, Day.MON), "JTP", "Kowalski", 3);

            timetable2.put(l11);
            timetable2.put(l22);

            System.out.println(timetable2);

//        String[] arr1 = {"t+", "t+", "t-", "d+"};
            String[] arr2 = {"t-"};
            Action[] actArr2 = ActionParser.parse(arr2);

            timetable2.perform(actArr2);


            System.out.println(timetable2);


            // Observer
            Subject subject = new SubjectLesson();

            LessonObserver observer = new LessonObserver(subject);
            subject.setState(l1);
            subject.setState(l2);



        try {
            Lesson l33 = new Lesson(timetable2, new Term(8, 0, Day.TUE), "Angielski", "Nowak", 1);


            timetable2.put(l33);



        }

        catch (Exception e){
            System.out.println(e);
        }

    }

    
}

