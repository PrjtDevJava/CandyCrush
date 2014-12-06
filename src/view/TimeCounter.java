package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimeCounter extends JPanel {

    private static Font f = new Font("Book Antiqua", Font.BOLD, 25);

    /**
     * timer : timer servant à décrémenter le chronometre
     */
    private Timer timer;

    /**
     * couleur : couleur de fond du chronometre
     */
    private Color color = Color.orange;

    /**
     * tempsRestant : temps restant
     */
    private int timeRest;

    /**
     * temps : temps initial
     */
    private int time;

    /**
     * Construction du chronometre
     *
     * @param N : le nombre de secondes initial
     */
    public TimeCounter(int N) {
        //timer = createTimer ();
        //timer.start();
        setOpaque(false);
        setPreferredSize(new Dimension(72, 72));
        this.setTimeRest(N);
        this.setTime(N);
    }

    public TimeCounter(int N, Color couleur) {
        this.color = couleur;
        //timer = createTimer ();
        timer.start();
        setOpaque(false);
        setPreferredSize(new Dimension(72, 72));
        this.setTimeRest(N);
        this.setTime(N);
    }

    @Override
    public void paintComponent(Graphics g) {
        this.drawCircle(g, 35, 35, 30);
    }

    /**
     * Fonction qui permet de dessiner le chronometre
     *
     * @param cg : element graphique
     * @param xCenter : abscice du centre du cercle
     * @param yCenter : ordonnee du centre du cercle
     * @param r : rayon du cercle
     */
    public void drawCircle(Graphics cg, int xCenter, int yCenter, int r) {
        cg.setColor(Color.white);
        cg.fillOval(xCenter - r, yCenter - r, 2 * r, 2 * r);
        cg.setColor(color);
        cg.fillArc(xCenter - r, yCenter - r, 2 * r, 2 * r, 90, -(360 - timeRest * 360 / time));
        cg.setColor(Color.black);
        cg.setFont(f);
        if (timeRest > 9) {
            cg.drawString("" + this.timeRest, 24, 42);
        } else {
            cg.drawString("0" + this.timeRest, 24, 42);
        }
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getTimeRest() {
        return timeRest;
    }

    public void setTimeRest(int timeRest) {
        this.timeRest = timeRest;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
