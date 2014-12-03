package controller;

import model.Grid;
import model.PointsCounter;
import view.MainScreen;

public class MainController {

    public MainController() {
        Grid g = new Grid(8, 8);
        MainScreen MScreen = new MainScreen();
        g.initGrid();
        MScreen.addGridListener(new GridController(g), g);
        PointsController pc = new PointsController(MScreen.getLabPoints());
        MScreen.setVisible(true);
        
    }

}
