package Piano;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener 
{
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 800;
    private static final int NUM_TILES = 12;
    private static final int TILE_WIDTH = FRAME_WIDTH / NUM_TILES;
    private static final int TILE_HEIGHT = 30;
    private static final int TILE_SPEED = 5;
    private static final int PANEL_HEIGHT = 500;
    private static final int IMAGE_HEIGHT = FRAME_HEIGHT - PANEL_HEIGHT;
    private static final Color LINE_COLOR = Color.GRAY;

    private Timer timer;
    private List<Tile> tiles;
    private ImageIcon imageIcon;
    
    private Piano piano;
    private int score;

    public Game(Piano piano) 
    {
        this.piano = piano;
        setLayout(null); // 컴포넌트의 위치를 직접 설정하기 위해 레이아웃을 null로 설정합니다.

        tiles = new ArrayList<>();
        timer = new Timer(10, this); // 10ms마다 actionPerformed 이벤트가 발생하도록 타이머를 설정합니다.
        timer.start();

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.WHITE);

        // 이미지 로드
        imageIcon = new ImageIcon("image.jpg");
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // 이미지 그리기
        g.drawImage(imageIcon.getImage(), 0, PANEL_HEIGHT, FRAME_WIDTH, IMAGE_HEIGHT, null);

        // 구분선 그리기
        g2d.setColor(LINE_COLOR);
        for (int i = 1; i < NUM_TILES; i++) {
            int x = i * TILE_WIDTH;
            g2d.draw(new Line2D.Double(x, 0, x, PANEL_HEIGHT));
        }

        // 타일 그리기
        for (Tile tile : tiles) 
        {
            g.setColor(tile.getColor());
            g.fillRect(tile.getX(), tile.getY(), TILE_WIDTH, TILE_HEIGHT);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // 새로운 타일 추가
        if (Math.random() < 0.05) { // 일정 확률로 새로운 타일 생성
            Random random = new Random();
            int x = random.nextInt(NUM_TILES) * TILE_WIDTH;
            int y = -TILE_HEIGHT;
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            // 중복 검사
            boolean isOverlapping = false;
            for (Tile existingTile : tiles) {
                if (existingTile.getX() == x && existingTile.getY() < y + TILE_HEIGHT) {
                    isOverlapping = true;
                    break;
                }
            }

            if (!isOverlapping) {
                tiles.add(new Tile(x, y, color));
            }
        }

        // 타일 업데이트
        Iterator<Tile> iterator = tiles.iterator();
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            tile.update();
            if((tile.getY() < 530) && (tile.getY() > 470)){
                switch (tile.getX()){
                    case 0:
                        this.judge('a');
                        break;
                    case 100:
                        this.judge('w');
                        break;
                    case 200:
                        this.judge('s');
                        break;
                    case 300:
                        this.judge('e');
                        break;
                    case 400:
                        this.judge('d');
                        break;
                    case 500:
                        this.judge('f');
                        break;
                    case 600:
                        this.judge('t');
                        break;
                    case 700:
                        this.judge('g');
                        break;
                    case 800:
                        this.judge('y');
                        break;
                    case 900:
                        this.judge('h');
                        break;
                    case 1000:
                        this.judge('u');
                        break;
                    case 1100:
                        this.judge('j');
                        break;

                }
            }

            // 화면 아래로 벗어난 타일 제거
            if (tile.getY() >= PANEL_HEIGHT) {
                iterator.remove();
            }
        }

        repaint();
    }

    private class Tile 
    {
        private int x;
        private int y;
        private Color color;

        public Tile(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Color getColor() {
            return color;
        }

        // 이건 좀따 만지자
        public void update() {
            y += TILE_SPEED;
        }
    }
    public void judge(char kc) {
        for (Tile tile : tiles) {
            boolean checkPress;
            try {
                checkPress = piano.isPressed.get(kc);
                if (checkPress && ((tile.getY() < 530) && (tile.getY() > 470))) {
                    score++;
                    System.out.println("Current Y Value: " + tile.getY());
                    System.out.println("Score: " + score);
                } else {
                    // Do something else if the condition is not met
                }
            }
            catch (Exception ex){
            }
        }
    }

}
