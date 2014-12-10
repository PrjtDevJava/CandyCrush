package controller;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import model.PointsCounter;
import model.UpdateAgregation;

public class PointsController implements Observer {

    private JLabel labP;
    public PointsCounter pointCounter;

    public PointsController(JLabel labPoints) {
        this.labP = labPoints;
        this.pointCounter = new PointsCounter();
        pointCounter.addObserver(this);
        UpdateAgregation.setPointsCounter(pointCounter);
        labP.setText(pointCounter.toString());
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof PointsCounter) {
            labP.setText(((PointsCounter) o).toString());
        }
    }

}
