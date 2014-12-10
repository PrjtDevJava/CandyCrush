package model;

import java.io.Serializable;

public class Grid implements Serializable {

    private Case matrix[][];
    private final int x;  // Largeur
    private final int y;  // Hauteur
    private final int nbShape;  // Hauteur

    public Grid(int x, int y, int nbShape) {
        this.matrix = new Case[y][x];
        this.x = x;
        this.y = y;
        this.nbShape = nbShape;
    }

    public void initGrid() {
        boolean ok;
        for (int j = 0; j < this.y; j++) {
            for (int i = 0; i < this.x; i++) {
                do {
                    ok = true;
                    this.matrix[j][i] = new Case(i, j, Type.NORMAL, this);
                    if (i > 1 && this.matrix[j][i].equivalent(this.matrix[j][i - 1]) && this.matrix[j][i - 1].equivalent(this.matrix[j][i - 2])) {
                        ok = false;
                    }
                    if (j > 1 && this.matrix[j][i].equivalent(this.matrix[j - 1][i]) && this.matrix[j - 1][i].equivalent(this.matrix[j - 2][i])) {
                        ok = false;
                    }
                } while (!ok);
            }
        }
    }

    public void changeGrid() {
        boolean ok;
        for (int j = 0; j < this.y; j++) {
            for (int i = 0; i < this.x; i++) {
                do {
                    ok = true;
                    this.matrix[j][i].regenerate(Type.NORMAL);
                    if (i > 1 && this.matrix[j][i].equivalent(this.matrix[j][i - 1]) && this.matrix[j][i - 1].equivalent(this.matrix[j][i - 2])) {
                        ok = false;
                    }
                    if (j > 1 && this.matrix[j][i].equivalent(this.matrix[j - 1][i]) && this.matrix[j - 1][i].equivalent(this.matrix[j - 2][i])) {
                        ok = false;
                    }
                } while (!ok);
            }
        }

    }

    public void changeGrid(Grid g) {
        for (int j = 0; j < this.y; j++) {
            for (int i = 0; i < this.x; i++) {
                this.matrix[j][i].regenerate(g.getCase(i, j));
            }
        }
    }

    public Case getCase(int x, int y) {
        if (x >= 0 && x < this.x && y >= 0 && y < this.y) {
            return matrix[y][x];
        }
        return null;
    }

    public Case[][] getMatrix() {
        return matrix;
    }

    public int getWidth() {
        return x;
    }

    public int getHeight() {
        return y;
    }

    public int getNbShape() {
        return nbShape;
    }

    @Override
    public String toString() {
        String str = "";
        for (int j = 0; j < this.y; j++) {
            for (int i = 0; i < this.x; i++) {
                str += this.matrix[j][i];
            }
            str += "\n";
        }
        return "Grid : \n" + str;
    }
}
