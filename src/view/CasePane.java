package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Case;
import model.Shape;
import model.Type;

public class CasePane extends JPanel implements Observer {
    public int x; // Public pas très sécurisé, mais plus simple
    public int y;
    public CasePane() {
        x = 0;
        y = 0;
    }

    public void init(int x, int y, Shape shape) {
        this.x = x;
        this.y = y;
        this.setBackground(shape.getColor());
    }
    

    @Override
    public void update(Observable obs, Object obj) {
        if (obs instanceof Case) {
            Case c = (Case) obs;
            if(c.getType() != Type.EMPTY){
                this.setBackground(c.getShape().getColor());
            }
            else{
                this.setBackground(Color.BLACK);
            }
        }
    }

}
