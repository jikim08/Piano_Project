package Beat;

import java.util.ArrayList;


public class Game extends Thread {
	public String titleName;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public void dropNotes() {
		Beat[] beats;

		if (titleName.equals("비행기")) {
			int startTime = 8000 -Main.REACH_TIME*1000;
			int gap=125;
			beats = new Beat[] { new Beat(startTime+gap*1 , "D"), new Beat(startTime + gap * 4, "S"),
					new Beat(startTime + gap * 5, "A"), new Beat(startTime + gap * 7, "s"),
					new Beat(startTime + gap * 9, "D"), new Beat(startTime + gap * 11, "D"),
					new Beat(startTime + gap * 13, "D"), new Beat(startTime + gap * 17, "S"),
					new Beat(startTime + gap * 19, "S"), new Beat(startTime + gap * 21, "S"),
					new Beat(startTime + gap * 25, "D"), new Beat(startTime + gap * 27, "D"),
					new Beat(startTime + gap * 29, "D"), new Beat(startTime + gap * 33, "D"),
					new Beat(startTime + gap * 36, "S"), new Beat(startTime + gap * 37, "A"),
					new Beat(startTime + gap * 39, "S"), new Beat(startTime + gap * 41, "D"),
					new Beat(startTime + gap * 43, "D"), new Beat(startTime + gap * 45, "D"),
					new Beat(startTime + gap * 49, "S"), new Beat(startTime + gap * 51, "S"),
					new Beat(startTime + gap * 53, "D"), new Beat(startTime + gap * 56, "S"),
					new Beat(startTime + gap * 57, "A"),};
		} else if (titleName.equals("나비야")) {
			int startTime = 8000 - Main.REACH_TIME;
			int gap = 125;
			beats = new Beat[] { new Beat(startTime+ gap * 1, "G"), new Beat(startTime + gap * 3, "D"),
					new Beat(startTime + gap * 5, "D"), new Beat(startTime + gap * 9, "F"),
					new Beat(startTime + gap * 11, "S"), new Beat(startTime + gap * 13, "S"),
					new Beat(startTime + gap * 17, "A"), new Beat(startTime + gap * 19, "S"),
					new Beat(startTime + gap * 21, "D"), new Beat(startTime + gap * 23, "F"),
					new Beat(startTime + gap * 25, "G"), new Beat(startTime + gap * 27, "G"),
					new Beat(startTime + gap * 29, "G"), new Beat(startTime + gap * 33, "G"),
					new Beat(startTime + gap * 35, "D"), new Beat(startTime + gap * 37, "D"),
					new Beat(startTime + gap * 39, "D"), new Beat(startTime + gap * 41, "F"),
					new Beat(startTime + gap * 43, "S"), new Beat(startTime + gap * 45, "S"),
					new Beat(startTime + gap * 49, "A"), new Beat(startTime + gap * 51, "D"),
					new Beat(startTime + gap * 53, "G"), new Beat(startTime + gap * 55, "G"),
					new Beat(startTime + gap * 57, "D"), new Beat(startTime + gap * 59, "D"),
					new Beat(startTime + gap * 61, "D"), new Beat(startTime + gap * 65, "S"),
					new Beat(startTime + gap * 67, "S"), new Beat(startTime + gap * 69, "S"),
					new Beat(startTime + gap * 71, "S"), new Beat(startTime + gap * 73, "S"),
					new Beat(startTime + gap * 75, "D"), new Beat(startTime + gap * 77, "F"),
					new Beat(startTime + gap * 81, "D"), new Beat(startTime + gap * 83, "D"),
					new Beat(startTime + gap * 85, "D"), new Beat(startTime + gap * 87, "D"),
					new Beat(startTime + gap * 89, "D"), new Beat(startTime + gap * 91, "F"),
					new Beat(startTime + gap * 93, "G"), new Beat(startTime + gap * 97, "G"),
					new Beat(startTime + gap * 99, "D"), new Beat(startTime + gap * 101, "D"),
					new Beat(startTime + gap * 105, "F"), new Beat(startTime + gap * 107, "S"),
					new Beat(startTime + gap * 109, "S"), new Beat(startTime + gap * 113, "A"),
					new Beat(startTime + gap * 115, "D"), new Beat(startTime + gap * 117, "G"),
					new Beat(startTime + gap * 119, "G"), new Beat(startTime + gap * 121, "D"),
					new Beat(startTime + gap * 123, "D"), new Beat(startTime + gap * 125, "D"),};
		}
		 else if (titleName.equals("학교종이 땡땡땡")) {
				int startTime = 8000 - Main.REACH_TIME;
				int gap = 125;
				beats = new Beat[] { new Beat(startTime+ gap * 1, "G"), new Beat(startTime + gap * 3, "G"),
						new Beat(startTime+ gap * 5, "H"), new Beat(startTime + gap * 7, "H"),
						new Beat(startTime+ gap * 9, "G"), new Beat(startTime + gap * 11, "G"),
						new Beat(startTime+ gap * 15, "D"), new Beat(startTime + gap * 17, "G"),
						new Beat(startTime+ gap * 19, "G"), new Beat(startTime + gap * 21, "D"),
						new Beat(startTime+ gap * 27, "S"), new Beat(startTime + gap * 29, "G"),
						new Beat(startTime + gap * 31, "G"),new Beat(startTime+ gap * 33, "H"),
						new Beat(startTime+ gap * 35, "H"), new Beat(startTime+ gap * 37, "G"),
						new Beat(startTime + gap * 39, "G"),new Beat(startTime+ gap * 43, "D"),
						new Beat(startTime+ gap * 45, "G"), new Beat(startTime + gap * 47, "D"),
						new Beat(startTime+ gap * 49, "S"),new Beat(startTime + gap * 51, "D"),
						new Beat(startTime + gap * 57, "A")};
				}
		 else if (titleName.equals("곰 세마리")) {
				int startTime = 8000 - Main.REACH_TIME;
				int gap = 125;
				beats = new Beat[] { new Beat(startTime+ gap * 1, "A"), new Beat(startTime + gap * 3, "A"),
						new Beat(startTime + gap * 4, "A"), new Beat(startTime + gap * 5, "A"),
						new Beat(startTime + gap * 7, "A"), new Beat(startTime + gap * 9, "D"),
						new Beat(startTime + gap * 11, "G"), new Beat(startTime + gap * 12, "G"),
						new Beat(startTime + gap * 13, "D"), new Beat(startTime + gap * 15, "A"),
						new Beat(startTime + gap * 17, "G"), new Beat(startTime + gap * 18, "G"),
						new Beat(startTime + gap * 19, "D"), new Beat(startTime + gap * 21, "G"),
						new Beat(startTime + gap * 22, "G"), new Beat(startTime + gap * 23, "D"),
						new Beat(startTime + gap * 25, "A"), new Beat(startTime + gap * 27, "A"),
						new Beat(startTime + gap * 29, "A"),new Beat(startTime + gap * 33, "G"),
						new Beat(startTime + gap * 35, "G"),new Beat(startTime + gap * 37, "D"),
						new Beat(startTime + gap * 39, "A"),new Beat(startTime + gap * 41, "G"),
						new Beat(startTime + gap * 43, "G"),new Beat(startTime + gap * 45, "G"),
						new Beat(startTime + gap * 49, "G"),new Beat(startTime + gap * 51, "G"),
						new Beat(startTime + gap * 53, "D"),new Beat(startTime + gap * 55, "A"),
						new Beat(startTime + gap * 57, "G"),new Beat(startTime + gap * 59, "G"),
						new Beat(startTime + gap * 61, "G"),new Beat(startTime + gap * 65, "G"),
						new Beat(startTime + gap * 67, "G"),new Beat(startTime + gap * 69, "D"),
						new Beat(startTime + gap * 71, "A"),new Beat(startTime + gap * 73, "G"),
						new Beat(startTime + gap * 74, "G"),new Beat(startTime + gap * 75, "G"),
						new Beat(startTime + gap * 76, "H"),new Beat(startTime + gap * 77, "G"),
						new Beat(startTime + gap * 81, "A"/*높은도*/),new Beat(startTime + gap * 83, "G"),
						new Beat(startTime + gap * 85, "A"/*높은도*/),new Beat(startTime + gap * 87, "G"),
						new Beat(startTime + gap * 89, "D"),new Beat(startTime + gap * 91, "S"),
						new Beat(startTime + gap * 93, "A"),};
		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	}
}
