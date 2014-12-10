package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import model.Case;
import model.Grid;
import model.UpdateAgregation;
import view.CasePane;
import view.MainScreen;

public class GridController implements MouseListener {

    private Grid grid;
    private CasePane caseSelected;

    GridController(Grid grid) {
        this.grid = grid;
        this.caseSelected = null;
    }

    @Override
    // préférable que MouseClicked, car le relachement n'est pas obligatoirement sur la même case
    public void mouseReleased(MouseEvent ev) {
        if (MainScreen.timer.getTimeRest() != 0) {
            MainScreen.timer.start();
            if (ev.getSource() != this.caseSelected) {
                CasePane pane = (CasePane) ev.getSource();
                if (caseSelected == null) {
                    caseSelected = pane;
                    pane.setBackground(CasePane.HOVER_COLOR);
                    pane.setBorder(CasePane.SELECTED_COLOR);
                } // Si il y seulement un carreau d'écart (haut, bas, doite, gauche mais pas diagonale)
                else if (pane != this.caseSelected
                        && (caseSelected.x - pane.x) <= 1 && (caseSelected.x - pane.x) >= -1 && (caseSelected.y - pane.y) == 0
                        || (caseSelected.y - pane.y) <= 1 && (caseSelected.y - pane.y) >= -1 && (caseSelected.x - pane.x) == 0) {
                    Case c1 = grid.getCase(caseSelected.x, caseSelected.y);
                    Case c2 = grid.getCase(pane.x, pane.y);

                    UpdateAgregation updtAgreg = new UpdateAgregation(c1);
                    updtAgreg.playCases(c2); // Test les 2 cases choisies par l'utilisateur
                    this.caseSelected.setBackground(CasePane.BACKGROUND_COLOR);
                    this.caseSelected.setBorder(CasePane.BORDER_COLOR);
                    this.caseSelected = null;
                } else {
                    this.caseSelected.setBackground(CasePane.BACKGROUND_COLOR);
                    this.caseSelected.setBorder(CasePane.BORDER_COLOR);
                    this.caseSelected = null;
                }
            } else {
                this.caseSelected.setBackground(CasePane.BACKGROUND_COLOR);
                this.caseSelected.setBorder(CasePane.BORDER_COLOR);
                this.caseSelected = null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "La partie est terminée");
        }
    }

    @Override
    public void mouseClicked(MouseEvent ev) {
    }

    @Override
    public void mousePressed(MouseEvent ev) {
    }

    @Override
    public void mouseEntered(MouseEvent ev) {
        CasePane pane = (CasePane) ev.getSource();
        pane.setBackground(CasePane.HOVER_COLOR);
    }

    @Override
    public void mouseExited(MouseEvent ev) {
        if (ev.getSource() != this.caseSelected) {
            CasePane pane = (CasePane) ev.getSource();
            pane.setBackground(CasePane.BACKGROUND_COLOR);
        }
    }

}
