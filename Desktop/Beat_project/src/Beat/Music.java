package Beat;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{
	
	private Player player;
	private boolean isLoop; //현재 곡이 무한 반복인지 한번만 재생되고 끝나는지를 설정하는 것
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop=isLoop;
			file = new File(Main.class.getResource("../music/music.mp3").toURI());
			fis=new FileInputStream(file);
			bis=new BufferedInputStream(fis);
			player = new Player(bis);
		}catch(Exception e) { //예외처리를 위해서 사용
			System.out.println(e.getMessage());
		}		
	}
	public int getTime() { //현재 실행되는 음악이 어떤 위치에서 실행되는지 알려줌 & 음악에 맞춰 노트를 떨어뜨릴 때 getTime()을 이용해 분석함
		if(player==null)
			return 0;
		return player.getPosition();
	}
	public void close() {
		isLoop= false;
		player.close();
		this.interrupt();//쓰레드를 중지상태로 만듬
	}
	@Override
	public void run() { //쓰레드를 상속받으면 무조건 사용해야하는 함수
		try {
			do {
				player.play();
				fis=new FileInputStream(file);
				bis=new BufferedInputStream(fis);
				player = new Player(bis);
				}while(isLoop); //isLoop가 true 값을 가지면 해당곡은 무한 반복으로 실행
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	}
}
