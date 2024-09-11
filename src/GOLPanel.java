import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GOLPanel extends JPanel {
    private GOLMatrix world;
    private JPanel buttons, gridHolder;
    private JButton cmdGo, cmdClear, cmdNext, cmdFaster, cmdSlower;
    private JButton[][] grid;
    private JLabel lblGeneration;
    private Timer timer;
    private int interval, worldSize;

    // constructor for the game of life panel
    public GOLPanel(int worldSize) {

        // initialize the 5 main buttons and give them the corresponding text as well the world and interval
        this.world = new GOLMatrix(worldSize);
        this.worldSize = world.getSize();
        this.interval = 200;
        this.cmdNext = new JButton("Next");
        this.cmdGo = new JButton("Go");
        this.cmdClear = new JButton("Clear");
        this.cmdFaster = new JButton("Faster");
        this.cmdSlower = new JButton("Slower");
        this.lblGeneration = new JLabel("Number of generations: 0");
        // enable and disable the bottoms by what instructed
        this.cmdNext.setEnabled(true);
        this.cmdGo.setEnabled(true);
        this.cmdClear.setEnabled(true);
        this.cmdFaster.setEnabled(false);
        this.cmdSlower.setEnabled(false);
        // build an action listener and pause the timer and given it interval as the starting speed
        Listener lis = new Listener();
        this.timer = new Timer(interval, lis);
        timer.stop();
        // place the 5 buttons and the label in the panel buttons
        this.buttons = new JPanel();
        this.buttons.add(lblGeneration);
        this.buttons.add(cmdGo);
        this.buttons.add(cmdNext);
        this.buttons.add(cmdClear);
        this.buttons.add(cmdFaster);
        this.buttons.add(cmdSlower);
        // build the layout of the main panel that the user will use
        this.gridHolder = new JPanel();
        this.grid = new JButton[this.worldSize][this.worldSize];
        setGrid(this.worldSize);
        setLayout(new BorderLayout());
        add(gridHolder, BorderLayout.CENTER);
        add(buttons, BorderLayout.NORTH);
        // connect the action listener and the buttons
        this.cmdNext.addActionListener(lis);
        this.cmdGo.addActionListener(lis);
        this.cmdClear.addActionListener(lis);
        this.cmdFaster.addActionListener(lis);
        this.cmdSlower.addActionListener(lis);
        cmdClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        cmdClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });
    }

    // make a grid layout and place in each button that will initialize with a white background and will respond from
    // the action listener
    private void setGrid(int size) {
        this.gridHolder.setLayout(new GridLayout(worldSize, worldSize));
        Listener lis = new Listener();
        // create buttons and add them to the grid panel
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                grid[i][j] = new JButton();
                grid[i][j].setBackground(Color.white);
                grid[i][j].addActionListener(lis);
                this.gridHolder.add(grid[i][j]);
            }
    }

    // a private class that will work with GOLPanel that will get every action that the user did
    // and depend on the action this class will make it append
    private class Listener extends MouseAdapter implements ActionListener {
        // make that will update the world were the user clicked on the grid buttons
        private void connect(int size, ActionEvent E) {
            for (int row = 0; row < size; row++)
                for (int cull = 0; cull < size; cull++)
                    if (grid[row][cull] == E.getSource())
                        world.flipCell(row, cull);
        }

        // change the color of a button in the grid update the world and the label
        private void nextGen() {
            world.nextGeneration();
            boolean[][] temp = world.getWorld();
            int size = worldSize;
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    if (temp[i][j])
                        grid[i][j].setBackground(Color.blue);
                    else
                        grid[i][j].setBackground(Color.white);
                }
            lblGeneration.setText("Number of generations: " + world.getGeneration());
        }

        // this function gets the user action and give each button the right out put
        public void actionPerformed(ActionEvent E) {
            final int SPEED = 20;
            boolean move;
            Color white = Color.white;
            Color blue = Color.blue;
            // this if is for the go and stop button and depend on the state of the button it will enable\disable
            // the right buttons and change the button text
            if (E.getSource() == cmdGo) {
                if (cmdGo.getText().equals("Go")) {
                    timer.start();
                    cmdGo.setText("Stop");
                    move = true;
                } else {
                    timer.stop();
                    cmdGo.setText("Go");
                    move = false;
                }
                cmdSlower.setEnabled(move);
                cmdFaster.setEnabled(move);
                cmdClear.setEnabled(!move);
                cmdNext.setEnabled(!move);
            }
            // this if is response for the run of the generations without pressing the next button
            // and for that there will be interference there is the other question
            if (timer.isRunning() && !(E.getSource() instanceof JButton))
                nextGen();
            // the next 2 if and if else response for the speed of with the next generation will be. the fastest is
            // 100 ms included
            if (E.getSource() == cmdFaster && timer.getDelay() > 100)
                timer.setDelay((timer.getDelay() - SPEED));

            else if (E.getSource() == cmdSlower)
                timer.setDelay((timer.getDelay() + SPEED));
                // this else if is response for moving the generation by 1 each time the button next is pressed
            else if (E.getSource() == cmdNext)
                nextGen();
                // reset the world and generation
            else if (E.getSource() == cmdClear) {
                world.clearWord();
                for (int i = 0; i < worldSize; i++)
                    for (int j = 0; j < worldSize; j++)
                        if (grid[i][j].getBackground().equals(blue))
                            grid[i][j].setBackground(white);
                timer.setDelay(interval);
                lblGeneration.setText("Number of generations: " + world.getGeneration());
            }
            // change the color of the button in the grid
            else if (E.getSource() instanceof JButton) {
                if (((JButton) E.getSource()).getBackground().equals(white)) {
                    ((JButton) E.getSource()).setBackground(blue);
                    connect(worldSize, E);
                } else if (((JButton) E.getSource()).getBackground().equals(blue)) {
                    ((JButton) E.getSource()).setBackground(white);
                    connect(worldSize, E);
                }
            }
        }
    }
}
