import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;

/*
    Minesweeper-like game. I had many problems, the first major one was that I couldn't get a working JMenu, so I had
    to instead use a very bare dialog box that would only change difficulty and automatically start a new game.
    Initializing the board came with problems as well, when trying to restart and trying to implement first-click.
    Also, a random extra row appears after my first click, however it has no effect on the game and can't be seen unless
    you resize the window, so I ignored it. This tutorial I found on youtube was what I used as an initial source, and
    the second article really helped me visualize the logic behind the cells.
    https://www.youtube.com/watch?v=KihhuTTNFw4
    https://medium.com/@ArmstrongCS/making-minesweeper-in-10-minutes-e4c4e810fa06
    https://zetcode.com/javagames/minesweeper/

 */

public class CalderaGame extends JFrame {

    JFrame f;
    public CalderaGame() {                                   // Basic setup
        JLabel statusbar = new JLabel("Traps");         // Text wouldn't show for some reason
        add(statusbar, BorderLayout.NORTH);
        add(new Board(statusbar));
        setResizable(true);
        pack();
        setTitle("The Yellowstone Caldera Weasel");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(CalderaGame::new);
    }
}