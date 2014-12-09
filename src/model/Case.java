package model;

import java.util.Observable;

public class Case extends Observable {

    public static final int NB_CASE_POINT = 3; // Nombre de cases pour avoir au moins un point
    private Shape shape;
    private Type type;
    private final Grid grid;
    private final int x;
    private final int y;

    public Case(int x, int y, Type type, Grid grid) {
        this.shape = Shape.values()[(int) (Math.random() * ((grid.getNbShape() - 1) + 1))];
        this.type = type;
        this.grid = grid;
        this.x = x;
        this.y = y;
    }

    /**
     * Génère une case avec une forme aléatoire
     *
     * @param type : Le type que l'on veut donner à la case
     */
    public void regenerate(Type type) {
        this.shape = Shape.values()[(int) (Math.random() * ((grid.getNbShape() - 1) + 1))];
        this.type = type;
        setChanged();
        notifyObservers(); // si un observer n'a pas perçus de changement depuis son dernier appel, il ne fait rien
    }

    /**
     * Régénère une case à partire des caractèristiques d'une autre
     *
     * @param c : La case sur la quelle on se base pour la génération
     */
    public void regenerate(Case c) {
        this.shape = c.getShape();
        this.type = c.getType();
        setChanged();   // Obligatoire avant de prévenir les observers, 
        // si un observer n'a pas perçus de changement depuis son dernier appel, il ne fait rien
        notifyObservers();
    }

    public void changeType(Type type) {
        this.type = type;
        setChanged();
        notifyObservers();
    }

    public void changeShape(Shape shape) {
        this.shape = shape;
        setChanged();
        notifyObservers();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void setShape(Shape s) {
        this.shape = s;
    }

    /**
     * Regarde si une agrégation est possible sur cette case (Si c'est possible,
     * appel d'autres cases pour appliquer la gravité)
     *
     * @return : le nombre de case à supprimer pour cette agrégation (Utile pour
     * compter les points)
     */
    @SuppressWarnings("empty-statement")
    public int aggregation() {
        if (this.type != Type.EMPTY) {
            new UpdateAgregation(this).start();
        }
        return 1;
    }

    public Type getType() {
        return type;
    }

    public Shape getShape() {
        return this.shape;
    }

    @Override
    public String toString() {
        if (this == null || this.type == null || this.shape == null) {
            return "null";
        }
        if (this.type == Type.EMPTY) {
            return "-1 ";
        }
        return this.type.ordinal() + "" + this.shape.ordinal() + " ";
    }

    public boolean equivalent(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Case c = (Case) obj;
        if (this.shape != c.shape || this.type != c.type) {
            return false;
        }
        return true;
    }

}
