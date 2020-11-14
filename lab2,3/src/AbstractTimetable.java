import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractTimetable implements ITimetable {
    protected final BasicTerm[][] fullTimeArr = {
            {new BasicTerm(7,59), new BasicTerm(20, 1)},
            {new BasicTerm(7,59), new BasicTerm(20, 1)},
            {new BasicTerm(7,59), new BasicTerm(20,1)},
            {new BasicTerm(7,59), new BasicTerm(20,1)},
            {new BasicTerm(7,59), new BasicTerm(17,1)},
            {null, null},
            {null, null}
    };

    protected final BasicTerm[][] partTimeArr = {
            {null, null},
            {null, null},
            {null, null},
            {null, null},
            {new BasicTerm(16,59), new BasicTerm(20,1)},
            {new BasicTerm(7,59), new BasicTerm(20,1)},
            {new BasicTerm(7,59), new BasicTerm(20,1)},
    };


//    public List<Lesson> list = new ArrayList<Lesson>();
    public Map<Term, Lesson> hashMap = new LinkedHashMap<>();


    public BasicTerm[][] getFullTimeArr() {
        return fullTimeArr;
    }

    public BasicTerm[][] getPartTimeArr(){
        return partTimeArr;
    }

    public boolean isFullTime(Term term){
        if (fullTimeArr[term.getDay().ordinal()][0] == null){
            return false;
        }
        return term.laterThan(fullTimeArr[term.getDay().ordinal()][0]) &&
                term.endTerm().earlierThan(fullTimeArr[term.getDay().ordinal()][1]);
    }

    public boolean isPartTime(Term term){
        if (partTimeArr[term.getDay().ordinal()][0] == null){
            return false;
        }
        return term.laterThan(partTimeArr[term.getDay().ordinal()][0]) &&
                term.endTerm().earlierThan(partTimeArr[term.getDay().ordinal()][1]);
    }

    public BasicTerm[][] getArr(Term term){
        if (this.isFullTime(term))
            return fullTimeArr;
        else
            return partTimeArr;
    }

    public boolean put(Lesson lesson) {
        if (!busy(lesson.getTerm())){
            if (canBeTransferredTo(lesson.getTerm(), lesson.isFullTime())) {
//                list.add(lesson);
                hashMap.put(lesson.getTerm(), lesson);
            }
            return true;
        }
        else {
            throw new IllegalArgumentException("Lesson " + lesson.toString() + " is incorrect");
        }
    }

    //mozna bylo przeniesc, bo implementacja ta sama
    public boolean busy(Term term) {

        Lesson lesson = hashMap.get(term);

        return lesson != null;

    }

    @Override
    public Object get(Term term) {
        return hashMap.get(term);

//        for (Lesson l : list){
//            if (l.getTerm().equals(term))
//                return l;
//        }
//        return null;
    }


}
