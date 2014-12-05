/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static model.Case.NB_CASE_POINT;

/**
 *
 * @author Sylvio
 */
public class UpdateAgregation extends java.lang.Thread {
    public static int nbThread = 0;
    private static Set<Integer> setColumnToUpdGrav = Collections.synchronizedSet(new HashSet());
    private static PointsCounter pc = null;
    
    private Case c;
    
    public UpdateAgregation(Case c){
        this.c = c;
    }
    
    private static synchronized void incrementThread(){
        nbThread++;
    }
    
    private static synchronized void decrementThread(Case c){
        nbThread--;
        if(nbThread == 0){
            //System.out.println("Send--------------");
            for (Integer it: setColumnToUpdGrav) {
                new UpdateGravity(it, c.getGrid()).start();
            }
            System.out.println("Column to update grav : " + setColumnToUpdGrav);
            setColumnToUpdGrav.clear();
        }
    }
    
    public static synchronized void setPointsCounter(PointsCounter pc){
        UpdateAgregation.pc = pc;
    }
    
    public static synchronized void addPoints(int points){
        UpdateAgregation.pc.addPoints(points);
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void run(){
        
        if(c != null && c.getShape() != null && c.getType() != Type.EMPTY){
            incrementThread();
            //System.out.println("Thread : " + Thread.currentThread().getId() + "  -> nb : " + nbThread);
            synchronized(c.getGrid()){
                Grid grid = c.getGrid();
                //System.out.println("Thread UpdateAgregation -> nbThread : " + nbThread);

                int nbR; // Nombre de bonnes case à droite
                int nbL; // Nombre de bonnes case à gauche
                int nbT; // Nombre de bonnes case en haut
                int nbB; // Nombre de bonnes case en bas
                int points = 0;

                int i;
                for(i=c.getX()+1; i < grid.getWidth() && grid.getCase(i, c.getY()).getShape() == c.getShape(); i++);
                nbR = i - (c.getX()+1);
                for(i=c.getX()-1; i >= 0 && grid.getCase(i, c.getY()).getShape() == c.getShape(); i--);
                nbL = i*(-1) + (c.getX()-1);
                for(i=c.getY()+1; i < grid.getHeight() && grid.getCase(c.getX(), i).getShape() == c.getShape(); i++);
                nbB = i - (c.getY()+1);
                for(i=c.getY()-1; i >= 0 && grid.getCase(c.getX(), i).getShape() == c.getShape(); i--);
                nbT = i*(-1) + (c.getY()-1);
    //            System.out.println("Droit : " + nbR + " Gauche : " + nbL);
    //            System.out.println("Bas : " + nbB + " Haut : " + nbT);


                if((nbR + nbL + 1) >= NB_CASE_POINT){ // +1 Pour compter la case actuelle
                    for(int j=c.getX()-nbL; j < (c.getX()+nbR+1); j++){
                        grid.getCase(j, c.getY()).changeType(Type.EMPTY);
                        grid.getCase(j, c.getY()).setShape(null);
                    }
                    points += (nbR + nbL) - 1;
                }
                else{
                    nbR=0;
                    nbL=0;
                }


                if((nbT + nbB + 1) >= NB_CASE_POINT){
                    for(int j=c.getY()-nbT; j < (c.getY()+nbB+1); j++){
                        grid.getCase(c.getX(), j).setShape(null);
                        grid.getCase(c.getX(), j).changeType(Type.EMPTY);
                    }
                    points += (nbT + nbB) - 1;
                }



            if(points > 0){
                UpdateAgregation.addPoints(points);
                setColumnToUpdGrav.add(c.getX());
                for(int j=c.getX()-nbL; j < (c.getX()+nbR+1); j++){
                    if(j != c.getX()){
                        setColumnToUpdGrav.add(j);
                    }
                }
            }
        }
        decrementThread(c);
        //System.out.println("Dell Thread : " + Thread.currentThread().getId() + "  -> nb : " + nbThread);
        }
        
        
    }
}
