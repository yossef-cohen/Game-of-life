public class GOLMatrix {
    private boolean[][] world;
    private int generation;
    private final int size;

    // constructor that get an int and build a nxn boolean matrix if the number is less than 3 make the matrix 3x3
    // restart generations to 0 and make size equals to n
    public GOLMatrix(int n) {
        if (n < 3)
            n = 3;

        world = new boolean[n][n];
        generation = 0;
        size = n;
    }

    // flip the cell from true to false or vise versa
    public void flipCell(int row, int col) {
        if ((row >= 0 && row < size) && (col >= 0 && col < size))
            world[row][col] = !world[row][col];
    }

    // return the matrix of GOLMatrix class
    public boolean[][] getWorld() {
        boolean[][] copy = new boolean[size][size];
        for (int i = 0; i < size; i++)
            System.arraycopy(world[i], 0, copy[i], 0, size);
        return copy;
    }

    // return the curren number that generation currently holds
    public int getGeneration() {
        return generation;
    }

    // getter for the size of the world
    public int getSize() {
        return size;
    }

    // reset the matrix and the generation
    public void clearWord() {
        this.world = new boolean[size][size];
        this.generation = 0;
    }

    // check the neighbors of each cell with another double for that run maximum of 8 time
    // and depend on the amount of neighbors flips the cells of world by the
    // rules of the game of life
    public void nextGeneration() {
        GOLMatrix nextgen = new GOLMatrix(size);

        int counter = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                for (int k = i - 1; k < i + 2; k++) {
                    if (k >= 0 && k < size)
                        for (int l = j - 1; l < j + 2; l++)
                            if (l >= 0 && l < size && (k != i || j != l) && world[k][l])
                                counter++;
                }

                if ((counter == 2 || counter == 3) && world[i][j])
                    nextgen.flipCell(i, j);

                else if (counter == 3)
                    nextgen.flipCell(i, j);

                counter = 0;
            }

        generation++;
        this.world = nextgen.getWorld();
    }
}