package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
public enum Shape {

    DIAMANT("diamant"),
    SAPHIR("saphir"),
    RUBIS("rubis"),
    EMERAUDE("emeraude"),
    AGATHE("agathe"),
    CRISTAL("cristal"),
    AIGUEMARINE("aiguemarine"),
    TOPAZE("topaze");
    BufferedImage image;

    Shape(String fichier) {
        try {
            this.image = ImageIO.read(new File("./src/images/" + fichier + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Shape.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

}
