package model;

import java.awt.Color;

public enum Shape {
    
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    RED(Color.RED),
    GREEN(Color.GREEN);
    Color color;

    Shape(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
}
