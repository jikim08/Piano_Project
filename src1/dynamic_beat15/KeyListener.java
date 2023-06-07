package dynamic_beat15;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter
{
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(DynamicBeat.game == null)
		{
			// 클래스 D.B. 에서 게임이 진행중이지 않으면 return;
			// 키보드 입력 이벤트를 무시함
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_S)
		{
			DynamicBeat.game.pressS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			DynamicBeat.game.pressD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F)
		{
			DynamicBeat.game.pressF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			DynamicBeat.game.pressSpace();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J)
		{
			DynamicBeat.game.pressJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K)
		{
			DynamicBeat.game.pressK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L)
		{
			DynamicBeat.game.pressL();
		}	
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
		if(DynamicBeat.game == null)
		{
			// 클래스 D.B. 에서 게임이 진행중이지 않으면 return;
			// 키보드 입력 이벤트를 무시함
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_S)
		{
			DynamicBeat.game.releaseS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			DynamicBeat.game.releaseD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F)
		{
			DynamicBeat.game.releaseF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			DynamicBeat.game.releaseSpace();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J)
		{
			DynamicBeat.game.releaseJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K)
		{
			DynamicBeat.game.releaseK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L)
		{
			DynamicBeat.game.releaseL();
		}
	}

}
