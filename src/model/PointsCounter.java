/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;

/**
 *
 * @author Sylvio
 */
public class PointsCounter extends Observable{
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
    
    public void addPoints(int points){
        this.points += points;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return ""+points;
    }
    
    
    
}
