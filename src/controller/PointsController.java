package controller;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import model.PointsCounter;
import model.UpdateAgregation;

public class PointsController implements Observer {

    private JLabel labP;

    public PointsController(JLabel labPoints) {
        this.labP = labPoints;
        PointsCounter pc = new PointsCounter();
        pc.addObserver(this);
        UpdateAgregation.setPointsCounter(pc);
        labP.setText("0");
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PointsCounter) {
            labP.setText(((PointsCounter) o).toString());
        }
    }

}
