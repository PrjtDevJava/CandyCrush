/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sylvio
 */
public class Params implements Serializable{
    public static int casesX = 8;
    public static int casesY = 8;
    public static int nbShape = 4;
    
    
    public static void saveParams(){
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream("config.cfg");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(Params.casesX);
            oos.writeObject(Params.casesY);
            oos.writeObject(Params.nbShape);
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
    
        public static void loadParams(){
            ObjectInputStream ois = null;
            try {
                final FileInputStream file = new FileInputStream("config.cfg");
                ois = new ObjectInputStream(file);
                Params.casesX = (Integer)ois.readObject();
                Params.casesY = (Integer)ois.readObject();
                Params.nbShape = (Integer)ois.readObject();
                
            } catch (final java.io.IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(Params.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ois != null) {
                ois.close();
            }
            } catch (final IOException ex) {
                ex.printStackTrace();
            } 
        }
    }
    
    
    public static void defaultParam(){
        Params.casesX = 8;
        Params.casesY = 8;
        Params.nbShape = 4;
    }
    
    
}
