package Piano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SelectPage extends JFrame {

    private Image screenImage;
    private Graphics screenGraphic;

    private ImageIcon leftButtonImage = new ImageIcon(SelectPage.class.getResource("leftButtonBasic.png"));
    private ImageIcon rightButtonImage = new ImageIcon(SelectPage.class.getResource("rightButtonBasic.png"));
    private ImageIcon leftButtonEnteredImage = new ImageIcon(SelectPage.class.getResource("leftButtonEntered.png"));
    private ImageIcon rightButtonEnteredImage = new ImageIcon(SelectPage.class.getResource("rightButtonEntered.png"));

    private Image introBackground = new ImageIcon(SelectPage.class.getResource("pianoBackground.jpg")).getImage();;
    private ImageIcon selectedImage = new ImageIcon(SelectPage.class.getResource("Airplane.jpg"));

    private JButton leftButton = new JButton(leftButtonImage);
    private JButton rightButton = new JButton(rightButtonImage);

    private int mouseX, mouseY;


    public SelectPage() {
        MyPanel BackPanel = new MyPanel();
        JLabel imageLa = new JLabel(selectedImage);


        setTitle("리듬 게임 곡 선택");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();

        c.setLayout(new BorderLayout());
        BackPanel.setLayout(new BorderLayout());

        BackPanel.add(imageLa,BorderLayout.CENTER);


        //leftButton.setVisible(true);
        leftButton.setBounds(140, 310, 60, 60);
        leftButton.setBorderPainted(false); // 형태 그대로 출력되게
        leftButton.setContentAreaFilled(false); // 위와 동일
        leftButton.setFocusPainted(false); // 위와 동일
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftButton.setIcon(leftButtonEnteredImage);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                //music 넣어도 됨
            }
            @Override
            public void mouseExited(MouseEvent e) {
                leftButton.setIcon(leftButtonImage);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
            } //왼쪽 버튼 이벤트

        });
        BackPanel.add(leftButton,BorderLayout.WEST);

        rightButton.setBounds(1080, 310, 60, 60);
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFocusPainted(false);
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rightButton.setIcon(rightButtonEnteredImage);
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                //music 넣어도 됨
            }
            @Override
            public void mouseExited(MouseEvent e) {
                rightButton.setIcon(rightButtonImage);
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                //오른쪽 버튼 이벤트
            }
        });
        BackPanel.add(rightButton, BorderLayout.EAST);
        setContentPane(BackPanel);

        setSize(1200, 800);
        setVisible(true);
    }

    class MyPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(introBackground,0,0,getWidth(),getHeight(),this);
        }
    }


    public static void main(String[] args){
        new SelectPage();
    }

}