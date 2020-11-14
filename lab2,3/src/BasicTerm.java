public class BasicTerm {
    protected int hour;
    protected int minute;
    protected int duration;


    public BasicTerm(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
        this.setDuration(90);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public BasicTerm endTerm(){
        int hour = this.getHour() + (this.getMinute() + this.getDuration())/60;
        int minute = (this.getMinute() + this.getDuration())%60;

        BasicTerm res = new BasicTerm(hour, minute);

        res.setDuration(this.getDuration());

        return res;
    }

    public BasicTerm endTerm(BasicTerm term){
        BasicTerm res = new BasicTerm(this.getHour(), this.getMinute());
        res.setDuration((term.getHour() - this.getHour()) * 60 + term.getMinute() - this.getMinute());
        return res;
    }

    public BasicTerm moveTerm(int duration){
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

        BasicTerm res = new BasicTerm(hour, minute);
        res.setDuration(this.getDuration());
        return res;
    }

    public boolean equals(BasicTerm term){
        if (this.getHour() == term.getHour())
            if (this.getMinute() == term.getMinute())
                if (this.getDuration() == term.getDuration())
                    return true;

        return false;
    }

    public boolean equalsOnlyHAndM(BasicTerm term){
        if (this.getHour() == term.getHour())
            if (this.getMinute() == term.getMinute())
                return true;

        return false;
    }

    public boolean earlierThan(BasicTerm term){
        if (this.getHour() < term.getHour()){
            return true;
        }else if (this.getHour() == term.getHour()){
            if (this.getMinute() < term.getMinute()){
                return true;
            }
        }
        return false;
    }

    public boolean laterThan(BasicTerm term){
        if (this.getHour() > term.getHour()){
            return true;
        }else if (this.getHour() == term.getHour()){
            if (this.getMinute() > term.getMinute()){
                return true;
            }
        }
        return false;
    }

    public String fixMinuteFormat(){
        if (this.getMinute() < 10)
            return "0" + this.getMinute();

        return Integer.toString(this.getMinute());
    }

    public String toString(boolean onlyStart){
        return (this.getHour() + ":" + fixMinuteFormat());
    }

    public String toString(){
        return (this.getHour() + ":" + fixMinuteFormat() + " [" + this.getDuration() + "]");
    }
}
