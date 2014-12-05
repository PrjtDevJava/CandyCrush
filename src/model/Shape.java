package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public enum Shape {

    DIAMANT(new Color(222, 217, 185), "diamant"),
    SAPHIR(new Color(222, 217, 185), "saphir"),
    RUBIS(new Color(222, 217, 185), "rubis"),
//    AGATHE(new Color(222, 217, 185), "agathe"),
//    CRISTAL(new Color(222, 217, 185), "cristal"),
//    AIGUEMARINE(new Color(222, 217, 185), "aiguemarine"),
//    TOPAZE(new Color(222, 217, 185), "topaze"),
    EMERAUDE(new Color(222, 217, 185), "emeraude");
    Color color;
    BufferedImage image;

    Shape(Color color, String fichier) {
        try {
            this.color = color;
            this.image = ImageIO.read(new File("./src/images/" + fichier + ".jpg"));
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
    
    public Border getBorder() {
        return BorderFactory.createLineBorder(new Color(211, 204, 160), 1);
    }

}
