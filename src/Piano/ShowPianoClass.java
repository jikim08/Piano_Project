package Piano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;


public class ShowPianoClass extends JFrame {

    public ShowPianoClass() {
        setTitle("Play Piano");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c= getContentPane();
        c.setLayout(new BorderLayout());
        setSize(1200,800);
        setResizable(false);


        JPanel northPa = new JPanel();
        northPa.setLayout(null);
        northPa.setPreferredSize(new Dimension(getPreferredSize().width,30));
        northPa.setBackground(Color.GRAY);
        JPanel backButton = new JPanel();
        backButton.setSize(30,15);
        backButton.setLocation(5,10);
        backButton.setBackground(Color.WHITE);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new StartPage(new HashMap<>());
            }
        });

        Piano piano = new Piano();
        c.add(piano, BorderLayout.SOUTH);
        northPa.add(backButton);

        JLabel octave = piano.getOctaveLa();
        octave.setSize(100,30);
        octave.setLocation(550,5);
        northPa.add(octave);
        c.add(northPa, BorderLayout.NORTH);

        setVisible(true);
    }

}
