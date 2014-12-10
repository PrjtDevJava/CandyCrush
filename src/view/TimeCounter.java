package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.utils.TimeMS;

public class TimeCounter extends JPanel implements Serializable {

    private static Font f = new Font(null, 0, 18);
    private Timer timer;
    private TimeMS timeRest;
    private int time;

    /**
     * Construction du chronometre
     *
     * @param N : le nombre de secondes initial
     */
    public TimeCounter(int N) {
        timer = createTimer();
        if (N != 0) {
            timer.start();
        }
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(72, 72));
        this.timeRest = new TimeMS(N);
        this.setTime(N);
        this.setLocation(50, 50);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
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
        cg.setColor(CasePane.BACKGROUND_COLOR);
        cg.fillOval(xCenter - r, yCenter - r, 2 * r, 2 * r);
        if (timeRest.timeToSec() < (time / 4)) {
            cg.setColor(Color.RED);
        } else if (timeRest.timeToSec() < (time * (2f / 3f))) {
            cg.setColor(Color.ORANGE);
        } else {
            cg.setColor(Color.GREEN);
        }

        cg.fillArc(xCenter - r, yCenter - r, 2 * r, 2 * r, 90, -(360 - timeRest.timeToSec() * 360 / time));
        cg.setColor(new Color(50, 50, 50));
        cg.setFont(f);

        cg.drawString(this.timeRest.toString(), 24, 42);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
        this.repaint();
    }

    public int getTimeRest() {
        return this.timeRest.timeToSec();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        this.timeRest.setTime(time);
    }

    
    public void setTimeRest(int timeRest) {
        this.timeRest.setTime(timeRest);    
    }
    
    private Timer createTimer() {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (timeRest.timeToSec() > 0) {
                    timeRest.substractTime(1);
                    repaint();
                } else {
                    timer.stop();
                }
            }
        };
        return new Timer(1000, action);
    }
}
