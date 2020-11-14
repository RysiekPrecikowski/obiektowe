
public abstract class AbstractTimetableItem implements ITimetableItem {
    protected BasicTerm[][] fullTimeArr = {
            {new BasicTerm(7,59), new BasicTerm(20,01)},
            {new BasicTerm(7,59), new BasicTerm(20,01)},
            {new BasicTerm(7,59), new BasicTerm(20,01)},
            {new BasicTerm(7,59), new BasicTerm(20,01)},
            {new BasicTerm(7,59), new BasicTerm(17,01)},
            {null, null},
            {null, null}
    };

    protected BasicTerm[][] partTimeArr = {
            {null, null},
            {null, null},
            {null, null},
            {null, null},
            {new BasicTerm(16,59), new BasicTerm(20,01)},
            {new BasicTerm(7,59), new BasicTerm(20,01)},
            {new BasicTerm(7,59), new BasicTerm(20,01)},
    };

    protected ITimetable timetable;

    protected Object term;

    @Override
    public Object getTerm() {
        return term;
    }

//    abstract public boolean equals();


}
