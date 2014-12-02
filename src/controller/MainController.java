package controller;

import model.Grid;
import view.MainScreen;

public class MainController {

    public MainController() {
        Grid g = new Grid(8, 8);
        MainScreen MScreen = new MainScreen();
        g.initGrid();
        MScreen.addGridListener(new GridController(g), g);
        MScreen.setVisible(true);
    }

}
