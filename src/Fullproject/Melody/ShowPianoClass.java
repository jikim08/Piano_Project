package Fullproject.Melody;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ShowPianoClass extends JFrame 
{

    public ShowPianoClass() 
    {
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
        backButton.setSize(30,15);      //뒤로가기 버튼(버튼으로 했더니 이벤트 충돌로 피아노가 작동하지 않아서 패널로 대체)
        backButton.setLocation(5,10);
        backButton.setBackground(Color.WHITE);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();

                new StartPage();
            }
        });

        Piano piano = new Piano();          //피아노 패널 생성
        c.add(piano, BorderLayout.SOUTH);       //피아노 패널 추가
        northPa.add(backButton);

        JLabel octave = piano.getOctaveLa();            //옥타브 표시 레이블
        octave.setSize(100,30);
        octave.setLocation(550,0);
        northPa.add(octave);
        c.add(northPa, BorderLayout.NORTH);

        setVisible(true);
    }

}
