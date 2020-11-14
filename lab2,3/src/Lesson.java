

public class Lesson extends AbstractTimetableItem{
    private Term term;
    private String name;
    private String teacherName;
    private int year;
    private boolean fullTime;


    public BasicTerm[][] getArr(){
        if (this.isFullTime())
            return fullTimeArr;
        else
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

    public Lesson(Term term, String name, String teacherName, int year){
        this.setTerm(term);
        this.setName(name);
        this.setTeacherName(teacherName);
        this.setYear(year);
        this.setFullTime(isFullTime(term));
    }

    public Lesson(ITimetable timetable, Term term, String name, String teacherName, int year){
        this.setTerm(term);
        this.setName(name);
        this.setTeacherName(teacherName);
        this.setYear(year);
        this.setFullTime(isFullTime(term));

        this.setTimetable(timetable);

    }


    private String type(){
        if (isFullTime()){
            return "studiow stacjonarnych";
        } else {
            return "studiow niestacjonarnych";
        }
    }
    public String toString(boolean fullName) {
        return getName() + " (" + getTerm().getDay() + " " + getTerm().toString(true) + "-" + getTerm().endTerm().toString(true) + ")" +"\n" +
                getYear() + " rok " + type() + "\n" +
                "Prowadzacy: " + getTeacherName();
    }

    public String toString(){
        return getName();
    }

    private boolean moveDay(int n){
        int k = Day.values().length;
        int maxDays = 10 * k;
        int indeks = (maxDays + this.getTerm().getDay().ordinal() + n) % k;

        BasicTerm[][] arr = this.getArr();
        if (arr[indeks][1] == null)
            return false;

        if (this.getTerm().earlierThan(arr[indeks][0]) ||
                this.getTerm().endTerm().laterThan(arr[indeks][1]))
            return false;

        this.getTerm().setDay(Day.values()[indeks]);
        return true;


    }

    public boolean earlierDay(){
        if (this.getTimetable() != null) {
            Term copy = new Term(this.getTerm().getHour(), this.getTerm().getMinute(), this.getTerm().getDay().prevDay());

            if (!this.getTimetable().canBeTransferredTo(copy, this.isFullTime()))
                return false;

            this.setTerm(copy);

            return true;
        }

        return moveDay(-1);
    }


    public boolean laterDay(){
        if (this.getTimetable() != null) {
            Term copy = new Term(this.getTerm().getHour(), this.getTerm().getMinute(), this.getTerm().getDay().nextDay());

            if (!this.getTimetable().canBeTransferredTo(copy, this.isFullTime()))
                return false;

            this.setTerm(copy);

            return true;
        }
        return moveDay(1);
    }


    private boolean moveTime(int duration){
        Term newTerm = this.getTerm().moveTerm(duration);

        if (this.getTimetable() != null) {
            if (!this.getTimetable().canBeTransferredTo(newTerm, this.isFullTime()))
                return false;

            this.setTerm(newTerm);
            return true;
        } else {
            if (this.isFullTime() != isFullTime(newTerm))
                return false;

            this.setTerm(newTerm);
            return true;
        }
    }
    public boolean earlierTime(int duration) {
        return moveTime(-duration);
    }

    public boolean laterTime(int duration){

        return moveTime(duration);
    }


    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public ITimetable getTimetable() {
        return timetable;
    }

    public void setTimetable(ITimetable timetable) {
        this.timetable = timetable;
    }
}
