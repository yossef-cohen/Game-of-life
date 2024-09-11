import javax.swing.JFrame;

public class GameOfLife {
    public static void main(String[] args) {
        int worldSize = 20; // You can change this to adjust the size of the world

        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GOLPanel(worldSize));
        frame.pack();
        frame.setVisible(true);
    }
}