package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.Case;
import model.Shape;
import model.Type;

public class CasePane extends JPanel implements Observer {

    public int x; // Public pas très sécurisé, mais plus simple
    public int y;
    private BufferedImage image;

    public CasePane() {
        x = 0;
        y = 0;
    }

    public void init(int x, int y, Shape shape) {
        this.x = x;
        this.y = y;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setBackground(shape.getColor());
        this.image = shape.getImage();
    }

    @Override
    public void update(Observable obs, Object obj) {
        if (obs instanceof Case) {
            Case c = (Case) obs;
            if (c.getType() != Type.EMPTY && c.getShape() != null) {
                this.setBackground(c.getShape().getColor());
                this.image = c.getShape().getImage();
            } else {
                this.setBackground(Color.BLACK);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }

}
