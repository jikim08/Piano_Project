package Piano;

import javax.swing.*;
import java.awt.*;


public class MainClass extends JFrame {

    public MainClass() {
        setTitle("Play Piano");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c= getContentPane();
        c.setLayout(new BorderLayout());
        setSize(1200,800);
        setResizable(false);


        Piano piano = new Piano();
        c.add(piano, BorderLayout.SOUTH);

        JPanel northPa = new JPanel();
        northPa.setLayout(new FlowLayout());
        northPa.add(piano.getOctaveLa());
        c.add(northPa, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainClass();
    }
}
