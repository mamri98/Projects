
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.Random;
import javax.swing.*;

public class Board extends JPanel {

    private final int CELL_SIZE = 15;                           // Pixel size of images
    private final int COVER_FOR_CELL = 10;
    private final int FLAG_FOR_CELL = 10;
    private final int EMPTY_CELL = 0;                           // 0 means empty cell
    private final int MINE_CELL = 9;                            // 9 means a mine cell
    private final int COVERED_MINE_CELL = MINE_CELL + COVER_FOR_CELL;           // covered mine cell
    private final int FLAGGED_MINE_CELL = COVERED_MINE_CELL + FLAG_FOR_CELL;

    private final int DRAW_MINE = 9;        // paint mine
    private final int DRAW_FLAG = 11;       // paint flag

    private final int DRAW_WRONG_FLAG = 12; // paint image 12(wrong flag)

    private int N_MINES;
    private int N_ROWS;
    private int N_COLS;

    private boolean FIRST_CLICK = true;
    private int[] field;                    // All the cells, each number represents its state
    private boolean inGame;
    private int minesLeft;
    private Image[] img;

    private int allCells;
    private final JLabel statusbar;         // The mines in the field, changes with difficulty


    public Board(JLabel statusbar) {

        this.statusbar = statusbar;
        initBoard();
    }

    private void initBoard() {                  // set difficulty and initialize board
        String[] possibleValues = {"Beginner", "Intermediate", "Expert"};
        String selectedValue = (String) JOptionPane.showInputDialog(null,
                "Choose one", "Input", JOptionPane.INFORMATION_MESSAGE,null,
                possibleValues, possibleValues[0]);
        if(Objects.equals(selectedValue, possibleValues[0])){
            N_MINES = 5;
            N_ROWS = 4;
            N_COLS = 4;
        }
        else if(Objects.equals(selectedValue, possibleValues[1])){
            N_MINES = 14;
            N_ROWS = 8;
            N_COLS = 8;
        }
        else if(Objects.equals(selectedValue, possibleValues[2])){
            N_MINES = 60;
            N_ROWS = 15;
            N_COLS = 15;
        }
        int BOARD_WIDTH = N_COLS * CELL_SIZE + 1;
        int BOARD_HEIGHT = N_ROWS * CELL_SIZE + 1;
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));     // There should be a better option than this

        int NUM_IMAGES = 13;                                            // 0 means empty cell, 1-8 is how many bombs surround
        img = new Image[NUM_IMAGES];                                    // 9 is mine, 10 is covered cell, 11 is a flag and
                                                                        // 12 is an empty flag
        for (int i = 0; i < NUM_IMAGES; i++) {

            var path = "src/resources/" + i + ".png";
            img[i] = (new ImageIcon(path)).getImage();
        }

        addMouseListener(new MinesAdapter());
        newGame();
    }

    private void newGame() {                                    // Initializes game

        int cell;

        var random = new Random();
        inGame = true;
        minesLeft = N_MINES;
        allCells = N_ROWS * N_COLS;
        field = new int[allCells];

        for (int i = 0; i < allCells; i++) {

            field[i] = COVER_FOR_CELL;
        }

        statusbar.setText(Integer.toString(minesLeft));

        int i = 0;

        while (i < N_MINES) {                               // sets up minefield and covers them

            int position = (int) (allCells * random.nextDouble());

            if ((position < allCells)
                    && (field[position] != COVERED_MINE_CELL)) {

                int current_col = position % N_COLS;
                field[position] = COVERED_MINE_CELL;
                i++;

                if (current_col > 0) {
                    cell = position - 1 - N_COLS;
                    if (cell >= 0) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                    cell = position - 1;
                    if (cell >= 0) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }

                    cell = position + N_COLS - 1;
                    if (cell < allCells) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                }

                cell = position - N_COLS;           // randomizes mine position
                if (cell >= 0) {
                    if (field[cell] != COVERED_MINE_CELL) {
                        field[cell] += 1;
                    }
                }

                cell = position + N_COLS;
                if (cell < allCells) {
                    if (field[cell] != COVERED_MINE_CELL) {
                        field[cell] += 1;
                    }
                }

                if (current_col < (N_COLS - 1)) {
                    cell = position - N_COLS + 1;
                    if (cell >= 0) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                    cell = position + N_COLS + 1;
                    if (cell < allCells) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                    cell = position + 1;
                    if (cell < allCells) {
                        if (field[cell] != COVERED_MINE_CELL) {
                            field[cell] += 1;
                        }
                    }
                }
            }
        }
    }

    private void find_empty_cells(int j) {                  // recursive algorithm to find empty cells, cells not bordered
                                                            // by any mine.
        int current_col = j % N_COLS;
        int cell;

        if (current_col > 0) {
            cell = j - N_COLS - 1;
            if (cell >= 0) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j - 1;
            if (cell >= 0) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + N_COLS - 1;
            if (cell < allCells) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }
        }

        cell = j - N_COLS;
        if (cell >= 0) {
            if (field[cell] > MINE_CELL) {
                field[cell] -= COVER_FOR_CELL;
                if (field[cell] == EMPTY_CELL) {
                    find_empty_cells(cell);
                }
            }
        }

        cell = j + N_COLS;
        if (cell < allCells) {
            if (field[cell] > MINE_CELL) {
                field[cell] -= COVER_FOR_CELL;
                if (field[cell] == EMPTY_CELL) {
                    find_empty_cells(cell);
                }
            }
        }

        if (current_col < (N_COLS - 1)) {
            cell = j - N_COLS + 1;
            if (cell >= 0) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + N_COLS + 1;
            if (cell < allCells) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + 1;
            if (cell < allCells) {
                if (field[cell] > MINE_CELL) {
                    field[cell] -= COVER_FOR_CELL;
                    if (field[cell] == EMPTY_CELL) {
                        find_empty_cells(cell);
                    }
                }
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {            // turns the numbers that represent the cell state into the
                                                        // corresponding images
        int uncover = 0;

        for (int i = 0; i < N_ROWS; i++) {

            for (int j = 0; j < N_COLS; j++) {

                int cell = field[(i * N_COLS) + j];

                if (inGame && cell == MINE_CELL) {

                    inGame = false;
                }

                int DRAW_COVER = 10;
                if (!inGame) {

                    if (cell == COVERED_MINE_CELL) {
                        cell = DRAW_MINE;
                    } else if (cell == FLAGGED_MINE_CELL) {
                        cell = this.DRAW_FLAG;
                    } else if (cell > COVERED_MINE_CELL) {
                        cell = DRAW_WRONG_FLAG;
                    } else if (cell > MINE_CELL) {
                        cell = DRAW_COVER;
                    }

                } else {

                    if (cell > COVERED_MINE_CELL) {
                        cell = this.DRAW_FLAG;
                    } else if (cell > MINE_CELL) {
                        cell = DRAW_COVER;
                        uncover++;
                    }
                }

                g.drawImage(img[cell], (j * CELL_SIZE),
                        (i * CELL_SIZE), this);
            }
        }

        if (uncover == 0 && inGame) {                                   // After game is over, choose to restart or not

            inGame = false;
            int a =JOptionPane.showConfirmDialog(null,
                    "Restart?", "You Won!", JOptionPane.YES_NO_OPTION);
            if (a==JOptionPane.YES_OPTION)
                initBoard();
            else
                System.exit(0);

        } else if (!inGame) {

            int a =JOptionPane.showConfirmDialog(null,
                    "Restart?", "You Lost", JOptionPane.YES_NO_OPTION);
            if (a==JOptionPane.YES_OPTION)
                initBoard();
            else
                System.exit(0);

        }
    }

    private class MinesAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            int cCol = x / CELL_SIZE;
            int cRow = y / CELL_SIZE;

            boolean doRepaint = false;

            if (!inGame) {


                repaint();
            }

            if ((x < N_COLS * CELL_SIZE) && (y < N_ROWS * CELL_SIZE)) {

                if (e.getButton() == MouseEvent.BUTTON3) {

                    if (field[(cRow * N_COLS) + cCol] > MINE_CELL) {

                        doRepaint = true;

                        if (field[(cRow * N_COLS) + cCol] <= COVERED_MINE_CELL) {

                            if (minesLeft > 0) {
                                field[(cRow * N_COLS) + cCol] += FLAG_FOR_CELL;
                                minesLeft--;
                                String msg = Integer.toString(minesLeft);
                                statusbar.setText(msg);
                            } else {
                                statusbar.setText("No marks left");
                            }
                        } else {

                            field[(cRow * N_COLS) + cCol] -= FLAG_FOR_CELL;
                            minesLeft++;
                            String msg = Integer.toString(minesLeft);
                            statusbar.setText(msg);
                        }
                    }

                } else {

                    if (field[(cRow * N_COLS) + cCol] > COVERED_MINE_CELL) {

                        return;
                    }

                    if ((field[(cRow * N_COLS) + cCol] > MINE_CELL)
                            && (field[(cRow * N_COLS) + cCol] < FLAGGED_MINE_CELL)) {

                        field[(cRow * N_COLS) + cCol] -= COVER_FOR_CELL;
                        doRepaint = true;

                        if (field[(cRow * N_COLS) + cCol] == MINE_CELL) {
                            inGame = false;
                        }

                        if (field[(cRow * N_COLS) + cCol] == EMPTY_CELL) {
                            find_empty_cells((cRow * N_COLS) + cCol);
                        }
                    }
                }
                if (doRepaint) {
                    repaint();
                }
            }
        }
    }
}