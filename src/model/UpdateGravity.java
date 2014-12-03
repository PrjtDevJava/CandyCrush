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
public class UpdateGravity extends java.lang.Thread {
    public static int nbThread = 0;
    public static Lock mut = new ReentrantLock();
    public static Integer level = 0;
    private final Case c;
    
    
    public UpdateGravity(Case c){
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
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(UpdateGravity.class.getName()).log(Level.SEVERE, null, ex);
//        }

       
        
        
        if(c.getType() == Type.EMPTY){
            synchronized(c.getGrid()){ // Pas très optimisé, on pourrait faire la synchronisation par colonne
                Grid grid = c.getGrid();
                int numStartC; // La case de départ (dès qu'on trouve un case non vide en dessous de la case c)
                for(numStartC=c.getY(); numStartC < grid.getHeight() && grid.getCase(c.getX(), numStartC).getType() == Type.EMPTY; numStartC++);
                numStartC--; // On se positionne sur la dernière case vide
                System.out.println("Thread UpdateGravite -> nbThread : " + nbThread);
                //System.out.println("Thread : " + Thread.currentThread() + "  Start c :" + numStartC);
                Case startC = grid.getCase(c.getX(), numStartC);
                for(int i=numStartC; i >= 0; i--){
                    Case tmpCase = grid.getCase(c.getX(), i);
                    if(tmpCase.getType() != Type.EMPTY){
                        startC.regenerate(tmpCase);
                        tmpCase.setShape(null);
                        tmpCase.changeType(Type.EMPTY);
                        int j;
                        for(j=numStartC; j >= i && grid.getCase(c.getX(), j).getType() != Type.EMPTY; j--); // S'arrète dès qu'on a une case vide( i est obligatoirement vide)
                        startC = grid.getCase(c.getX(), j);
                        numStartC = j; // Replace le numéro de case
                    }
                }
                for(int i=numStartC; i >= 0; i--){
                    grid.getCase(c.getX(), i).regenerate(Type.NORMAL);
                }
            }
        }
//        mut.lock();
//        nbThread--;
//        if(nbThread == 0){
//            UpdateAgregation.cond.signal(); // On attend que toutes les aggrégations soient faites
//        }
//        mut.unlock();

        
        
         //section critique
        System.out.println("Nb thread : " + Thread.activeCount());
        
        
    }
    
}
