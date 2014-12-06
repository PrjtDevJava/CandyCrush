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

    public void applyGravity() {
        for (int i = 0; i < this.x; i++) {
            for (int j = this.y - 1; j > 0; j--) {
                if (this.matrix[j][i].getType() == Type.EMPTY) {
                    for (int k = j; k > 0; k--) {
                        this.matrix[k][i].regenerate(this.matrix[k - 1][i]);
                    }
                    this.matrix[0][i].regenerate(new Case(i, j, Type.NORMAL, this));
                }
            }
        }
    }

    public void initGrid() {
        for (int j = 0; j < this.y; j++) {
            for (int i = 0; i < this.x; i++) {
                this.matrix[j][i] = new Case(i, j, Type.NORMAL, this);
            }
        
        }
    }

    public Case getCase(int x, int y) {
        if (x >= 0 && x < this.x && y >= 0 &&y < this.y) {
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
