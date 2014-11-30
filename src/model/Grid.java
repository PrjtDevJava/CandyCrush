package model;

public class Grid {

    private final Case matrix[][];
    private int x;
    private int y;

    public Grid(int x, int y) {
        this.matrix = new Case[x][y];
        this.x = x;
        this.y = y;
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
    
    public Case getCase(int i, int j){
        if(i < x && j < y){
            return matrix[i][j];
        }
        return null;
    }
    
    public void applyGravity() {
        for (int j = 0; j < this.y; j++) {
            int i = this.x - 1;
            while(this.matrix[i][j].getType() == Type.EMPTY){
                
                i--;
            }
        }
    }

    public void initGrid() {
        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < this.y; j++) {
                int numRand = 0 + (int)(Math.random() * (((Shape.values().length-1) - 0) + 1));
                this.matrix[i][j] = new Case(Shape.values()[numRand], Type.NORMAL);
            }
        }
    }
    
    
    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(this.matrix[i][j] == null){
                    str += " -1 ";
                }
                else
                    str += " " + i + j + " ";
            }
            str += "\n";
        }
        return "Grid : \n" + str;
    }
    
}
