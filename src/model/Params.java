/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Sylvio
 */
public class Params implements Serializable{
    public int casesX;
    public int casesY;
    public int nbShape;
    
    public Params(){
        
    }
    
    public void saveParams(){
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream("config.cfg");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(this);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                oos.flush();
                oos.close();
            }
            } catch (final IOException ex) {
                ex.printStackTrace();
            } 
        }
    }
    
    public void defaultParam(){
        this.casesX = 8;
        this.casesY = 8;
        this.nbShape = 4;
    }
    
}
