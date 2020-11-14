public class LessonObserver implements Observer {
    protected Subject subject;

    public LessonObserver(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }

    @Override
    public void update() {
        Object state = subject.getState();
    }
}
