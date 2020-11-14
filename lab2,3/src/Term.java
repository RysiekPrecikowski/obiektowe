import java.util.Objects;

public class Term extends BasicTerm{
    protected Day day;

    public Term(int hour, int minute, Day day){
        super(hour, minute);
        this.hour = hour;
        this.minute = minute;
        this.setDuration(90);
        this.setDay(day);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        if (this.getDay() == term.getDay())
            if (this.getHour() == term.getHour())
                if (this.getMinute() == term.getMinute())
                    if (this.getDuration() == term.getDuration())
                        return true;

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDay()) + Objects.hash(getHour()) + Objects.hash(getMinute()) + Objects.hash(getDuration());
    }

    //    public boolean equalsIgnoreDay(Term term){
//
//        if (this.getHour() == term.getHour())
//            if (this.getMinute() == term.getMinute())
//                if (this.getDuration() == term.getDuration())
//                    return true;
//
//        return false;
//    }
//
//    public boolean equalsOnlyHAndM(Term term){
//        if (this.getHour() == term.getHour())
//            if (this.getMinute() == term.getMinute())
//                return true;
//
//        return false;
//    }

//    public boolean earlierThan(Term term){
//        if (this.getHour() < term.getHour()){
//            return true;
//        }else if (this.getHour() == term.getHour()){
//            if (this.getMinute() < term.getMinute()){
//                return true;
//            }
//        }
//        return false;
//    }

//    public boolean laterThan(Term term){
//        if (this.getHour() > term.getHour()){
//            return true;
//        }else if (this.getHour() == term.getHour()){
//            if (this.getMinute() > term.getMinute()){
//                return true;
//            }
//        }
//        return false;
//    }

    public Term endTerm(Term term){
        Term res = new Term(this.getHour(), this.getMinute(), this.day);
        res.setDuration((term.getHour() - this.getHour()) * 60 + term.getMinute() - this.getMinute());
        return res;
    }

    public Term endTerm(){
        int hour = this.getHour() + (this.getMinute() + this.getDuration())/60;
        int minute = (this.getMinute() + this.getDuration())%60;

        Term res = new Term(hour, minute, this.day);

        res.setDuration(this.getDuration());

        return res;
    }

    public Term moveTerm(int duration){
        int hour;
        int minute;
        if (duration > 0) {
            hour = this.getHour() + (this.getMinute() + duration) / 60;
            minute = (this.getMinute() + duration) % 60;
        } else {
            int maxH = 99; // maksymalnie 99 godzin do tylu mozna
            hour = this.getHour() - maxH + (maxH* 60 + this.getMinute() + duration) / 60;
            minute = (maxH * 60 + this.getMinute() + duration) % 60;
        }

        Term res = new Term(hour, minute, this.getDay());
        res.setDuration(this.getDuration());

        return res;
    }

    public boolean isFullTime(){
        Lesson idk = new Lesson(this.moveTerm(0), "","",1);
        BasicTerm[][] arr = idk.getArr();

        if (arr[this.getDay().ordinal()][0] == null){
            return false;
        }
        return this.laterThan(arr[this.getDay().ordinal()][0]) &&
                this.endTerm().earlierThan(arr[this.getDay().ordinal()][1]);
    }




    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
