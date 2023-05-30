package Piano;


import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
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
    private char[] whiteKey = {'a','s','d','f','g','h','j'};    //흰 건반
    private int[] whiteNote = {60,62,64,65,67,69,71};       //흰 건반 소리
    private char[] blackKey={'w','e','r','t','y','u'};          //검은 건반
    private int[] blackNote = {61,63,0,66,68,70};           //검은 건반 소리

    public Piano(){

        setTitle("keyEvent");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.setPreferredSize(new Dimension(panel.getPreferredSize().width, 300));
        subPanel1 = new JPanel();
        subPanel2 = new JPanel();
        subPanel1.setLayout(new GridLayout(1, 6));
        subPanel2.setLayout(new GridLayout(1,7));

        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            midiChannel = synthesizer.getChannels()[0]; // 첫 번째 MIDI 채널 선택
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }


        label = new JLabel("getChar");
        label.setSize(150,10);
        label.setLocation(150,100);
        buttonMap = new HashMap<>();
        noteMap = new HashMap<>();

        c.add(label, BorderLayout.NORTH);


        for(int i = 0; i < 12; i++){
            if(i == 0 || i == 2 || i == 4 || i == 5 || i == 7 || i == 9 || i == 11){
                subPanel1.add(new JPanel());//빈공간 추가
                continue;
            }
            JButton button = new JButton();
            button.setBackground(Color.black);
            //button.setPreferredSize(new Dimension(button.getPreferredSize().width,100));
            buttonMap.put(blackKey[i/2], button);
            noteMap.put(blackKey[i/2], blackNote[i/2]);
            subPanel1.add(button);
        }


        for(int i = 0; i < 7; i++){
            JButton button = new JButton();
            button.setBackground(Color.WHITE);
            //button.setPreferredSize(new Dimension(button.getPreferredSize().width,150));
            buttonMap.put(whiteKey[i], button);
            noteMap.put(whiteKey[i], whiteNote[i]);
            subPanel2.add(button);
        }

        /*for (int i = 0; i < 6; i++) {
            if (i == 2) continue;
            JButton button = buttonMap.get(sharpKey[i]);
            c.setComponentZOrder(button, 0); // 0은 가장 위로 올리는 인덱스입니다.
        }*/
        panel.add(subPanel1);
        panel.add(subPanel2);

        c.add(panel,BorderLayout.SOUTH);
        c.addKeyListener(new KeyAdapter() {
            private Map<Character, Boolean> isPressed = new HashMap<>();

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                char keyChar = e.getKeyChar();
                label.setText(String.valueOf(keyChar));
                if(keyCode == VK_RIGHT){
                    raiseOctave();
                    return;
                }
                else if(keyCode == VK_LEFT){
                    downOctave();
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
                isPressed.put(keyChar,true);
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
                isPressed.put(keyChar, false);
            }
        });

        setSize(1200,800);
        setVisible(true);

        c.setFocusable(true);
        c.requestFocus();
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
    }

    public static void main(String[] args){
        new Piano();
    }
}

