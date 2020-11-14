
import java.util.ArrayList;
import java.util.List;

public class Timetable1 extends AbstractTimetable {

    @Override
    public boolean canBeTransferredTo(Term term, boolean full_time) {

        if (term.isFullTime() == full_time){
            return !this.busy(term);
        }
        return false;

    }

//    @Override
//    public boolean busy(Term term) {
//        for (Lesson l : list){
//            if (term.getDay() == l.getTerm().getDay())
//                if ((l.getTerm().earlierThan(term.endTerm()) &&
//                        l.getTerm().endTerm().laterThan(term)))
//                    return true;
//        }
//        return false;
//    }


    @Override
    public void perform(Action[] actions) {
        int def = 90;

        int i = 0;


        ArrayList<Lesson> lessonList = new ArrayList<>(hashMap.values());
        int l = lessonList.size();
        for(Action act : actions){
            Lesson lesson = lessonList.get(i);
            Term oldKey = lesson.getTerm();
            switch (act){
                case DAY_LATER -> lesson.laterDay();
                case DAY_EARLIER -> lesson.earlierDay();
                case TIME_LATER -> lesson.laterTime(def);
                case TIME_EARLIER -> lesson.earlierTime(def);

            }
            hashMap.put(lesson.getTerm(), hashMap.remove(oldKey));
            i+=1;
            if (i >= l)
                i = 0;
        }

    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Day lastDay = Day.SUN;
        Term lastTerm = new Term(20,0,lastDay);
        Day day;
        Term term;
        for(int i = 0 ; i < 7 ; i+=1){
            day = Day.values()[i];


//            System.out.println(day);

            res.append(day.toString()).append("\n");
            for(term = new Term(8,0,day) ; term.earlierThan(lastTerm) ; term = term.endTerm()){
//                System.out.print(term);
                res.append(term.toString());
                if(this.busy(term)) {
                    res.append(" ").append(this.get(term).toString());
//                    System.out.println(" " + this.get(term).toString(true));
                }
                else {
//                    System.out.println(" ");
                    res.append(" ");
                }

                res.append("\n");
            }
        }
        return res.toString();
    }
}
