package model;

import java.util.Objects;
import java.util.Observable;

public class Case extends Observable {
    public static final int NB_CASE_POINT = 3; // Nombre de cases pour avoir au moins un point
    private Shape shape;
    private Type type;
    private final Grid grid;
    private final int x;
    private final int y;

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
    
    public void changeType(Type type){
        this.type = type;
        setChanged();
        notifyObservers();
    }
    
    public void changeShape(Shape shape){
        this.shape = shape;
        setChanged();
        notifyObservers();
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setShape(Shape s){
        this.shape = s;
    }
    
    /** Regarde si une agrégation est possible sur cette case (Si c'est possible, appel d'autres cases pour appliquer la gravité)
     * @return : le nombre de case à supprimer pour cette agrégation (Utile pour compter les points)
     */
    @SuppressWarnings("empty-statement")
    public int aggregation(){
        int nbR; // Nombre de bonnes case à droite
        int nbL; // Nombre de bonnes case à gauche
        int nbT = 0; // Nombre de bonnes case en haut
        int nbB = 0; // Nombre de bonnes case en bas
        int points = 0;
        
        int i;
        for(i=this.x+1; i < grid.getWidth() && grid.getCase(i, this.y).getShape() == this.shape; i++);
        nbR = i - (this.x+1);
        for(i=this.x-1; i >= 0 && grid.getCase(i, this.y).getShape() == this.shape; i--);
        nbL = i*(-1) + (this.x-1);
        for(i=this.y+1; i < grid.getHeight() && grid.getCase(this.x, i).getShape() == this.shape; i++);
        nbB = i - (this.y+1);
        for(i=this.y-1; i >= 0 && grid.getCase(this.x, i).getShape() == this.shape; i--);
        nbT = i*(-1) + (this.y-1);
        System.out.println("Droit : " + nbR + " Gauche : " + nbL);
        System.out.println("Bas : " + nbB + " Haut : " + nbT);
        
        System.out.println("Px : " + (nbR + nbL) + " Py : " + (nbT + nbB));
        
        if((nbR + nbL + 1) >= NB_CASE_POINT){ // +1 Pour compter la case actuelle
            for(int j=this.x-nbL; j < (this.x+nbR+1); j++){
                grid.getCase(j, this.y).changeType(Type.EMPTY);
            }
            points += (nbR + nbL + 1);
        }
        if((nbT + nbB + 1) >= NB_CASE_POINT){
            for(int j=this.y-nbT; j < (this.y+nbB+1); j++){
                grid.getCase(this.x, j).changeType(Type.EMPTY);
            }
            points += (nbR + nbL + 1);
        }
        
        
        return points;
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
