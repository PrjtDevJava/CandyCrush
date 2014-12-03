/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Sylvio
 */
public class UpdateGravity extends java.lang.Thread {
    public static int nbThread = 0;
    public static Lock mut = new ReentrantLock();
    private static Set<Case> setCaseToUpdAgreg = Collections.synchronizedSet(new HashSet());
    private final Case c;
    
    
    public UpdateGravity(Case c){
        this.c = c;
    }
    
    public static synchronized void incrementThread(){
        nbThread++;
    }
    
     public static synchronized void decrementThread(){
        nbThread--;
        if(nbThread == 0){
            System.out.println("Mise à jour Grav: " + setCaseToUpdAgreg);
            for (Case curCase : setCaseToUpdAgreg) {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(UpdateGravity.class.getName()).log(Level.SEVERE, null, ex);
//                }
                new UpdateAgregation(curCase).start();
            }
            setCaseToUpdAgreg.clear();
        }
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void run(){


        if(c.getType() == Type.EMPTY){
            incrementThread();
            synchronized(c.getGrid()){ // Pas très optimisé, on pourrait faire la synchronisation par colonne
                Grid grid = c.getGrid();
                int numStartC; // La case de départ (dès qu'on trouve un case non vide en dessous de la case c)
                for(numStartC=c.getY(); numStartC < grid.getHeight() && grid.getCase(c.getX(), numStartC).getType() == Type.EMPTY; numStartC++);
                if(numStartC != 0){
                    numStartC--; // On se positionne sur la dernière case vide
                }
                for(int i=numStartC; i >= 0; i--){ // Ajoute les cases sur lesquelles il faudra vérifier les agrégations
                    setCaseToUpdAgreg.add(grid.getCase(c.getX(), i));
                }
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
            decrementThread();
        }
        
        
        System.out.println("Nb thread : " + Thread.activeCount());
        
        
    }
    
}
