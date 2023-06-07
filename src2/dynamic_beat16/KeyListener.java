package dynamic_beat16;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends Thread {
    private boolean running;
    
    public KeyListener() {
        running = true;
    }
    
    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void stopThread() {
        running = false;
    }
    
    public void keyPressed(KeyEvent e) {
        if (DynamicBeat.game == null) {
            return;
        }
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_S:
                DynamicBeat.game.pressS();
                break;
            case KeyEvent.VK_D:
                DynamicBeat.game.pressD();
                break;
            case KeyEvent.VK_F:
                DynamicBeat.game.pressF();
                break;
            case KeyEvent.VK_SPACE:
                DynamicBeat.game.pressSpace();
                break;
            case KeyEvent.VK_J:
                DynamicBeat.game.pressJ();
                break;
            case KeyEvent.VK_K:
                DynamicBeat.game.pressK();
                break;
            case KeyEvent.VK_L:
                DynamicBeat.game.pressL();
                break;
        }
    }
    
    public void keyReleased(KeyEvent e) {
        if (DynamicBeat.game == null) {
            return;
        }
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_S:
                DynamicBeat.game.releaseS();
                break;
            case KeyEvent.VK_D:
                DynamicBeat.game.releaseD();
                break;
            case KeyEvent.VK_F:
                DynamicBeat.game.releaseF();
                break;
            case KeyEvent.VK_SPACE:
                DynamicBeat.game.releaseSpace();
                break;
            case KeyEvent.VK_J:
                DynamicBeat.game.releaseJ();
                break;
            case KeyEvent.VK_K:
                DynamicBeat.game.releaseK();
                break;
            case KeyEvent.VK_L:
                DynamicBeat.game.releaseL();
                break;
        }
    }
}
