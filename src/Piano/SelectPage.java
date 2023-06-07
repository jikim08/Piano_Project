package Piano;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectPage extends JFrame {

    private Image screenImage;
    private Graphics screenGraphic;
    private Clip clip;

    private ImageIcon leftButtonImage = new ImageIcon(SelectPage.class.getResource("images//leftButtonBasic.png"));
    private ImageIcon rightButtonImage = new ImageIcon(SelectPage.class.getResource("images//rightButtonBasic.png"));
    private ImageIcon leftButtonEnteredImage = new ImageIcon(SelectPage.class.getResource("images//leftButtonEntered.png"));
    private ImageIcon rightButtonEnteredImage = new ImageIcon(SelectPage.class.getResource("images//rightButtonEntered.png"));
    private ImageIcon startButtonImage = new ImageIcon(SelectPage.class.getResource("images//startButton.png"));
    private ImageIcon startButtonEnteredImage = new ImageIcon(SelectPage.class.getResource("images//startEnteredButton.png"));

    private Image introBackground = new ImageIcon(SelectPage.class.getResource("images//pianoBackground.jpg")).getImage();
    private ImageIcon selectedImage[] = {
        new ImageIcon(SelectPage.class.getResource("images//Airplane.jpg")),
        new ImageIcon(SelectPage.class.getResource("images//Butterfly.jpg")),
        new ImageIcon(SelectPage.class.getResource("images//ThreeBears.jpg"))
    };

    private JButton leftButton = new JButton(leftButtonImage);
    private JButton rightButton = new JButton(rightButtonImage);
    private JButton startButton = new JButton(startButtonImage);

    private int nowSelected = 0;
    
    private Audio[] selectedBackgroundMusic = {new Audio("src/Piano/musics/airplane.wav", true), new Audio("src/Piano/musics/butterfly.wav", true), new Audio("src/Piano/musics/threebears.wav",true)};
    private Audio selectedButtonSound = new Audio("src/Piano/musics/buttonEnteredMusic.wav", false);

    private JLabel imageLa;

    public SelectPage() {
        MyPanel BackPanel = new MyPanel();
        imageLa = new JLabel(selectedImage[nowSelected]);
        setTitle("리듬 게임 곡 선택");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        BackPanel.setLayout(new BorderLayout());
        BackPanel.add(imageLa, BorderLayout.CENTER);
        selectedBackgroundMusic[0].start();

        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFocusPainted(false);
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftButton.setIcon(leftButtonEnteredImage);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftButton.setIcon(leftButtonImage);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            	selectedButtonSound.start();
            	selectedBackgroundMusic[nowSelected].stop();
               if(nowSelected == 0)
                  nowSelected = selectedImage.length - 1;
               else
                  nowSelected--;
                imageLa.setIcon(selectedImage[nowSelected]);
                selectedBackgroundMusic[nowSelected].start();
            }
        });
        BackPanel.add(leftButton, BorderLayout.WEST);

        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFocusPainted(false);
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rightButton.setIcon(rightButtonEnteredImage);
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rightButton.setIcon(rightButtonImage);
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            	selectedButtonSound.start();
            	selectedBackgroundMusic[nowSelected].stop();
               if(nowSelected == selectedImage.length - 1)
                  nowSelected = 0;
               else
                  nowSelected++;
                    imageLa.setIcon(selectedImage[nowSelected]);
                    selectedBackgroundMusic[nowSelected].start();
                }
        });
        BackPanel.add(rightButton, BorderLayout.EAST);
        
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	startButton.setIcon(startButtonEnteredImage);
            	startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	startButton.setIcon(startButtonImage);
            	startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            	selectedButtonSound.start();
            	//게임 화면으로 넘어가기
                //음악 집어 넣기
            }
        });
        BackPanel.add(startButton, BorderLayout.SOUTH);
        setContentPane(BackPanel);

        setSize(1200, 800);
        setVisible(true);
    }
    
    public void playSound(String pathName, boolean isLoop) {
    	try {
    		clip = AudioSystem.getClip();
    		File audioFile = new File(pathName);
    		AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
    		clip.open(audioStream);
    		clip.start();
    		if(isLoop)
    			clip.loop(Clip.LOOP_CONTINUOUSLY);
    	} catch(LineUnavailableException e){
    		e.printStackTrace();
    	}
    	catch(UnsupportedAudioFileException e){
    		e.printStackTrace();
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    class MyPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(introBackground, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        new SelectPage();
    }

}