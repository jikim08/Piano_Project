package Fullproject.Melody;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javazoom.jl.player.Player;

public class StartPage extends JFrame 
{
    private static final long serialVersionUID = 1L;
    private Image screenImage;
    private Graphics screenGraphic;

    private final ImageIcon startButtonBasicImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Fullproject/images/startButtonBasic.png")));
    private final ImageIcon quitButtonBasicImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Fullproject/images/quitButtonBasic.png")));

    private JButton startButton = new JButton(startButtonBasicImage);
    private JButton quitButton = new JButton(quitButtonBasicImage);

    private Player player;
    private boolean isLoop;
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;

    private int mouseX, mouseY;

    public StartPage() {


        setTitle("Piano");
        setSize(1200, 800);
        Container c = getContentPane();
        c.setLayout(null);

        MyPanel panel = new MyPanel();
        panel.setLayout(null);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        c.setLayout(null);

        isLoop = false;

        startButton.setBounds(690, 440, 500, 200);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //start("buttonPressedMusic.mp3", false);
                dispose();
                new ShowPianoClass();
            }
        });
        panel.add(startButton);

        quitButton.setBounds(690, 580, 500, 200);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //start("buttonPressedMusic.mp3", false);
                dispose();
                new SelectPage();
            }
        });
        panel.add(quitButton);

        panel.setSize(1200,800);
        panel.setLocation(0,0);
        setContentPane(panel);
        //start("introMusic.mp3", true);
    }

    class MyPanel extends JPanel {
        private ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Fullproject/images/pianoBackground.jpg")));
        private Image img = icon.getImage();

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0,1200,800 ,this);
        }
    }


    public void start(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            file = new File(getClass().getResource("/music/" + name).toURI());
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);

            do {
                player.play();
                player.close();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } while (isLoop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
