package Beat;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	
	private Image Note = new ImageIcon(Main.class.getResource("")).getImage();
	private int x,y= 580- 1000/Main.SLEEP_TIME*Main.NOTE_SPEED;
	private String noteType;
	
	public Note(String noteType) {
		if(noteType.equals("A")) {
			x=0;
		}
		else if(noteType.equals("S")) {
			x=257;
		}
		else if(noteType.equals("D")) {
			x=514;
	}
		else if(noteType.equals("F")) {
			x=771;
		}
		else if(noteType.equals("G")) {
			x=1028;
		}
		else if(noteType.equals("H")) {
			x=1285;
		}
		else if(noteType.equals("J")) {
			x=1542;
		}
		this.noteType=noteType;
	}

	public void drop() {
		y +=Main.NOTE_SPEED;
	}
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				Thread.sleep(Main.SLEEP_TIME);
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
			}
		}
		
	}
