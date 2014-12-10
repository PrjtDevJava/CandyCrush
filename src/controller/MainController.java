package controller;

import java.io.IOException;
import model.Grid;
import model.Params;
import view.MainScreen;
import view.OptionScreen;

public class MainController {

    public MainController() throws IOException {
        Grid g = new Grid(Params.casesX, Params.casesY, Params.nbShape);
        MainScreen MScreen = new MainScreen(Params.casesX, Params.casesY, Params.time);
        g.initGrid();
        MScreen.addGridListener(new GridController(g), g);
        PointsController pc = new PointsController(MScreen.getLabPoints());
        MScreen.addItemMenuListener(new MenuController(g, pc.pointCounter, MScreen));
        MScreen.addMenuListener(new MenuController(g, pc.pointCounter, MScreen));
        OptionScreen optScreen = new OptionScreen();
        MScreen.addOptionScreen(optScreen);
        MScreen.setVisible(true);
    }

}
