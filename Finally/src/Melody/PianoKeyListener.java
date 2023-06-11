package Melody;

import static java.awt.event.KeyEvent.VK_LEFT;

import static java.awt.event.KeyEvent.VK_RIGHT;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class PianoKeyListener extends KeyAdapter implements Runnable {
    public Piano piano;
    private Map<Character, Boolean> isPressed = new HashMap<>();

    public PianoKeyListener(Piano piano){
        this.piano = piano;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();
        if(keyCode == VK_RIGHT){
            piano.raiseOctave();
            return;
        }
        else if(keyCode == VK_LEFT){
            piano.downOctave();
            return;
        }

        try{
            if(isPressed.get(keyChar))
                return;
        }
        catch (Exception ex){

        }
        try {
            piano.midiChannel.noteOn(piano.noteMap.get(keyChar),150);
            JPanel button = piano.buttonMap.get(keyChar);
            button.setBackground(Color.RED);

            button = piano.subWhiteButtonMap.get(keyChar);
            button.setBackground(Color.RED);

        }
        catch (Exception ex){

        }
        isPressed.put(keyChar,true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char keyChar = e.getKeyChar();
        try {
            JPanel button = piano.buttonMap.get(keyChar);
            for(int i = 0; i<7;i++){
                if(piano.whiteKey[i] == keyChar) {
                    JPanel subButton = piano.subWhiteButtonMap.get(keyChar);
                    subButton.setBackground(Color.WHITE);
                    button.setBackground(Color.WHITE);
                    break;
                }
                else if(i <6 && piano.blackKey[i] == keyChar){
                    button.setBackground(Color.BLACK);
                    break;
                }
            }
            piano.midiChannel.noteOff(piano.noteMap.get(keyChar));
        }
        catch (Exception ex) {

        }
        isPressed.put(keyChar, false);
    }

    @Override
    public void run() {
        piano.addKeyListener(this);
    }
}
