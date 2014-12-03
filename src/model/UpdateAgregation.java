/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.Case.NB_CASE_POINT;

/**
 *
 * @author Sylvio
 */
public class UpdateAgregation extends java.lang.Thread {
    public static int nbThread = 0;
    public static Lock mut = new ReentrantLock();
    
    private Case c;
    
    public UpdateAgregation(Case c){
        this.c = c;
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void run(){
        mut.lock();
        try {
            nbThread++;
        } finally {
            mut.unlock();
        }
        
        
        if(c.getType() != Type.EMPTY){
            synchronized(c.getGrid()){
            Grid grid = c.getGrid();
            System.out.println("Thread UpdateAgregation -> nbThread : " + nbThread);

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
            System.out.println("Droit : " + nbR + " Gauche : " + nbL);
            System.out.println("Bas : " + nbB + " Haut : " + nbT);

            System.out.println("Px : " + (nbR + nbL) + " Py : " + (nbT + nbB));

            if((nbR + nbL + 1) >= NB_CASE_POINT){ // +1 Pour compter la case actuelle
                for(int j=c.getX()-nbL; j < (c.getX()+nbR+1); j++){
                    grid.getCase(j, c.getY()).changeType(Type.EMPTY);
                    grid.getCase(j, c.getY()).setShape(null);
                }
                points += (nbR + nbL);
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
                points += (nbT + nbB);
            }
            
        mut.lock();
        nbThread--;
        if(nbThread == 0){
            notifyAll();
            mut.unlock();
        }
        else{
            mut.unlock();
            try {
                wait();
                System.out.println("Salut : " + Thread.currentThread());
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateAgregation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
//        if(points > 0){
//            new UpdateGravity(c).start();
//            for(int j=c.getX()-nbL; j < (c.getX()+nbR+1); j++){
//                if(j != c.getX()){
//                    new UpdateGravity(grid.getCase(j, c.getY())).start();
//                }
//            }
//        }
        
//                if(points > 0){
//                    new UpdateGravity(c).start();
//                    for(int j=c.getX()-nbL; j < (c.getX()+nbR+1); j++){
//                        if(j != c.getX()){
//                            new UpdateGravity(grid.getCase(j, c.getY())).start();
//                        }
//                    }
//                }
            }
        }
    }
}
