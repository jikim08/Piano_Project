package dynamic_beat15;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread
{
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../Images/noteBasic.png")).getImage();
	private int x;	// note's location
	private int y = 580-(1000/Main.SLEEP_TIME*Main.NOTE_SPEED)*Main.REACH_TIME;
	
	private boolean proceeded = true;
	
	private String noteType;
	
	public boolean isProceeded()
	{
		return proceeded;
	}
	public void close()
	{
		proceeded = false;
	}
	public String getNoteType() {
		return noteType;
	}
	public Note(String noteType)
	{
		if(noteType.equals("S"))
			x = 228;
		else if(noteType.equals("D"))
			x = 332;
		else if(noteType.equals("F"))
			x = 436;
		else if(noteType.equals("Space"))
			x = 540;
		else if(noteType.equals("J"))
			x = 744;
		else if(noteType.equals("K"))
			x = 848;
		else if(noteType.equals("L"))
			x = 952;
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g)
	{
		if(noteType.equals("Space"))
		{
			g.drawImage(noteBasicImage, x, y,null);
		}
		else
		{
			g.drawImage(noteBasicImage, x, y,null);
			//g.drawImage(noteBasicImage, x+100, y,null);
			// 가로의 길이(스페이스키)가 긴 키의 경우
			// 이건 피아노로 수정할때는 필요없을듯
		}
	}
	
	public void drop()
	{
		// y좌표가 notespeed의 속도로 떨어짐
		y += Main.NOTE_SPEED;
		// 노트의 y값이 620보다 커지면 판정라인 아래로 내려갔다고 판단
		// 판정라인 아래이므로 miss라 출력
		if(y > 620)
		{
			// judgeline에 닿아 소멸되는건 close가 수행
			// 정확히는 판정라인 위에 닿자마자 소멸
			// 판정함수 실행 시 miss는 노트가 판정라인을 완전히 지나간 후에 출력해야함
			// 별개로 노트는 판정라인에 닿으면 소멸?
			// 이렇게되면 late, fast , perfect등을 판정하기 어려움
			System.out.println("Miss");
			close();
		}
	}
	
	@Override
	public void run()
	{
		try
		{
			while(true)
			{
				drop();
				
				// 
				if(proceeded)
				{
					Thread.sleep(Main.SLEEP_TIME);
				}
				else
				{
					interrupt();
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void judge()
	{
		if(y >= 610)
		{
			System.out.println("Late");
			close();
		}
		else if(y >= 600)
		{
			System.out.println("Good");
			close();
		}
		else if(y >= 590)
		{
			System.out.println("Great");
			close();
		}
		else if(y >= 580)
		{
			System.out.println("Perfect");
			close();
		}
		else if(y >= 570)
		{
			System.out.println("Great");
			close();
		}
		else if(y >= 560)
		{
			System.out.println("Good");
			close();
		}
		else if(y >= 550)
		{
			System.out.println("Early");
			close();
		}
		
	}
}
