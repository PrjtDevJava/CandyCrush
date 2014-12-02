/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Sylvio
 */
public class UpdateGravity extends java.lang.Thread{
    private Case c;
    
    public UpdateGravity(Case c){
        this.c = c;
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void run(){
        synchronized(c.getGrid()){ // Pas très optimisé, on pourrait faire la synchronisation par colonne
            if(c.getType() == Type.EMPTY){
                Grid grid = c.getGrid();
                int numStartC; // La case de départ (dès qu'on trouve un case non vide en dessous de la case c)
                for(numStartC=c.getY(); numStartC < grid.getHeight() && grid.getCase(c.getX(), numStartC).getType() == Type.EMPTY; numStartC++);
                numStartC--; // On se positionne sur la dernière case vide

                System.out.println("Thread : " + Thread.currentThread() + "  Start c :" + numStartC);
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
            System.out.println("Nb thread : " + Thread.activeCount());
        }
        
    }
    
}
