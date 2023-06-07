package Piano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShowPianoClass extends JFrame {

    public ShowPianoClass() {
        setTitle("Play Piano");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c= getContentPane();
        c.setLayout(new BorderLayout());
        setSize(1200,800);
        setResizable(false);


        Piano piano = new Piano();
        c.add(piano, BorderLayout.SOUTH);

        JPanel northPa = new JPanel();
        JButton next = new JButton();
        next.setSize(50,30);
        next.setText("<-");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SelectPage();
            }
        });
        northPa.add(next);
        northPa.setLayout(new FlowLayout());
        northPa.add(piano.getOctaveLa());
        c.add(northPa, BorderLayout.NORTH);

        setVisible(true);
    }

}
