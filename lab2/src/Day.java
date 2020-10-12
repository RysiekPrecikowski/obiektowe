
public enum Day {
    MON, TUE, WED, THU, FRI, SAT, SUN;

    public String toString(){
        switch(this){
            case MON: return "Poniedziałek";
            case TUE: return "Wtorek";
            case WED: return "Środa";
            case THU: return "Czwartek";
            case FRI: return "Piątek";
            case SAT: return "Sobota";
            case SUN: return "Niedziela";
            default: return "";

        }
    }

    public Day nextDay(){
        return Day.values()[(this.ordinal() + 1) % Day.values().length];  
    }

    public Day prevDay(){
        return Day.values()[(this.ordinal() - 1) % Day.values().length];  
    }
}
