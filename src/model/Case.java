package model;

import java.util.Observable;

public class Case extends Observable {

    private final Shape shape;
    private final Type type;

    public Case(Shape shape, Type type) {
        this.shape = shape;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
