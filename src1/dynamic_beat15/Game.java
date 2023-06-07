package dynamic_beat15;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread
{
	private Image gameInfoImage= new ImageIcon(Main.class.getResource("../images/gameinfo.png")).getImage();
	private Image judgeLine= new ImageIcon(Main.class.getResource("../images/judgeLine.png")).getImage();
	
	private Image noteRouteLine= new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
//	private Image noteBasicImage= new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	
	private Image noteRouteS= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteD= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteF= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJ= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteK= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteL= new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle)
	{
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}
	
	public void screenDraw(Graphics2D g)
	{
		g.drawImage(noteRouteS, 228, 30, null);
		g.drawImage(noteRouteD, 332, 30, null);
		g.drawImage(noteRouteF, 436, 30, null);
		g.drawImage(noteRouteSpace1, 540, 30, null);
		g.drawImage(noteRouteSpace2, 640, 30, null);
		g.drawImage(noteRouteJ, 744, 30, null);
		g.drawImage(noteRouteK, 848, 30, null);
		g.drawImage(noteRouteL, 952, 30, null);
		g.drawImage(noteRouteLine, 224, 30, null);
		g.drawImage(noteRouteLine, 328, 30, null);
		g.drawImage(noteRouteLine, 432, 30, null);
		g.drawImage(noteRouteLine, 536, 30, null);
		g.drawImage(noteRouteLine, 740, 30, null);
		g.drawImage(noteRouteLine, 844, 30, null);
		g.drawImage(noteRouteLine, 948, 30, null);
		g.drawImage(noteRouteLine, 1052, 30, null);
				
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgeLine, 0, 580, null);
		
		for(int i = 0; i<noteList.size(); i++)
		{
			Note note = noteList.get(i);
			// 현재 노트가 작동하고있지 않으면
			// 노트를 지워줌
			if(!note.isProceeded())
			{
				noteList.remove(i);
				i--;
			}
			else
			{
				note.screenDraw(g);				
			}
		}
		
		// 게임창 하단에 곡명 출력
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 텍스트 화질저하 개선
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName,20,690);
		g.drawString(difficulty, 1100, 690);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000000", 565, 690);
		
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.drawString("s", 270, 610);
		g.drawString("d", 374, 610);
		g.drawString("f", 478, 610);
		g.drawString("space bar", 580, 610);
		g.drawString("j", 784, 610);
		g.drawString("k", 889, 610);
		g.drawString("l", 993, 610);
		
	}
	public void pressS()
	{
		judge("S");
		noteRouteS = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("FX_piano01.mp3",false).start();
	}
	public void releaseS()
	{
		noteRouteS = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressD()
	{
		judge("D");
		noteRouteD = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("FX_piano03.mp3",false).start();
	}
	public void releaseD()
	{
		noteRouteD = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressF()
	{
		judge("F");
		noteRouteF = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("FX_piano05.mp3",false).start();
	}
	public void releaseF()
	{
		noteRouteF = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressSpace()
	{
		judge("Space");
		noteRouteSpace1 = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2 = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("FX_piano06.mp3",false).start();
	}
	public void releaseSpace()
	{
		noteRouteSpace1 = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2 = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressJ()
	{
		judge("J");
		noteRouteJ = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("FX_piano08.mp3",false).start();
	}
	public void releaseJ()
	{
		noteRouteJ = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressK()
	{
		judge("K");
		noteRouteK = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("FX_piano10.mp3",false).start();
	}
	public void releaseK()
	{
		noteRouteK = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressL()
	{
		judge("L");
		noteRouteL = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("FX_piano12.mp3",false).start();
	}
	public void releaseL()
	{
		noteRouteL = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void close()
	{
		gameMusic.close();
		this.interrupt();
	}
	@Override
	public void run()
	{
		dropNote();
	}
	public void dropNote()
	{
		Beat[] beats = null;
		// easy&hard 선택 설정
		// 이건 크게 필요없을지도
		// 리듬게임이 아니라 피아노 연습이니깐.
		if(titleName.equals("Nocturne")/*&& difficulty.equals.("Hard"*/)
		{
			int startTime = 4000 - Main.REACH_TIME*1000;
			int gap = 125; // 최소 박자의 간격
			beats = new Beat[]
					{
							new Beat(startTime, "S"),
							new Beat(startTime + gap*2, "D"),
							new Beat(startTime + gap*4, "F"),
							new Beat(startTime + gap*6, "L"),
							new Beat(startTime + gap*8, "K"),
							new Beat(startTime + gap*10, "J"),
							new Beat(startTime + gap*12, "Space"),
							new Beat(startTime + gap*14, "S"),
							new Beat(startTime + gap*16, "L"),
							new Beat(startTime + gap*18, "Space"),
							
							
					};
		}
		else if(titleName.equals("Sailor's Song"))
		{
			int startTime = 1000 - Main.REACH_TIME*1000;
			beats = new Beat[]
					{
							new Beat(startTime, "Space"),
					};
		}
		else if(titleName.equals("Sonatina No 2"))
		{
			int startTime = 1000 - Main.REACH_TIME*1000;
			beats = new Beat[]
					{
							new Beat(startTime, "Space"),
					};
		}
		int i = 0;
		gameMusic.start();
		while(i<beats.length&&!isInterrupted())
		{
			boolean dropped = false;
			if(beats[i].getTime()<=gameMusic.getTime())
			{
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
			}
			if(!dropped)
			{
				try
				{
					Thread.sleep(5);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	} // dropNote()
	
	public void judge(String input)
	{
		for(int i = 0; i<noteList.size(); i++)
		{
			// 노트를 인덱스 0부터 하나씩 확인
			// index가 0부터 올라가므로 자연스럽게 가장 먼저나온 노트를 검사함
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType()))
			{
				note.judge();
				break;
			}
		}
	}

}
