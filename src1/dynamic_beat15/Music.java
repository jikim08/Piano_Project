package dynamic_beat15;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread
{
	private Player player;
	private boolean isLoop; // 곡 반복재생여부
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop)
	{
		try
		{
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI()); // class D.B.에서 introMusic을 초기화할 때 name에 introMusic.mp3를 대입함.
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public int getTime() // 현재 실행되는 음악의 재생시간을 반환 // 0.001초 단위로 반환
	{
		if(player == null)
			return 0;
		return player.getPosition();
	}
	public void close() // 중간에 페이지변환or종료 등 현 페이지를 벗어나면 음악도 같이 종료
	{
		isLoop = false;
		player.close();
		this.interrupt(); // 인터럽트를 발생시키는듯?
	}
	
	@Override
	public void run() // 음원 무한반복 여부
	{
		try
		{
			do
			{
				player.play();
			}while(isLoop); // isLoop가 true이면 음원 무한반복
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}
