public class Break extends AbstractTimetableItem{
    private BasicTerm term;

    public Break(BasicTerm term, int duration) {
        this.term = term;
        this.term.setDuration(duration);
    }

    @Override
    public String toString() {
        return term.toString();
    }

    public BasicTerm getTerm() {
        return term;
    }
}
