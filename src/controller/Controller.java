package controller;

import model.Case;
import model.Grid;
import model.Shape;
import model.Type;
import view.CasePane;
import view.MainScreen;

public class Controller {

    public Controller() {
        Case c = new Case(Shape.YELLOW, Type.NORMAL);
        CasePane p = new CasePane();
        c.addObserver(p);
        c.regenerate(new Case(Shape.RED, Type.NORMAL));
        Grid g = new Grid(7, 7);
        g.initGrid();
        System.out.println(g);
        g.applyGravity();
        System.out.println(g);
        MainScreen MScreen = new MainScreen(g);
    }

}
