public class Term {
    final public int hour;
    final public int minute;
    public int duration;

    public Term(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
        this.duration = 90;
    }

    public String toString(){
        return (this.hour + ":" + this.minute + " [" + this.duration + "]");
    }

    public boolean equals(Term term){
        if (this.hour == term.hour)
            if (this.minute == term.minute)
                if (this.duration == term.duration)
                    return true;
        
        return false;
    }

    public boolean earlierThan(Term term){
        if (this.hour < term.hour){
            return true;
        }else if (this.hour == term.hour){
            if (this.minute < term.minute){
                return true;
            }
        } 
        return false;
    }

    public boolean laterThan(Term term){
        if (this.hour > term.hour){
            return true;
        }else if (this.hour == term.hour){
            if (this.minute > term.minute){
                return true;
            }
        } 
        return false;
    }

    public Term endTerm(Term term){
        Term res = new Term(this.hour, this.minute);
        res.duration = (term.hour - this.hour) * 60 + term.minute - this.minute;
        return res;
    }

    public Term endTerm(){
        int hour = this.hour + (this.minute + this.duration)/60;
        int minute = (this.minute + this.duration)%60;

        Term res = new Term(hour, minute);

        res.duration = this.duration;

        return res;
    }
}
