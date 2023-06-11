package Melody;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowPianoGameClass extends JFrame {
	private int trackNum;

	public ShowPianoGameClass(int nowSelected) {
		this.trackNum = nowSelected;
		setTitle("Play Piano");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		setSize(1200, 800);
		setResizable(false);

		JPanel northPa = new JPanel();
		northPa.setLayout(null);
		northPa.setPreferredSize(new Dimension(getPreferredSize().width, 30));
		northPa.setBackground(Color.GRAY);
		JPanel backButton = new JPanel();
		backButton.setSize(30, 15);
		backButton.setLocation(5, 10);
		backButton.setBackground(Color.WHITE);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				new SelectPage();
			}
		});

		Piano piano = new Piano();
		c.add(piano, BorderLayout.SOUTH);
		Game game = new Game(piano, trackNum);
		c.add(game, BorderLayout.CENTER);
		northPa.add(backButton);

		JLabel octave = piano.getOctaveLa();
		octave.setSize(100, 30);
		octave.setLocation(550, 10);
		northPa.add(octave);
		c.add(northPa, BorderLayout.NORTH);

		setVisible(true);
	}

}
