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
import model.Shape;
import view.CasePane;

/**
 *
 * @author Sylvio
 */
public class GridController implements MouseListener{
    private Grid grid;
    private CasePane caseSelected;
    private int points; // *** Temporaire
    
    
    GridController(Grid grid){
        this.grid = grid;
        this.caseSelected = null;
        this.points = 0;
    }
    
    @Override
    // préférable que MouseClicked, car le relachement n'est pas obligatoirement sur la même case
    public void mouseReleased(MouseEvent ev) {
        if(ev.getSource() != this.caseSelected){
            CasePane pane = (CasePane)ev.getSource();
            if(caseSelected == null){
                caseSelected = pane;
                pane.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
            }
            // Si il y seulement un carreau d'écart (haut, bas, doite, gauche mais pas diagonale)
            else if(pane != this.caseSelected &&
                    (caseSelected.x - pane.x) <= 1 && (caseSelected.x - pane.x) >= -1 && (caseSelected.y - pane.y) == 0 ||  
                    (caseSelected.y - pane.y) <= 1 && (caseSelected.y - pane.y) >= -1 && (caseSelected.x - pane.x) == 0){
                Case c1 = grid.getCase(caseSelected.x, caseSelected.y);
                Case c2 = grid.getCase(pane.x, pane.y);
                Shape tmpShape = c1.getShape();
                c1.setShape(c2.getShape());
                c2.setShape(tmpShape);
                int pointsAdd = c1.aggregation();
                pointsAdd +=  c2.aggregation(); // On regarde s'il y a une aggrégation quand on inverse les cases
               if(pointsAdd > 0){
                   points += pointsAdd;
                   c1.changeShape(c1.getShape());
                   c2.changeShape(c2.getShape());
               }
               else{
                   c2.setShape(c1.getShape());
                   c1.setShape(tmpShape);
               }
                this.caseSelected.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                caseSelected = null;
            }
            else{
                this.caseSelected.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                caseSelected = null;
            }
        }
        else{
            this.caseSelected.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
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
