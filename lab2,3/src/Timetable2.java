import java.util.ArrayList;
import java.util.List;

public class Timetable2 extends AbstractTimetable{
    static Break[] breaks;
    public static boolean skipBreaks = true;



    public Timetable2(Break [] breaks) {
        Timetable2.breaks = breaks;


        // dodawanie przerw do listy abstracttimetableitem
//        for (Break b : breaks)
//            this.testPutBreak(b);
    }

    @Override
    public boolean canBeTransferredTo(Term term, boolean full_time) {
        if (term.isFullTime() == full_time){
            if (!this.busy(term)){

                int bInd = 0;
                for(BasicTerm tmpTerm = new BasicTerm(8,0) ; tmpTerm.endTerm().earlierThan(new BasicTerm(20,0)) ; ){

                    if (term.equals(tmpTerm)) {
                        return true;
                    }

                    tmpTerm = tmpTerm.endTerm();
                    if (bInd < breaks.length) {
                        if (tmpTerm.getHour() == breaks[bInd].getTerm().getHour() &&
                                tmpTerm.getMinute() == breaks[bInd].getTerm().getMinute()) {

                            tmpTerm = tmpTerm.moveTerm(breaks[bInd].getTerm().getDuration());

                            bInd += 1;
                        }
                    }
                }
            }
        }
        return false;

    }

//    public List<AbstractTimetableItem> testList = new ArrayList<>();


    //metody test do dodawania do listy abstractTimetableItem

//    public boolean testPutLesson(Lesson item){
//        if (!busy(item.getTerm())){
//            if (canBeTransferredTo(item.getTerm(), item.isFullTime()))
//                testList.add(item);
//            return true;
//        }
//
//        return false;
//    }
//
//    public boolean testPutBreak(Break item){
//        for (AbstractTimetableItem i : testList){
//            if (i.equals(item))
//                return false;
//        }
//
//        testList.add(item);
//        return true;
//    }




//    public boolean isBreak(BasicTerm term){
//        for (Break b : breaks){
//
//            if ((b.getTerm().earlierThan(term.endTerm()) &&
//                    b.getTerm().endTerm().laterThan(term)))
//                return true;
//        }
//        return false;
//    }
//
//    public boolean isLesson(Term term){
//        for (Lesson l : list){
//
//            if (l.getTerm().equals(term))
//                return true;
//        }
//        return false;
//    }

    @Override
    public boolean busy(Term term) {
//        for (Lesson l : list){
//            if (term.getDay() == l.getTerm().getDay()) {
//                if ((l.getTerm().earlierThan(term.endTerm()) &&
//                        l.getTerm().endTerm().laterThan(term))) {
//                    if (!skipBreaks) {
//
//                        return isLesson(term);
//                    } else
//                        return true;
//                }
//            }
//        }

        Lesson lesson = hashMap.get(term);

        return lesson != null;
//        return false;

    }


    // tutaj testowane dodawanie do listy AbstractTimetableItem

//    @Override
//    public boolean put(Lesson lesson) {
//        if (!busy(lesson.getTerm())){
//            if (canBeTransferredTo(lesson.getTerm(), lesson.isFullTime()))
//                testList.add(lesson);
//            return true;
//        }
//
//        return false;
//    }

    @Override
    public void perform(Action[] actions) {
        int def = 90;

        int i = 0;


        int bDurationPrev = 0;
        int bDurationAfter = 0;
        ArrayList<Lesson> lessonList = new ArrayList<>(hashMap.values());

        int l = lessonList.size();
        for(Action act : actions){

            Lesson lesson = lessonList.get(i);

            bDurationPrev = 0;
            bDurationAfter = 0;
            //search for break after and prev
            if (skipBreaks){
                for (Break b : breaks){
                    if (b.getTerm().endTerm().equalsOnlyHAndM(lesson.getTerm())){
                        bDurationPrev = b.getTerm().getDuration();
                    }
                    if (b.getTerm().equalsOnlyHAndM(lesson.getTerm().endTerm())){
                        bDurationAfter = b.getTerm().getDuration();
                    }
                }
            }
            Term oldKey = lesson.getTerm();
            switch (act) {
                case DAY_LATER:
                    lesson.laterDay();
                    break;
                case DAY_EARLIER:
                    lesson.earlierDay();
                    break;
                case TIME_LATER:
                    System.out.println(lesson.laterTime(def + bDurationAfter));
                    break;
                case TIME_EARLIER:
                    System.out.println(lesson.earlierTime(def + bDurationPrev));
                    break;
            }
            hashMap.put(lesson.getTerm(), hashMap.remove(oldKey));
            i+=1;
            if (i >= l)
                i = 0;
        }
    }


    @Override
    public String toString() {
        String res = "";
        Day lastDay = Day.SUN;
        Term lastTerm = new Term(20,0,lastDay);
        Day day = null;
        Term term = null;
        int shifted = 0;

        int bInd = 0;
        for(int i = 0 ; i < 7 ; i+=1){
            day = Day.values()[i];
            bInd = 0;

//            System.out.println(day);

            res += day.toString()+"\n";
            for(term = new Term(8,0,day) ; term.endTerm().earlierThan(lastTerm) ; ){
//                System.out.print(term);
                res += term.toString();
                if(this.busy(term)) {
                    res += " " + this.get(term).toString();
//                    System.out.println(term);
//                    System.out.println(" " + this.get(term).toString());

                }
                else {
//                    System.out.println(" ");
                    res += " ";
                }

                term = term.endTerm();

                if (bInd < breaks.length) {
                    if (term.getHour() == breaks[bInd].getTerm().getHour() &&
                            term.getMinute() == breaks[bInd].getTerm().getMinute()) {
//                    System.out.println(breaks[bInd]);
                        res += "\n" + breaks[bInd].toString() + "--------------";

                        term = term.moveTerm(breaks[bInd].getTerm().getDuration());

                        bInd += 1;
                    }
                }

                res += "\n";
            }
        }

        return res+"\nList of terms "+hashMap.toString();


        //Sprawdzenie poprawnosci dodawania do listy abstracttimetableitem
//        return this.testList.toString();
    }
}
