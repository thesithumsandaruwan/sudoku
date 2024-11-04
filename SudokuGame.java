import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class SudokuGame {
    private int[][] solutionGrid;
    private int[][] gameGrid;
    private JTextField[][] textFields;
    private JFrame frame;
    private JPanel gridPanel;
    private JButton newButton, resetButton, showSolutionButton;
    private String difficulty;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuGame().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Sudoku Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Difficulty selection
        String[] options = {"Easy", "Medium", "Hard"};
        difficulty = (String) JOptionPane.showInputDialog(frame, "Select difficulty level:",
                "Difficulty Selection", JOptionPane.PLAIN_MESSAGE, null, options, "Easy");

        // Generate grids
        generateSolutionGrid();
        generateGameGrid();

        // Set up GUI components
        gridPanel = new JPanel(new GridLayout(9, 9));
        textFields = new JTextField[9][9];

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField tf = new JTextField();
                tf.setHorizontalAlignment(JTextField.CENTER);
                if (gameGrid[row][col] != 0) {
                    tf.setText(String.valueOf(gameGrid[row][col]));
                    tf.setEditable(false);
                    tf.setBackground(Color.LIGHT_GRAY);
                } else {
                    tf.setEditable(true);
                    tf.addKeyListener(new KeyAdapter() {
                        public void keyReleased(KeyEvent e) {
                            String text = tf.getText();
                            if (!text.matches("[1-9]?")) {
                                tf.setText("");
                            } else {
                                int row = -1, col = -1;
                                // Find the position of this text field
                                for (int i = 0; i < 9; i++) {
                                    for (int j = 0; j < 9; j++) {
                                        if (textFields[i][j] == tf) {
                                            row = i;
                                            col = j;
                                        }
                                    }
                                }
                                if (text.isEmpty()) {
                                    tf.setBackground(Color.WHITE);
                                } else if (Integer.parseInt(text) != solutionGrid[row][col]) {
                                    tf.setBackground(Color.RED);
                                } else {
                                    tf.setBackground(Color.WHITE);
                                }
                            }
                        }
                    });
                }
                textFields[row][col] = tf;
                gridPanel.add(tf);
            }
        }

        // Buttons
        newButton = new JButton("New");
        resetButton = new JButton("Reset");
        showSolutionButton = new JButton("Show Solution");

        newButton.addActionListener(e -> {
            frame.dispose();
            new SudokuGame().createAndShowGUI();
        });

        resetButton.addActionListener(e -> {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (gameGrid[row][col] == 0) {
                        textFields[row][col].setText("");
                        textFields[row][col].setBackground(Color.WHITE);
                    }
                }
            }
        });

        showSolutionButton.addActionListener(e -> {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (gameGrid[row][col] == 0) {
                        textFields[row][col].setText(String.valueOf(solutionGrid[row][col]));
                        textFields[row][col].setEditable(false);
                        textFields[row][col].setBackground(Color.LIGHT_GRAY);
                    }
                }
            }
            resetButton.setEnabled(false);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(showSolutionButton);

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    private void generateSolutionGrid() {
        // Simple hardcoded solution; replace with a generator for full functionality
        solutionGrid = new int[][]{
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
    }

    private void generateGameGrid() {
        // Copy the solution grid
        gameGrid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(solutionGrid[i], 0, gameGrid[i], 0, 9);
        }

        // Remove numbers based on difficulty
        int clues = getNumberOfClues();
        int cellsToRemove = 81 - clues;
        Random rand = new Random();

        while (cellsToRemove > 0) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (gameGrid[row][col] != 0) {
                gameGrid[row][col] = 0;
                cellsToRemove--;
            }
        }
    }

    private int getNumberOfClues() {
        switch (difficulty) {
            case "Easy":
                return 35 + new Random().nextInt(11); // 35-45
            case "Medium":
                return 30 + new Random().nextInt(11); // 30-40
            case "Hard":
                return 25 + new Random().nextInt(11); // 25-35
            default:
                return 35;
        }
    }
}