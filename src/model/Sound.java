/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Sylvio
 */
//public class Sound{
//    public static synchronized void playSound(final String url) {
//      new Thread(new Runnable() {
//        public void run() {
////            AudioClip clip; 
////            try {
////                clip = Applet.newAudioClip(new URL ("file", "localhost", "D://piece.wav"));
////                 clip.play();
////            } catch (MalformedURLException ex) {
////                Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
////            }
//           
////          try {
////            Clip clip = AudioSystem.getClip();
////            URL url = this.getClass().getResource("./src/sons/piece.wav");
////            AudioInputStream aiStream = AudioSystem.getAudioInputStream(url);   
//////            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
//////              Sound.class.getResourceAsStream("D://piece.wav"));
////            //clip.open(aiStream);
////            //clip.start(); 
////          } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
////            System.err.println(e.getMessage());
////          }
////        }
//
////          private URL URL(String filecblahfoowav) {
////              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////          }
//      }).start();
//    }
//}
