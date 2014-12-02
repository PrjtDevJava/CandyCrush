/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import model.Case;
import model.Grid;
import view.CasePane;

/**
 *
 * @author Sylvio
 */
public class GridController implements MouseListener{
    private Grid grid;
    private CasePane caseSelected;
    
    GridController(Grid grid){
        this.grid = grid;
        this.caseSelected = null;
    }
    
    @Override
    // préférable que MouseClicked, car le relachement n'est pas obligatoirement sur la même case
    public void mouseReleased(MouseEvent ev) {
        CasePane pane = (CasePane)ev.getSource();
        
        if(caseSelected == null){
            caseSelected = pane;
            pane.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        }
        // Si il y seulement un carreau d'écart (haut, bas, doite, gauche mais pas diagonale)
        else if((caseSelected.x - pane.x) <= 1 && (caseSelected.x - pane.x) >= -1 && (caseSelected.y - pane.y) == 0 ||  
                (caseSelected.y - pane.y) <= 1 && (caseSelected.y - pane.y) >= -1 && (caseSelected.x - pane.x) == 0){
            pane.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
            Case c1 = grid.getCase(caseSelected.x, caseSelected.y);
            Case c2 = grid.getCase(pane.x, pane.y);
            c1.aggregation(c2.getShape()); // On regarde s'il y a une aggrégation quand on inverse les cases
            System.out.println("Color : " + c2.getShape());
            //c2.aggregation(c1.getShape());
            System.out.println("casePane : x:" + caseSelected.x + " y:" + caseSelected.y);
            System.out.println("case : x:" + c1.getX() + " y:" + c1.getY());
            caseSelected = null;
        }
        
        
    }
    
    @Override
    public void mouseClicked(MouseEvent ev) {
    }

    @Override
    public void mousePressed(MouseEvent ev) {
        
    }


    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
