package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Grid;

public class MenuController implements ActionListener {

    private Grid grid;
    private PointsController point;

    MenuController(Grid grid, PointsController pc) {
        this.grid = grid;
        this.point = pc;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.grid.changeGrid();
        this.point.pointCounter.setPoints(0);
    }

}
