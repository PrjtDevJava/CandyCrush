package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import static model.Case.NB_CASE_POINT;

public class UpdateAgregation extends java.lang.Thread {

    public static int nbThread = 0;
    private static Set<Integer> setColumnToUpdGrav = Collections.synchronizedSet(new HashSet());
    private static PointsCounter pc = null;

    private Case c1;

    public UpdateAgregation(Case c) {
        this.c1 = c;
    }

    private static synchronized void incrementThread() {
        nbThread++;
    }

    private static synchronized void decrementThread(Case c) {
        nbThread--;
        if (nbThread == 0) {
            //System.out.println("Send--------------");
            for (Integer it : setColumnToUpdGrav) {
                new UpdateGravity(it, c.getGrid()).start();
            }
            //System.out.println("Column to update grav : " + setColumnToUpdGrav);
            setColumnToUpdGrav.clear();
        }
    }

    public static synchronized void setPointsCounter(PointsCounter pc) {
        UpdateAgregation.pc = pc;
    }

    public static synchronized void addPoints(int points) {
        UpdateAgregation.pc.addPoints(points);
    }

    /**
     * Cette fonction permet jouer les cases utilisateur en mode synchrone
     *
     * @param c2 : La 2ème case que l'utilisateur à choisi
     * @return true si au moins une des cases utilisateur a été "aggrégée"
     */
    public boolean playCases(Case c2) {
        incrementThread();
        incrementThread();
        boolean goodMove = false;
        Shape tmpShape = c1.getShape();
        c1.setShape(c2.getShape());
        c2.setShape(tmpShape);

        if (this.agregation(this.c1) != 0) {
            goodMove = true;
        }
        if (this.agregation(c2) != 0) {
            goodMove = true;
        }
        if (goodMove) {
            c1.changeShape(c1.getShape());
            c2.changeShape(c2.getShape());
        } else {
            c2.setShape(c1.getShape());
            c1.setShape(tmpShape);
        }
        decrementThread(c1);
        decrementThread(c2);
        return goodMove;
    }

    @SuppressWarnings("empty-statement")
    public int agregation(Case c) {
        int points = 0;
        if (c != null && c.getShape() != null && c.getType() != Type.EMPTY) {
            //System.out.println("Thread : " + Thread.currentThread().getId() + "  -> nb : " + nbThread);
            synchronized (c.getGrid()) {
                Grid grid = c.getGrid();
                //System.out.println("Thread UpdateAgregation -> nbThread : " + nbThread);

                int nbR; // Nombre de bonnes case à droite
                int nbL; // Nombre de bonnes case à gauche
                int nbT; // Nombre de bonnes case en haut
                int nbB; // Nombre de bonnes case en bas

                int i;
                for (i = c.getX() + 1; i < grid.getWidth() && grid.getCase(i, c.getY()).getShape() == c.getShape(); i++);
                nbR = i - (c.getX() + 1);
                for (i = c.getX() - 1; i >= 0 && grid.getCase(i, c.getY()).getShape() == c.getShape(); i--);
                nbL = i * (-1) + (c.getX() - 1);
                for (i = c.getY() + 1; i < grid.getHeight() && grid.getCase(c.getX(), i).getShape() == c.getShape(); i++);
                nbB = i - (c.getY() + 1);
                for (i = c.getY() - 1; i >= 0 && grid.getCase(c.getX(), i).getShape() == c.getShape(); i--);
                nbT = i * (-1) + (c.getY() - 1);
                //            System.out.println("Droit : " + nbR + " Gauche : " + nbL);
                //            System.out.println("Bas : " + nbB + " Haut : " + nbT);

                if ((nbR + nbL + 1) >= NB_CASE_POINT) { // +1 Pour compter la case actuelle
                    for (int j = c.getX() - nbL; j < (c.getX() + nbR + 1); j++) {
                        grid.getCase(j, c.getY()).changeType(Type.EMPTY);
                        grid.getCase(j, c.getY()).setShape(null);
                    }
                    points += (nbR + nbL) - 1;
                } else {
                    nbR = 0;
                    nbL = 0;
                }

                if ((nbT + nbB + 1) >= NB_CASE_POINT) {
                    for (int j = c.getY() - nbT; j < (c.getY() + nbB + 1); j++) {
                        grid.getCase(c.getX(), j).setShape(null);
                        grid.getCase(c.getX(), j).changeType(Type.EMPTY);
                    }
                    points += (nbT + nbB) - 1;
                }

                if (points > 0) {
                    UpdateAgregation.addPoints(points);
                    setColumnToUpdGrav.add(c.getX());
                    for (int j = c.getX() - nbL; j < (c.getX() + nbR + 1); j++) {
                        if (j != c.getX()) {
                            setColumnToUpdGrav.add(j);
                        }
                    }
                }
            }

            //System.out.println("Dell Thread : " + Thread.currentThread().getId() + "  -> nb : " + nbThread);
        }
        return points;
    }

    @Override
    public void run() {
        incrementThread();
        agregation(c1);
        decrementThread(c1);
    }
}
