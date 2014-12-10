package model;

import java.io.Serializable;
import java.util.Observable;

public class PointsCounter extends Observable implements Serializable {

    private int points;

    public PointsCounter() {
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        setChanged();
        notifyObservers();
    }

    public void addPoints(int points) {
        this.points += points;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "<html><div style=\"margin-left:40px;\">" + points + "</div></html>";
    }

}
