import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/*
    MemoryGame implementation. Matches professor's guidelines, however I failed at making a start/restart option in time,
    I incorrectly assumed that recursion would work like in C++ but it instead opens a new window. Another issue encountered
    halfway through was that my border is at minimum size when running, setSize did not fix it. Sources that I used was a
    12 part tutorial on Youtube https://www.youtube.com/watch?v=XulTntnrb8M&list=PLcvfyq8zN1IqZ1z96JrKxkZ8uCWFR8FXj&index=1
    which was a great tutorial in helping with this project.
 */

public class MemoryGame extends JFrame {

    private static final String[] pics = { "images/Dragonite.jpg", "images/Espeon.jpg", "images/Milotic.jpg",  // static string of our pictures stored in array
            "images/Pikachu.jpg", "images/Starmie.jpg", "images/Totodile.jpg", "images/Treecko.jpg", "images/Umbreon.jpg"};
    private static final String cardBack = "images/whosThat.jpg";                       // Backside
    private final GridPanel[] grid;                           // Grid setup
    private final ImageIcon backIcon;
    private final ImageIcon[] icons;
    private final JLabel matchText;                          // Matches made label
    private final JLabel guessText;
    private final Timer myTimer;                             // Time delay
    private final int numButtons;
    private int openImages;
    private int oddClickIndex;                              // Basically the previous image opened
    private int currentIndex;           // Image that is currently open, if only 1 image open then this is the same as oddClick
    private static int match = 0;
    private static int guess = 0;

    public MemoryGame() {

        setTitle("Who's That Pokemon?");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(5,5,900,700);            // These didn't work when trying to fix window
        setBackground(Color.BLACK);
        pack();
        setVisible(true);

        numButtons = pics.length * 2;                       // makes future algorithms easier
        backIcon = new ImageIcon(cardBack);

        grid = new GridPanel[numButtons];                   // Initializing the grid panels and image icons
        icons = new ImageIcon[numButtons];

        JPanel topPanel = new JPanel();

        matchText = new JLabel("Matches Made: 0");
        topPanel.add(matchText);
        guessText = new JLabel("Guesses Made: 0");
        topPanel.add(guessText);

        topPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(4, 4));

        add(bottomPanel);

        for (int i = 0, j = 0; i < pics.length; i++) {              // Creates the buttons
            icons[j] = new ImageIcon(pics[i]);
            setGrid(j);
            bottomPanel.add(grid[j++].getButton());
            icons[j] = icons[j - 1];
            setGrid(j);
            bottomPanel.add(grid[j++].getButton());
        }

        Random rnd = new Random();
        for (int i = 0; i < icons.length; i++) {                    // temporary array to randomize buttons
            int index = rnd.nextInt(numButtons);
            ImageIcon temp = icons[i];
            icons[i] = icons[index];
            icons[index] = temp;
        }
        myTimer = new Timer(3000, new TimerListener());
    }

    private void setGrid(int index) {                               // Sets the grid, but the index is randomized
        grid[index] = new GridPanel();
        grid[index].setButton(new JButton(""));
        grid[index].getButton().addActionListener(new ImageButtonListener()); // On-click
        grid[index].getButton().setIcon(backIcon);                  // Default view of the cards
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {            // Initially sets everything to backside of card

            grid[currentIndex].getButton().setIcon(backIcon);
            grid[oddClickIndex].getButton().setIcon(backIcon);
            myTimer.stop();
        }
    }

    private class ImageButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (myTimer.isRunning())                            // increment open images
                return;
            openImages++;

            for (int i = 0; i < numButtons; i++) {              // Initializes current index
                if (e.getSource() == grid[i].getButton()) {
                    grid[i].getButton().setIcon(icons[i]);
                    currentIndex = i;
                }
            }

            if (openImages % 2 == 0) {                      // Forces no more than 2 open images at once
                if (currentIndex == oddClickIndex) {
                    openImages--;
                    return;
                }
                if (icons[currentIndex] != icons[oddClickIndex]) { // If they don't match
                    myTimer.start();
                } else {
                    match++;
                    matchText.setText("Matches Made: " + match);
                    grid[currentIndex].setOpen();               // Keep those images open
                    grid[oddClickIndex].setOpen();
                }
                guess++;
                guessText.setText("Guesses Made: " + guess);
            } else {
                oddClickIndex = currentIndex;                       // Only 1 card has been clicked
            }
            if (match == 8){            // This is where my restart would go, no time to figure out the method
                System.exit(0);
            }
        }
    }


    public static void main(String[] args) {        // Call MemoryGame
        new MemoryGame();
    }
}