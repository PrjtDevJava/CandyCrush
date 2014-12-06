package model;

import java.util.Observable;

public class PointsCounter extends Observable {

    private int points;

    public PointsCounter() {
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "" + points;
    }

}
