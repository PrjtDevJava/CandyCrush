package model;

import java.util.Observable;

public class Case extends Observable {

    private Shape shape;
    private Type type;

    public Case(Shape shape, Type type) {
        this.shape = shape;
        this.type = type;
    }

    public Type getType() {
        return type;
    }
    
    public Shape getShape(){
        return this.shape;
    }
    
    
   public void regenerate(Shape s, Type t){
       this.shape = s;
       this.type = t;
       setChanged(); // Obligatoire avant de prévenir les observers, 
                     // si un observer n'a pas perçus de changement depuis son dernier appel, il ne fait rien
       notifyObservers();
   }
   

}
