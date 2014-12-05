package view;

import java.awt.Color;
import java.awt.Cursor;
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
    public static int BORDER_SIZE = 1;
    public static Color BACKGROUND_COLOR = new Color(222, 217, 185);
    public static Color HOVER_COLOR = new Color(242, 237, 205);
    public static Color BORDER_COLOR = new Color(212, 207, 175);
    public static Color SELECTED_COLOR = new Color(182, 177, 145);
    private BufferedImage image;

    public CasePane() {
        x = 0;
        y = 0;
    }

    public void init(int x, int y, final Shape shape) {
        this.x = x;
        this.y = y;
        this.image = shape.getImage();
        this.setBackground(BACKGROUND_COLOR);
        this.setBorder(BORDER_COLOR);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void setBorder(Color color) {
        this.setBorder(BorderFactory.createLineBorder(color, BORDER_SIZE));
    }

    @Override
    public void update(Observable obs, Object obj) {
        if (obs instanceof Case) {
            Case c = (Case) obs;
            if (c.getType() != Type.EMPTY && c.getShape() != null) {
                this.setBackground(BACKGROUND_COLOR);
                this.image = c.getShape().getImage();
            } else {
                this.setBackground(Color.DARK_GRAY);
                this.image = null;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

}
