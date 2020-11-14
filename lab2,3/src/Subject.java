public interface Subject {
    public void addObserver(Observer observer);
    public void deleteObserver(Observer observer);
    public void notifyAllObservers();

    public Object getState();
    public void setState(Object state);
}
