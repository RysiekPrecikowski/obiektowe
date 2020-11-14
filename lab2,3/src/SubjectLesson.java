import java.util.List;
import java.util.ArrayList;

public class SubjectLesson implements Subject{
    protected List<Observer> observers = new ArrayList<>();
    private Object state;


    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
        this.notifyAllObservers();
    }


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer : observers){
            observer.update();
        }
    }
}
