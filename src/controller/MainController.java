package controller;

import java.io.IOException;
import model.Grid;
import view.MainScreen;

public class MainController {

    public MainController(int x, int y, int nbShape) throws IOException {
        Grid g = new Grid(x, y, nbShape);
        MainScreen MScreen = new MainScreen(x, y);
        g.initGrid();
        MScreen.addGridListener(new GridController(g), g);
        PointsController pc = new PointsController(MScreen.getLabPoints());
        MScreen.setVisible(true);
    }

}
