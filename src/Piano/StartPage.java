package Piano;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javazoom.jl.player.Player;

public class StartPage extends JFrame {
    private static final long serialVersionUID = 1L;
    private Image screenImage;
    private Graphics screenGraphic;

    private ImageIcon startButtonBasicImage = new ImageIcon(getClass().getResource("Piano/images/startButtonBasic3.png"));
    private ImageIcon quitButtonBasicImage = new ImageIcon(getClass().getResource("Piano/images/quitButtonBasic.png"));
    private ImageIcon pointButtonBasicImage = new ImageIcon(getClass().getResource("Piano/images/pointButton.png"));


    private JButton startButton = new JButton(startButtonBasicImage);
    private JButton quitButton = new JButton(quitButtonBasicImage);
    private JButton pointButton = new JButton(pointButtonBasicImage);

    private Player player;
    private boolean isLoop;
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;

    private int mouseX, mouseY;
    private MyPanel panel = new MyPanel();

    public StartPage(Map<String, String> map) {


        setTitle("Piano");
        setSize(1200, 800);
        Container c = getContentPane();
        c.setLayout(null);
        panel.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        isLoop = false;

        startButton.setBounds(690, 440, 500, 200);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                start("buttonPressedMusic.mp3", false);
                //게임 시작 이벤트
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
                start("buttonPressedMusic.mp3", false);
            }
        });
        panel.add(quitButton);




        Object[][] data = convertMapToArray(map);
        // 첫 번째 행에 열 이름을 저장
        String[] headers = (String[]) data[0];
        // 나머지 행은 JTable의 데이터로 사용
        Object[][] tableData = Arrays.copyOfRange(data, 1, data.length);
        // 테이블 모델을 생성하고 데이터와 열 이름을 설정
        DefaultTableModel model = new DefaultTableModel(tableData, headers);
        // 모델로 부터 테이블을 생성
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        table.setOpaque(true); // 테이블을 하얗게 설정
        table.setBackground(new Color(255, 255, 255));

        // 테이블의 헤더도 투명하게 설정
        table.getTableHeader().setOpaque(true); // 테이블 헤더를 하얗게 설정
        table.getTableHeader().setBackground(new Color(255, 255, 255));

        // 스크롤 패널에 테이블을 추가
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 270, 200, 400);

        // 스크롤 패널과 뷰포트의 투명도를 설정한다. 이 부분은 테이블의 배경색이 정상적으로 나타나게 하기 위한 설정이다
        scrollPane.setOpaque(false); // 스크롤 패널의 투명도를 설정
        scrollPane.getViewport().setOpaque(false); // 스크롤 패널의 뷰포트 투명도를 설정

        pointButton.setBounds(20, 650, 100, 100);
        pointButton.setBorderPainted(false);
        pointButton.setContentAreaFilled(false);
        pointButton.setFocusPainted(false);
        pointButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panel.add(scrollPane);
            }
        });
        panel.add(pointButton);  ////////////////// (슬레쉬바 사이 전부다 새로운것)

        panel.setSize(1200, 800);
        panel.setLocation(0, 0);
        c.add(panel);

        start("introMusic.mp3", true);
    }

    class MyPanel extends JPanel {
        private ImageIcon icon = new ImageIcon("src/images/introBackground2.jpg");
        private Image img = icon.getImage();

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, 1200, 800, this);
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

    private static Object[][] convertMapToArray(Map<String, String> map) {
        // Map에서 Key만 추출
        String[] columns = map.keySet().toArray(new String[0]);

        // Map에서 Value만 추출
        String[] values = map.values().toArray(new String[0]);

        // 이차원 배열로 변환
        Object[][] dataArray = new Object[map.size()][2];
        for (int i = 0; i < map.size(); i++) {
            dataArray[i][0] = columns[i];
            dataArray[i][1] = values[i];
        }

        // 각 행에 헤더 값을 추가
        String[] headers = {"등수", "점수"};
        Object[][] result = new Object[dataArray.length + 1][];
        result[0] = headers;
        System.arraycopy(dataArray, 0, result, 1, dataArray.length);

        return result;
    }////////////슬래쉬바 사이에 전부 추가된것
}