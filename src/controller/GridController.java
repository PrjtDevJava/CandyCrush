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
import model.Case;
import model.Grid;
import model.Shape;
import model.UpdateAgregation;
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
        if(ev.getSource() != this.caseSelected){
            CasePane pane = (CasePane)ev.getSource();
            if(caseSelected == null){
                caseSelected = pane;
                pane.setBorder(Color.ORANGE);
            }
            // Si il y seulement un carreau d'écart (haut, bas, doite, gauche mais pas diagonale)
            else if(pane != this.caseSelected &&
                    (caseSelected.x - pane.x) <= 1 && (caseSelected.x - pane.x) >= -1 && (caseSelected.y - pane.y) == 0 ||  
                    (caseSelected.y - pane.y) <= 1 && (caseSelected.y - pane.y) >= -1 && (caseSelected.x - pane.x) == 0){
                Case c1 = grid.getCase(caseSelected.x, caseSelected.y);
                Case c2 = grid.getCase(pane.x, pane.y);
                
               
                UpdateAgregation updtAgreg = new UpdateAgregation(c1);
                updtAgreg.playCases(c2); // Test les 2 cases choisies par l'utilisateur
                this.caseSelected.setBorder(CasePane.BORDER_COLOR_DEFAULT);
                caseSelected = null;
            }
            else{
                this.caseSelected.setBorder(CasePane.BORDER_COLOR_DEFAULT);
                caseSelected = null;
            }
        }
        else{
            this.caseSelected.setBorder(CasePane.BORDER_COLOR_DEFAULT);
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
