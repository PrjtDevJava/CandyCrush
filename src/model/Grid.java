package model;

public class Grid {

    private final Case matrix[][];
    int x;
    int y;

    public Grid(int x, int y) {
        this.matrix = new Case[x][y];
        this.x = x;
        this.y = y;
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
                if (j % 3 == 0) {
                    this.matrix[i][j] = new Case(Shape.RED, Type.NORMAL);
                } else if (j % 3 == 0) {
                    this.matrix[i][j] = new Case(Shape.BLUE, Type.NORMAL);
                } else {
                    this.matrix[i][j] = new Case(Shape.GREEN, Type.NORMAL);
                }
            }
        }
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                str += " " + i + j + " ";
            }
            str += "\n";
        }
        return "Grid : \n" + str;
    }

}
