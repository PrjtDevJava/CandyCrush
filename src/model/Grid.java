package model;

public class Grid {

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
                    if (i > 1 && this.matrix[j][i].equals(this.matrix[j][i - 1]) && this.matrix[j][i - 1].equals(this.matrix[j][i - 2])) {
                        ok = false;
                    }
                    if (j > 1 && this.matrix[j][i].equals(this.matrix[j - 1][i]) && this.matrix[j - 1][i].equals(this.matrix[j - 2][i])) {
                        ok = false;
                    }
                } while (!ok);
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
