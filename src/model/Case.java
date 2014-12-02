package model;

import java.awt.Color;
import java.util.Objects;
import java.util.Observable;

public class Case extends Observable {

    private Shape shape;
    private Type type;
    private Grid grid;
    private int x;
    private int y;

    public Case(int x, int y, Type type, Grid grid) {
        this.shape = Shape.values()[(int) (Math.random() * ((Shape.values().length - 1) + 1))];
        this.type = type;
        this.grid = grid;
        this.x = x;
        this.y = y;
    }


    public void regenerate(Case c) {
        this.shape = c.getShape();
        this.type = c.getType();
        setChanged();   // Obligatoire avant de prévenir les observers, 
        // si un observer n'a pas perçus de changement depuis son dernier appel, il ne fait rien
        notifyObservers();
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    /** Regarde si une agrégation est possible sur cette case (Si c'est possible, appel d'autres cases pour appliquer la gravité)
     * @param shape : la formae par laquelle la case pourrait être remplacée si l'agrégation est possible
     * @return : le nombre de case à supprimer pour cette agrégation (Utile pour compter les points)
     */
    public int aggregation(Shape shape){
        int pointsXR = 0; // Points sur l'axe x droit
        int pointsXL = 0; // Points sur l'axe x gauche
        int pointsY = 0; // Points sur l'axe y
        for(int curX=this.x+1; curX < grid.getWidth() && grid.getCase(curX, this.y).getShape() == shape; curX++){
            if(curX-this.x >= 3){
                pointsXR++;
            }
        }
        for(int curX=this.x-1; curX > 0 && grid.getCase(curX, this.y).getShape() == shape; curX++){
            if(curX-this.x >= 3){
                pointsXL++;
            }
        }
        
        
        
        
        return 0;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.shape);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Case other = (Case) obj;
        if (this.shape != other.shape) {
            return false;
        }
        return true;
    }

    
   
    
    

}
