import javax.swing.*;
import java.awt.*;

/*
Problems with layout, couldn't figure out how to center align, I believe there's a better
layout option for it as well. 
 */

public class GUI {

    public GUI(){
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();                // GUI setup, button setup
        panel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 30));
        panel.setLayout(new GridBagLayout());       // Specific layout called gridbaglayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1,1,1,1);       // Creates space between buttons

        JTextField textField = new JTextField("Type a message...");     // For creating a text box
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textField, gbc);

        JButton sendButton = new JButton("Send");               // Creates buttons to match a mobile keyboard
        gbc.gridx=8;
        gbc.gridy=0;
        gbc.gridwidth=1;
        panel.add(sendButton, gbc);

        JButton qbutton = new JButton("Q");
        gbc.gridx=0;
        gbc.gridy=1;
        panel.add(qbutton, gbc);

        JButton wbutton = new JButton("W");
        gbc.gridx=1;
        gbc.gridy=1;
        panel.add(wbutton, gbc);

        JButton ebutton = new JButton("E");
        gbc.gridx=2;
        gbc.gridy=1;
        panel.add(ebutton, gbc);

        JButton rbutton = new JButton("R");
        gbc.gridx=3;
        gbc.gridy=1;
        panel.add(rbutton, gbc);

        JButton tbutton = new JButton("T");
        gbc.gridx=4;
        gbc.gridy=1;
        panel.add(tbutton, gbc);

        JButton ybutton = new JButton("Y");
        gbc.gridx=5;
        gbc.gridy=1;
        panel.add(ybutton, gbc);

        JButton ubutton = new JButton("U");
        gbc.gridx=6;
        gbc.gridy=1;
        panel.add(ubutton, gbc);

        JButton ibutton = new JButton("I");
        gbc.gridx=7;
        gbc.gridy=1;
        panel.add(ibutton, gbc);

        JButton obutton = new JButton("O");
        gbc.gridx=8;
        gbc.gridy=1;
        panel.add(obutton, gbc);

        JButton pbutton = new JButton("P");
        gbc.gridx=9;
        gbc.gridy=1;
        panel.add(pbutton, gbc);

        JButton abutton = new JButton("A");
        gbc.gridx=0;
        gbc.gridy=2;
        panel.add(abutton, gbc);

        JButton sbutton = new JButton("S");
        gbc.gridx=1;
        gbc.gridy=2;
        panel.add(sbutton, gbc);

        JButton dbutton = new JButton("D");
        gbc.gridx=2;
        gbc.gridy=2;
        panel.add(dbutton, gbc);

        JButton fbutton = new JButton("F");
        gbc.gridx=3;
        gbc.gridy=2;
        panel.add(fbutton, gbc);

        JButton gbutton = new JButton("G");
        gbc.gridx=4;
        gbc.gridy=2;
        panel.add(gbutton, gbc);

        JButton hbutton = new JButton("H");
        gbc.gridx=5;
        gbc.gridy=2;
        panel.add(hbutton, gbc);

        JButton jbutton = new JButton("J");
        gbc.gridx=6;
        gbc.gridy=2;
        panel.add(jbutton, gbc);

        JButton kbutton = new JButton("K");
        gbc.gridx=7;
        gbc.gridy=2;
        panel.add(kbutton, gbc);

        JButton lbutton = new JButton("L");
        gbc.gridx=8;
        gbc.gridy=2;

        JButton zbutton = new JButton("Z");
        gbc.gridx=0;
        gbc.gridy=3;
        panel.add(zbutton, gbc);

        JButton xbutton = new JButton("X");
        gbc.gridx=1;
        gbc.gridy=3;
        panel.add(xbutton, gbc);

        JButton cbutton = new JButton("C");
        gbc.gridx=2;
        gbc.gridy=3;
        panel.add(cbutton, gbc);

        JButton vbutton = new JButton("V");
        gbc.gridx=3;
        gbc.gridy=3;
        panel.add(vbutton, gbc);

        JButton bbutton = new JButton("B");
        gbc.gridx=4;
        gbc.gridy=3;
        panel.add(bbutton, gbc);

        JButton nbutton = new JButton("N");
        gbc.gridx=5;
        gbc.gridy=3;
        panel.add(nbutton, gbc);

        JButton mbutton = new JButton("M");
        gbc.gridx=6;
        gbc.gridy=3;
        panel.add(mbutton, gbc);


        frame.add(panel, BorderLayout.CENTER);                      // Basic GUI setup stuff
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args){
        new GUI();
    }
}
