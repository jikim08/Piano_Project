package Fullproject.Melody;

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
    public Map<Character, JPanel> buttonMap;        //건반과 키를 맵핑
    public Map<Character, JPanel> subWhiteButtonMap;    //흰건반 윗부분
    public Map<Character, Integer> noteMap;     //음과 키를 맵핑
    private JPanel subPanel1;           //검은 건반이 올라갈 패널
    private JPanel subPanel2;           //흰건반이 올라갈 패널
    public final char[] whiteKey = {'a','s','d','f','g','h','j'};       //흰 건반 키
    public int[] whiteNote = {60,62,64,65,67,69,71};            //흰 건반 음
    public final char[] blackKey={'w','e','r','t','y','u'};     //검은 건반 키
    public int[] blackNote = {61,63,0,66,68,70};        //검은 건반 음
    private int octave = 0;             //현재 옥타브
    public JLabel octaveLa = new JLabel("현재 옥타브: " + octave);       //옥타브를 표시할 레이블
    public Map<Character, Boolean> isPressed = new HashMap<>();     //키가 눌려있는지 확인하기 위한 맵

    public Piano(){
        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(getPreferredSize().width, 300));
        subPanel1 = new JPanel();
        subPanel2 = new JPanel();
        subPanel1.setLayout(null);
        subPanel2.setLayout(null);

        try {           //신디사이저 기능 열기
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            midiChannel = synthesizer.getChannels()[0];
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }

        buttonMap = new HashMap<>();
        subWhiteButtonMap = new HashMap<>();
        noteMap = new HashMap<>();

        for(int i = 0; i < 12; i++){            //검은 건반
            if(i == 0 || i == 2 || i == 4 || i == 5 || i == 7 || i == 9 || i == 11){        //흰 건반 윗부분이 들어감
                JPanel empty = new JPanel();
                subWhiteButtonMap.put(whiteKey[(i+1)/2],empty);     //윗쪽 검은 건반 사이에 흰 건반들도 색이 바뀔 수 있도록 맵핑해둠
                empty.setSize(100,150);
                empty.setLocation(i*100,0);
                empty.setBackground(Color.WHITE);
                empty.setBorder(BorderFactory.createMatteBorder(1,1,0,1,Color.BLACK));
                subPanel1.add(empty);
                continue;
            }
            JPanel button = new JPanel();               //검은 건반들
            button.setSize(100,150);
            button.setLocation(i*100,0);
            button.setBackground(Color.black);
            buttonMap.put(blackKey[i/2], button);
            noteMap.put(blackKey[i/2], blackNote[i/2]);
            subPanel1.add(button);
        }

        for(int i = 0; i < 7; i++){             //흰건반
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

    public void raiseOctave(){              //옥타브를 올리는 함수
        Map<Character, Integer> tmp = new HashMap<>();

        for(int i = 0; i < 7; i++){                 //전체적으로 음의 높이를 12만큼(한옥타브) 올려준 뒤
            tmp.put(whiteKey[i],noteMap.get(whiteKey[i])+12);
        }
        for(int i = 0; i < 6; i++){
            if(i==2)    continue;
            tmp.put(blackKey[i],noteMap.get(blackKey[i])+12);
        }
        noteMap = tmp;                      //기존 맵을 바꿔주고
        octave++;               //옥타브를 올림
        octaveLa.setText("현재 옥타브: " + octave);
    }

    public void downOctave(){               //옥타브를 내리는 함수(위와 동일)
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
       public void keyPressed(KeyEvent e) {     //키가 눌리면
           int keyCode = e.getKeyCode();
           char keyChar = e.getKeyChar();
           if (keyCode == VK_RIGHT) {           //방향키로 옥타브가 조절되게 설정
               raiseOctave();
               return;
           } else if (keyCode == VK_LEFT) {
               downOctave();
               return;
           }

           try {                    //눌려있는지 확인해서 이미 눌려있는 키면 소리가 연속해서 안나도록 설정. (드르르르 방지)
               if (isPressed.get(keyChar))
                   return;
           } catch (Exception ex) {

           }
           try {
               midiChannel.noteOn(noteMap.get(keyChar), 150);       //음 재생
               JPanel button = buttonMap.get(keyChar);
               button.setBackground(Color.RED);     //색 변경

               button = subWhiteButtonMap.get(keyChar);
               button.setBackground(Color.RED);

           } catch (Exception ex) {

           }
           isPressed.put(keyChar, true);        //해당 키가 눌려있음을 표시
       }

       @Override
       public void keyReleased(KeyEvent e) {
           char keyChar = e.getKeyChar();
           try {
               JPanel button = buttonMap.get(keyChar);
               for (int i = 0; i < 7; i++) {            //색을 원래대로 바꿔주고
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
               midiChannel.noteOff(noteMap.get(keyChar));       //음을 정지시킴
           } catch (Exception ex) {

           }
           isPressed.put(keyChar, false);       //해당 키가 더 이상 눌려있지 않음을 표시
       }
   }
}