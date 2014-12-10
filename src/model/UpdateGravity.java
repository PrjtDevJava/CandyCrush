package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateGravity extends java.lang.Thread {

    public static int nbThread = 0;
    public static Lock mut = new ReentrantLock();
    private static Set<Case> setCaseToUpdAgreg = Collections.synchronizedSet(new HashSet());
    private final int column;
    private final Grid grid;

    public UpdateGravity(int numColumn, Grid grid) {
        this.column = numColumn;
        this.grid = grid;
    }

    public static synchronized void incrementThread() {
        nbThread++;
    }

    public static synchronized void decrementThread() {
        nbThread--;
        if (nbThread == 0) {
            System.out.println("----");
            for (Case curCase : setCaseToUpdAgreg) {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(UpdateGravity.class.getName()).log(Level.SEVERE, null, ex);
//                }
                //System.out.println("x: " + curCase.getX() + " y: " + curCase.getY());
                new UpdateAgregation(curCase).start();
                System.out.println("x: " + curCase.getX() + " y: " + curCase.getY());
            }
            setCaseToUpdAgreg.clear();
            
        }
    }

    @Override
    public void run() {

        incrementThread();
        gravityHightPerf();
        decrementThread();

        //System.out.println("Nb thread : " + Thread.activeCount());
    }
    
    @SuppressWarnings("empty-statement")
    private void gravityLowPerf(){
        synchronized (grid) { // Pas très optimisé, on pourrait faire la synchronisation par colonne

            int numStartC; // La case de départ (dès qu'on trouve un case non vide en dessous de la case c)
            for (numStartC = grid.getHeight() - 1; numStartC > 0 && grid.getCase(column, numStartC).getType() != Type.EMPTY; numStartC--);
            if(numStartC+1 != grid.getHeight()){
                setCaseToUpdAgreg.add(grid.getCase(column, numStartC+1));
            }
            
            Case startC = grid.getCase(column, numStartC);

            for (int i = numStartC; i >= 0; i--) {
                Case tmpCase = grid.getCase(column, i);
                setCaseToUpdAgreg.add(tmpCase); // Ajoute dans la liste pour vérifier la gravité
                if (tmpCase.getType() != Type.EMPTY) {
                    startC.regenerate(tmpCase);
                    tmpCase.setShape(null);
                    tmpCase.changeType(Type.EMPTY);

                    if (numStartC > 0) {
                        numStartC--;
                        startC = grid.getCase(column, numStartC);
                    }
                }
            }
            // Régénère des nouvelles couleurs aléatoires pour les cases vides de la colonne
            for (int i = numStartC; i >= 0; i--) {
                Case tmpCase = grid.getCase(column, i);
                tmpCase.regenerate(Type.NORMAL);
                //setCaseToUpdAgreg.add(tmpCase);
            }
        }
    }
    
    @SuppressWarnings("empty-statement")
    private void gravityHightPerf(){
        //synchronized (grid) { // Pas très optimisé, on pourrait faire la synchronisation par colonne

            int numStartC; // La case de départ (dès qu'on trouve un case non vide en dessous de la case c)
            for (numStartC = grid.getHeight() - 1; numStartC > 0 && grid.getCase(column, numStartC).getType() != Type.EMPTY; numStartC--);
            if(numStartC+1 != grid.getHeight()){
                setCaseToUpdAgreg.add(grid.getCase(column, numStartC+1));
            }
            
            Case startC = grid.getCase(column, numStartC);
            Case[] casesToMove = new Case[numStartC+1];
            
            for (int i = numStartC; i >= 0; i--) {
                Case tmpCase = grid.getCase(column, i);
                casesToMove[i] = tmpCase;
                setCaseToUpdAgreg.add(tmpCase);
            }
            
            int i;
            do{
                try {
                    Thread.sleep(150);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UpdateGravity.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(i=casesToMove.length-1; i >= 0; i--){
                    if(casesToMove[i].getType() == Type.EMPTY){
                        break;
                    }
                }
                if(i >= 0){
                    for(int j=i; j > 0; j--){
                        casesToMove[j].regenerate(casesToMove[j-1]);
                    }
                    casesToMove[0].regenerate(Type.NORMAL);
                }
                
            }while(i >= 0);
            
            // Régénère des nouvelles couleurs aléatoires pour les cases vides de la colonne
//            for (int i = numStartC; i >= 0; i--) {
//                Case tmpCase = grid.getCase(column, i);
//                tmpCase.regenerate(Type.NORMAL);
//                //setCaseToUpdAgreg.add(tmpCase);
//            }
        //}
    }

}
