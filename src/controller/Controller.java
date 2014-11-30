/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Case;
import model.Grid;
import model.Shape;
import model.Type;
import view.CasePane;
import view.MainScreen;

/**
 *
 * @author Patu
 */
public class Controller {
    
    public Controller(){
        Case c = new Case(Shape.YELLOW, Type.NORMAL);
        CasePane p = new CasePane();
        c.addObserver(p);
        c.regenerate(Shape.RED, Type.BOMB);
        Grid g = new Grid(10, 10);
        g.initGrid();
        System.out.println(g);
        MainScreen MScreen = new MainScreen(g);
    }
    
    
}
