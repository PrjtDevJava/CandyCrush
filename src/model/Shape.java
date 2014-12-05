package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public enum Shape {

    BLUE(Color.BLUE, "diamant"),
    GREEN(Color.GREEN, "emeraude"),
    GRIS(Color.WHITE, "fer"),
    JAUNE(Color.YELLOW, "or");
    Color color;
    BufferedImage image;

    Shape(Color color, String fichier) {
        try {
            this.color = color;
            this.image = ImageIO.read(new File("./src/images/" + fichier + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Shape.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Color getColor() {
        return color;
    }

    public BufferedImage getImage() {
        return image;
    }

}
