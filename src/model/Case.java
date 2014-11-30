package model;

import java.util.Observable;

public class Case extends Observable {

    private Shape shape;
    private Type type;

    public Case(Shape shape, Type type) {
        this.shape = shape;
        this.type = type;
    }

    public Case(Type type) {
        this.shape = Shape.values()[(int) (Math.random() * ((Shape.values().length - 1) + 1))];
        this.type = type;
    }

    public void regenerate(Case c) {
        this.shape = c.getShape();
        this.type = c.getType();
        setChanged();   // Obligatoire avant de prévenir les observers, 
        // si un observer n'a pas perçus de changement depuis son dernier appel, il ne fait rien
        notifyObservers();
    }

    public Type getType() {
        return type;
    }

    public Shape getShape() {
        return this.shape;
    }

    @Override
    public String toString() {
        if (this.type == Type.EMPTY) {
            return "-1 ";
        }
        return this.type.ordinal() + "" + this.shape.ordinal() + " ";
    }

}
