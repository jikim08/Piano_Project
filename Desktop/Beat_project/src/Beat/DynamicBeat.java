package Beat;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame {

	
	private Image background = new ImageIcon(Main.class.getResource("../images/Background.jpg"))
			.getImage();
	
	ArrayList<Track> trackList = new ArrayList<Track>();

	
	public DynamicBeat() {
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		Music introMusic = new Music("music.mp3", true);
		introMusic.start();
		
	}
}
