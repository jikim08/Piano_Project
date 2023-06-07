package dynamic_beat15;

public class Main 
{
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 700;
	public static final int NOTE_SPEED = 3;	// 노트의 낙하속도
	public static final int SLEEP_TIME = 10; // 노트의 낙하 빈도?
	public static final int REACH_TIME = 2; // 노트 생성후 judgeline까지 도달하는데 걸리는 시간.
	public static void main(String[] args) 
	{
		new DynamicBeat();
	}
}