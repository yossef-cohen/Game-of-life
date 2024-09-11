# Game of Life

This is a Java implementation of Conway's Game of Life, a cellular automaton simulation.

## Files

- `GOLMatrix.java`: Implements the core logic of the Game of Life.
- `GOLPanel.java`: Provides the graphical user interface for the simulation.
- `GameOfLife.java`: Contains the main method to run the application.

## How to Run

1. Compile all Java files:
   ```
   javac *.java
   ```

2. Run the GameOfLife class:
   ```
   java GameOfLife
   ```

## Controls

- **Go/Stop**: Starts or stops the automatic generation progression.
- **Next**: Advances the simulation by one generation.
- **Clear**: Resets the grid and generation count.
- **Faster**: Increases the speed of automatic generation progression.
- **Slower**: Decreases the speed of automatic generation progression.

## Customization

You can adjust the size of the world by changing the `worldSize` variable in the `GameOfLife.java` file.

## Rules

The Game of Life follows these rules:

1. Any live cell with fewer than two live neighbors dies (underpopulation).
2. Any live cell with two or three live neighbors lives on to the next generation.
3. Any live cell with more than three live neighbors dies (overpopulation).
4. Any dead cell with exactly three live neighbors becomes a live cell (reproduction).

Enjoy exploring the fascinating patterns of Conway's Game of Life!
