package Piano;


import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;

public class Piano extends JFrame {
    private Synthesizer synthesizer;
    private MidiChannel midiChannel;
    private char keyChar;
    private JLabel label;
    private Map<Character, JButton> buttonMap;      //각 버튼에 해당하는 키들을 맵핑
    private Map<Character, Integer> noteMap;        //각 키들에 해당
    private JPanel panel;
    private JPanel subPanel1;       //검은 검반이 올라갈 패널
    private JPanel subPanel2;       //흰 건반이 올라갈 패널
    private final char[] whiteKey = {'a','s','d','f','g','h','j'};    //흰 건반
    private int[] whiteNote = {60,62,64,65,67,69,71};       //흰 건반 소리
    private final char[] blackKey={'w','e','r','t','y','u'};          //검은 건반
    private int[] blackNote = {61,63,0,66,68,70};           //검은 건반 소리
    private int octave = 0;

    public Piano(){

        setTitle("Play Piano");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());                //south에 피아노 위치시킴

        panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.setPreferredSize(new Dimension(panel.getPreferredSize().width, 300));
        subPanel1 = new JPanel();                   //검은 건반 패널
        subPanel2 = new JPanel();                   //흰 건반 패널
        subPanel1.setLayout(new GridLayout(1, 6));
        subPanel2.setLayout(new GridLayout(1,7));

        try {                       //신디사이저 열기
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            midiChannel = synthesizer.getChannels()[0]; // 첫 번째 MIDI 채널 선택
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }


        label = new JLabel("getChar");              //어떤 키 눌렀는지 확인용
        label.setSize(150,10);
        label.setLocation(150,100);

        buttonMap = new HashMap<>();                //키와 버튼 맵핑
        noteMap = new HashMap<>();                  //키와 음 맵핑

        c.add(label, BorderLayout.WEST);
        JLabel showOctave = new JLabel("현재 옥타브: " + String.valueOf(octave));
        c.add(showOctave,BorderLayout.EAST);


        for(int i = 0; i < 12; i++){            //검은 건반 생성
            if(i == 0 || i == 2 || i == 4 || i == 5 || i == 7 || i == 9 || i == 11){
                JPanel empty = new JPanel();        //검은 건반들 사이의 빈 공간
                empty.setBackground(Color.WHITE);
                subPanel1.add(empty);//빈공간 추가
                continue;
            }
            JButton button = new JButton();
            button.setBackground(Color.black);
            buttonMap.put(blackKey[i/2], button);           //인덱스 맞춰서 맵핑
            noteMap.put(blackKey[i/2], blackNote[i/2]);
            subPanel1.add(button);              //패널에 추가
        }


        for(int i = 0; i < 7; i++){             //흰 건반 생성
            JButton button = new JButton();
            Border lineBorder = new LineBorder(Color.BLACK); // 원래 테두리 선
            Border emptyBorder = new EmptyBorder(0, 5, 5, 5); // 위쪽 여백만 적용
            CompoundBorder compoundBorder = new CompoundBorder(lineBorder, emptyBorder);
            button.setBackground(Color.WHITE);
            buttonMap.put(whiteKey[i], button);
            noteMap.put(whiteKey[i], whiteNote[i]);
            subPanel2.add(button);
        }

        panel.add(subPanel1);
        panel.add(subPanel2);

        c.add(panel,BorderLayout.SOUTH);
        c.addKeyListener(new KeyAdapter() {
            private Map<Character, Boolean> isPressed = new HashMap<>();        //중복입력 방지를 위해 추가

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                char keyChar = e.getKeyChar();
                label.setText(String.valueOf(keyChar));
                if(keyCode == VK_RIGHT){            //오른쪽 방향키는 옥타브 올리기
                    raiseOctave();
                    showOctave.setText("현재 옥타브: " + String.valueOf(octave));
                    return;
                }
                else if(keyCode == VK_LEFT){          //왼쪽 방향키는 옥타브 내리기
                    downOctave();
                    showOctave.setText("현재 옥타브: " + String.valueOf(octave));
                    return;
                }

                try{            //중복입력 방지(꾹 눌렀을 때 드르르르 현상 없애기)
                    if(isPressed.get(keyChar))
                        return;
                }
                catch (Exception ex){
                    //   isPressed.put(keyChar, false);
                }
                try {
                    JButton button = buttonMap.get(keyChar);
                    button.setBackground(Color.RED);
                    midiChannel.noteOn(noteMap.get(keyChar),150);
                }
                catch (Exception ex){
                    label.setText("None Exist Key");
                }
                isPressed.put(keyChar,true);            //해당 키가 눌려있음을 표시해둠
            }

            @Override
            public void keyReleased(KeyEvent e) {
                char keyChar = e.getKeyChar();
                try {
                    JButton button = buttonMap.get(keyChar);
                    for(int i = 0; i<7;i++){
                        if(whiteKey[i] == keyChar) {
                            button.setBackground(Color.WHITE);
                            break;
                        }
                        else if(i <6 && blackKey[i] == keyChar){
                            button.setBackground(Color.BLACK);
                            break;
                        }
                    }
                    midiChannel.noteOff(noteMap.get(keyChar));
                }
                catch (Exception ex) {
                    label.setText("None Exist Key");
                }
                isPressed.put(keyChar, false);      //해당 키의 상태를 눌려있지 않은 걸로 변경
            }
        });

        setSize(1200,800);
        setVisible(true);

        c.setFocusable(true);
        c.requestFocus();
    }

    public void raiseOctave(){          //옥타브 올리기
        Map<Character, Integer> tmp = new HashMap<>();

        for(int i = 0; i < 7; i++){
            tmp.put(whiteKey[i],noteMap.get(whiteKey[i])+12);       //임시 맵에 키들과 원래 해당 키의 밸류값을 한옥타브 올려서 저장
        }
        for(int i = 0; i < 6; i++){
            if(i==2)    continue;
            tmp.put(blackKey[i],noteMap.get(blackKey[i])+12);
        }
        noteMap = tmp;      //noteMap을 변경해줌
        octave++;
    }

    public void downOctave(){           //옥타브 내리기(위와 동일)
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
    }

    public static void main(String[] args){
        new Piano();
    }
}

