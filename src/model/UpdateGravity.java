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
    private final int column;
    private final Grid grid;
    
    
    public UpdateGravity(int numColumn, Grid grid){
        this.column = numColumn;
        this.grid = grid;
    }
    
    
    public static synchronized void incrementThread(){
        nbThread++;
    }
    
     public static synchronized void decrementThread(){
        nbThread--;
        if(nbThread == 0){
            System.out.println("Mise à jour Grav: " + setCaseToUpdAgreg);
            for (Case curCase : setCaseToUpdAgreg) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UpdateGravity.class.getName()).log(Level.SEVERE, null, ex);
                }
                new UpdateAgregation(curCase).start();
            }
            setCaseToUpdAgreg.clear();
        }
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void run(){

            incrementThread();
            synchronized(grid){ // Pas très optimisé, on pourrait faire la synchronisation par colonne
                
                int numStartC; // La case de départ (dès qu'on trouve un case non vide en dessous de la case c)
                for(numStartC=grid.getHeight()-1; numStartC > 0 && grid.getCase(column, numStartC).getType() != Type.EMPTY; numStartC--);
                Case startC = grid.getCase(column, numStartC);
                
                for(int i=numStartC; i >= 0; i--){
                    Case tmpCase = grid.getCase(column, i);
                    if(tmpCase.getType() != Type.EMPTY){
                        setCaseToUpdAgreg.add(startC);
                        startC.regenerate(tmpCase);
                        tmpCase.setShape(null);
                        tmpCase.changeType(Type.EMPTY);
                        
                        if(numStartC > 0){
                            numStartC--;
                            startC = grid.getCase(column, numStartC);
                        }
                    }
                }
                // Régénère des nouvelles couleurs aléatoires pour les cases vides de la colonne
                for(int i=numStartC; i >= 0; i--){
                    Case tmpCase = grid.getCase(column, i);
                    tmpCase.regenerate(Type.NORMAL);
                    setCaseToUpdAgreg.add(tmpCase);
                }
            }
            decrementThread();
        
        
        //System.out.println("Nb thread : " + Thread.activeCount());
        
        
    }
    
}
