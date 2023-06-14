package Fullproject.Melody;



import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectPage extends JFrame
{
    //이미지 아이콘들과 이미지들을 초기화 합니다.
    private ImageIcon leftButtonImage = new ImageIcon(SelectPage.class.getResource("/Fullproject/images/leftButtonBasic.png"));
    private ImageIcon rightButtonImage = new ImageIcon(SelectPage.class.getResource("/Fullproject/images/rightButtonBasic.png"));
    private ImageIcon leftButtonEnteredImage = new ImageIcon(SelectPage.class.getResource("/Fullproject/images/leftButtonEntered.png"));
    private ImageIcon rightButtonEnteredImage = new ImageIcon(SelectPage.class.getResource("/Fullproject/images/rightButtonEntered.png"));
    private ImageIcon startButtonImage = new ImageIcon(SelectPage.class.getResource("/Fullproject/images/startButton.png"));
    private ImageIcon startButtonEnteredImage = new ImageIcon(SelectPage.class.getResource("/Fullproject/images/startEnteredButton.png"));
    private ImageIcon backButtonImage = new ImageIcon(SelectPage.class.getResource("/Fullproject/images/backButton.png"));
    private ImageIcon backButtonEnteredImage = new ImageIcon(SelectPage.class.getResource("/Fullproject/images/backButton.png"));

    private Image introBackground = new ImageIcon(SelectPage.class.getResource("/Fullproject/images/pianoBackground.jpg")).getImage();
    private ImageIcon selectedImage[] =
            {
                    new ImageIcon(SelectPage.class.getResource("/Fullproject/images/Airplane.jpg")),
                    new ImageIcon(SelectPage.class.getResource("/Fullproject/images/Butterfly.jpg")),
                    new ImageIcon(SelectPage.class.getResource("/Fullproject/images/ThreeBears.jpg"))
            };

    //버튼들을 초기화 합니다.
    private JButton leftButton = new JButton(leftButtonImage);
    private JButton rightButton = new JButton(rightButtonImage);
    private JButton startButton = new JButton(startButtonImage);
    private JButton backButton = new JButton(backButtonImage);

    //현재 선택한 인덱스를 나타냅니다.
    private int nowSelected = 0;

    //오디오 클래스를 이용한 백그라운드 뮤직과 버튼을 선택했을 때의 효과음입니다.
    private Audio[] selectedBackgroundMusic = {new Audio("src/Piano/musics/airplane.wav", true), new Audio("src/Piano/musics/butterfly.wav", true), new Audio("src/Piano/musics/threebears.wav",true)};
    private Audio selectedButtonSound = new Audio("src/Piano/musics/buttonEnteredMusic.wav", false);

    /* 배열 생성해서 생성자 만든 다음에 누르면 넘어가게 해야 돼요 */
    //private ShowPianoClass[] seletedPianoGame = {new ShowPianoClass}

    private JLabel imageLa;

    public SelectPage()
    {
        //패널 생성해서 컴포넌트 추가하기
        MyPanel BackPanel = new MyPanel();
        imageLa = new JLabel(selectedImage[nowSelected]);
        //창 설정
        imageLa.setSize(600,600);
        imageLa.setLocation(300,100);
        setTitle("리듬 게임 곡 선택");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //버튼들을 수동적으로 위치시키기 위한 설정
        BackPanel.setLayout(null);
        BackPanel.add(imageLa);

        //현재 선택된 인덱스에 맞는 백그라운드 뮤직 시작
        selectedBackgroundMusic[nowSelected].start();

        //LEFT버튼 설정
        leftButton.setSize(50,50);
        leftButton.setLocation(10,375);
        //버튼 디테일 설정
        leftButton.setBorderPainted(false); // 테두리 X
        leftButton.setContentAreaFilled(false); // 버튼의 배경을 채우지 않도록
        leftButton.setFocusPainted(false); // 선택이 되었을 때 테두리가 생기지 않도록 설정
        leftButton.addMouseListener(new MouseAdapter() {

            @Override // 버튼 위에 마우스가 올라가면
            public void mouseEntered(MouseEvent e) {
                //이미지와 커서를 변경
                leftButton.setIcon(leftButtonEnteredImage);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override // 버튼 위에서 마우스가 내려가면
            public void mouseExited(MouseEvent e) {
                //이미지와 커서를 본래의 것으로 다시 변경
                leftButton.setIcon(leftButtonImage);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override //버튼이 눌리면
            public void mousePressed(MouseEvent e) {
                // 효과음이 나도록 설정
                selectedButtonSound.start();
                //본래의 백그라운드 뮤직과 사진을 종료 후 인덱스에 맞는 사진과 음악이 나오게 설정
                selectedBackgroundMusic[nowSelected].stop();
                if(nowSelected == 0)
                    nowSelected = selectedImage.length - 1;
                else
                    nowSelected--;
                imageLa.setIcon(selectedImage[nowSelected]);
                selectedBackgroundMusic[nowSelected].start();
            }
        });
        BackPanel.add(leftButton);
        // 다른 버튼들의 전부 비슷한 기능들은 주석 적지 않았습니다!
        // 다른 기능들은 적었습니다!

        rightButton.setSize(50,50);
        rightButton.setLocation(1120,375);
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
        BackPanel.add(rightButton);

        startButton.setSize(200,100);
        startButton.setLocation(500,650);
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

            @Override // 버튼 클릭시
            public void mousePressed(MouseEvent e) {
                selectedButtonSound.start(); // 효과음 설정
                dispose(); //해당 창을 종료
                new ShowPianoGameClass(nowSelected); //게임 클래스로 넘어감
                selectedBackgroundMusic[nowSelected].stop();
                //백그라운드 뮤직 종료
            }
        });
        BackPanel.add(startButton);

        backButton.setSize(51,51);
        backButton.setLocation(10, 10);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backButtonEnteredImage);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(backButtonImage);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
                new StartPage();
                selectedButtonSound.start();
                selectedBackgroundMusic[nowSelected].stop();
                //메인클래스로 넘어가는것
            }
        });
        BackPanel.add(backButton);

        //컴포넌트를 프레임의 기본 콘텐츠 영역으로 설정
        //추가된 컴포넌트들은 이 영역 위로 그려짐
        setContentPane(BackPanel);

        setSize(1200, 800);
        setVisible(true);
    }

    //패널 클래스 생성
    class MyPanel extends JPanel {
        //패널의 그래픽을 그리는 역할입니다.
        @Override
        public void paintComponent(Graphics g) {
            //기본적인 패널의 그리기를 시작합니다.
            super.paintComponent(g);
            //백그라운드 이미지를 크기에 맞게 그립니다.
            g.drawImage(introBackground, 0, 0, getWidth(), getHeight(), this);
        }
    }


}