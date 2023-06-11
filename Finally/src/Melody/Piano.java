package Melody;

import static java.awt.event.KeyEvent.VK_LEFT;

import static java.awt.event.KeyEvent.VK_RIGHT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Piano extends JPanel {
    private Synthesizer synthesizer;
    public MidiChannel midiChannel;
    public Map<Character, JPanel> buttonMap;
    public Map<Character, JPanel> subWhiteButtonMap;
    public Map<Character, Integer> noteMap;
    private JPanel subPanel1;
    private JPanel subPanel2;
    public final char[] whiteKey = {'a','s','d','f','g','h','j'};
    public int[] whiteNote = {60,62,64,65,67,69,71};
    public final char[] blackKey={'w','e','r','t','y','u'};
    public int[] blackNote = {61,63,0,66,68,70};
    private int octave = 0;
    public JLabel octaveLa = new JLabel("현재 옥타브: " + octave);
    public Map<Character, Boolean> isPressed = new HashMap<>();

    public Piano(){
        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(getPreferredSize().width, 300));
        subPanel1 = new JPanel();
        subPanel2 = new JPanel();
        subPanel1.setLayout(null);
        subPanel2.setLayout(null);

        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            midiChannel = synthesizer.getChannels()[0];
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }

        buttonMap = new HashMap<>();
        subWhiteButtonMap = new HashMap<>();
        noteMap = new HashMap<>();

        for(int i = 0; i < 12; i++){
            if(i == 0 || i == 2 || i == 4 || i == 5 || i == 7 || i == 9 || i == 11){
                JPanel empty = new JPanel();
                subWhiteButtonMap.put(whiteKey[(i+1)/2],empty);
                empty.setSize(100,150);
                empty.setLocation(i*100,0);
                empty.setBackground(Color.WHITE);
                empty.setBorder(BorderFactory.createMatteBorder(1,1,0,1,Color.BLACK));
                subPanel1.add(empty);
                continue;
            }
            JPanel button = new JPanel();
            button.setSize(100,150);
            button.setLocation(i*100,0);
            button.setBackground(Color.black);
            buttonMap.put(blackKey[i/2], button);
            noteMap.put(blackKey[i/2], blackNote[i/2]);
            subPanel1.add(button);
        }

        for(int i = 0; i < 7; i++){
            JPanel button = new JPanel();
            if(i == 6){
                button.setSize(200,150);
            }
            button.setSize(1200/7,150);
            button.setLocation(i*(1200/7)-13,0);
            button.setBorder(BorderFactory.createMatteBorder(0,1,0,1,Color.BLACK));
            button.setBackground(Color.WHITE);
            buttonMap.put(whiteKey[i], button);
            noteMap.put(whiteKey[i], whiteNote[i]);
            subPanel2.add(button);
        }

        add(subPanel1);
        add(subPanel2);
        addKeyListener(new PianoKeyListener());
        setFocusable(true);
        requestFocus();
    }

    public void raiseOctave(){
        Map<Character, Integer> tmp = new HashMap<>();

        for(int i = 0; i < 7; i++){
            tmp.put(whiteKey[i],noteMap.get(whiteKey[i])+12);
        }
        for(int i = 0; i < 6; i++){
            if(i==2)    continue;
            tmp.put(blackKey[i],noteMap.get(blackKey[i])+12);
        }
        noteMap = tmp;
        octave++;
        octaveLa.setText("현재 옥타브: " + octave);
    }

    public void downOctave(){
        Map<Character, Integer> tmp = new HashMap<>();

        for(int i = 0; i < 7; i++){
            tmp.put(whiteKey[i],noteMap.get(whiteKey[i])-12);
        }
        for(int i = 0; i < 6; i++){
            if(i==2)    continue;
            tmp.put(blackKey[i],noteMap.get(blackKey[i])-12);
        }
        noteMap = tmp;
        octave--;
        octaveLa.setText("현재 옥타브: " + octave);
    }

    public JLabel getOctaveLa(){
        return octaveLa;
    }

   class PianoKeyListener extends KeyAdapter {

       @Override
       public void keyPressed(KeyEvent e) {
           int keyCode = e.getKeyCode();
           char keyChar = e.getKeyChar();
           if (keyCode == VK_RIGHT) {
               raiseOctave();
               return;
           } else if (keyCode == VK_LEFT) {
               downOctave();
               return;
           }

           try {
               if (isPressed.get(keyChar))
                   return;
           } catch (Exception ex) {

           }
           try {
               midiChannel.noteOn(noteMap.get(keyChar), 150);
               JPanel button = buttonMap.get(keyChar);
               button.setBackground(Color.RED);

               button = subWhiteButtonMap.get(keyChar);
               button.setBackground(Color.RED);

           } catch (Exception ex) {

           }
           isPressed.put(keyChar, true);
       }

       @Override
       public void keyReleased(KeyEvent e) {
           char keyChar = e.getKeyChar();
           try {
               JPanel button = buttonMap.get(keyChar);
               for (int i = 0; i < 7; i++) {
                   if (whiteKey[i] == keyChar) {
                       JPanel subButton = subWhiteButtonMap.get(keyChar);
                       subButton.setBackground(Color.WHITE);
                       button.setBackground(Color.WHITE);
                       break;
                   } else if (i < 6 && blackKey[i] == keyChar) {
                       button.setBackground(Color.BLACK);
                       break;
                   }
               }
               midiChannel.noteOff(noteMap.get(keyChar));
           } catch (Exception ex) {

           }
           isPressed.put(keyChar, false);
       }
   }
}