package model;

public class Grid {

    private final Case matrix[][];
    private int x;  // Largeur
    private int y;  // Hauteur

    public Grid(int x, int y) {
        this.matrix = new Case[y][x];
        this.x = x;
        this.y = y;
    }

    public void applyGravity() {
        for (int i = 0; i < this.x; i++) {
            for (int j = this.y - 1; j > 0; j--) {
                if (this.matrix[j][i].getType() == Type.EMPTY) {
                    for (int k = j; k > 0; k--) {
                        this.matrix[k][i].regenerate(this.matrix[k - 1][i]);
                    }
                    this.matrix[0][i].regenerate(new Case(Type.NORMAL));
                }
            }
        }
    }

    public void initGrid() {
        for (int j = 0; j < this.y; j++) {
            for (int i = 0; i < this.x; i++) {
                this.matrix[j][i] = new Case(Type.NORMAL);
            }
        }
    }

    public Case getCase(int x, int y) {
        if (x < this.x && y < this.y) {
            return matrix[y][x];
        }
        return null;
    }

    public Case[][] getMatrix() {
        return matrix;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
