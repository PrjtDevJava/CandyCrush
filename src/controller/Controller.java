package controller;

import model.Grid;
import view.MainScreen;

public class Controller {

    public Controller() {
        Grid g = new Grid(8, 8);
        g.initGrid();
        MainScreen MScreen = new MainScreen(g);
    }

}
