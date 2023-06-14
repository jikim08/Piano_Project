package Piano;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {
	private static final int FRAME_WIDTH = 1200;
	private static final int FRAME_HEIGHT = 800;
	private static final int NUM_TILES = 12;
	private static final int TILE_WIDTH = FRAME_WIDTH / NUM_TILES;
	private static final int TILE_HEIGHT = 30;
	private static final int TILE_SPEED = 5;
	private static final int PANEL_HEIGHT = 500;
	private static final int IMAGE_HEIGHT = FRAME_HEIGHT - PANEL_HEIGHT;
	private static final Color LINE_COLOR = Color.GRAY;
	private static final Color TILE_COLOR = Color.CYAN;

	private Timer timer;
	private List<Tile> tiles;
	private ImageIcon imageIcon;

	private Piano piano;
	private int score;
	private int trackNum;

	public Game(Piano piano, int trackNum) {
		this.piano = piano;
		this.trackNum = trackNum;
		setLayout(null);

		tiles = new ArrayList<>();
		timer = new Timer(10, this);
		timer.start();

		setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setBackground(Color.WHITE);

		imageIcon = new ImageIcon("image.jpg");

		mapTiles();

		// ...
	}

	/*
	 * tileMappings.add(new int[]{0, 0}); A tileMappings.add(new int[]{100, 0}); W
	 * tileMappings.add(new int[]{200, 0}); S tileMappings.add(new int[]{300, 0}); E
	 * tileMappings.add(new int[]{400, 0}); D tileMappings.add(new int[]{500, 0}); F
	 * tileMappings.add(new int[]{600, 0}); T tileMappings.add(new int[]{700, 0}); G
	 * tileMappings.add(new int[]{800, 0}); Y tileMappings.add(new int[]{900, 0}); H
	 * tileMappings.add(new int[]{1000, 0}); U tileMappings.add(new int[]{1100, 0});
	 * J
	 */
	private void mapTiles() {
		List<int[]> tileMappings = TileFactory.getTileMappings(trackNum);

		for (int[] mapping : tileMappings) {
			int x = mapping[0];
			int y = mapping[1];
			tiles.add(new Tile(x, y));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g.drawImage(imageIcon.getImage(), 0, PANEL_HEIGHT, FRAME_WIDTH, IMAGE_HEIGHT, null);

		g2d.setColor(LINE_COLOR);
		for (int i = 1; i < NUM_TILES; i++) {
			int x = i * TILE_WIDTH;
			g2d.draw(new Line2D.Double(x, 0, x, PANEL_HEIGHT));
		}

		for (Tile tile : tiles) {
			g.setColor(TILE_COLOR);
			g.fillRect(tile.getX(), tile.getY(), TILE_WIDTH, TILE_HEIGHT);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Tile tile : tiles) {
			tile.update();
			if ((tile.getY() < 530) && (tile.getY() > 470)) {
				switch (tile.getX()) {
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
		}

		repaint();
	}

	private class Tile {
		private int x;
		private int y;
		private boolean scored;

		public Tile(int x, int y) {
			this.x = x;
			this.y = y;
			this.scored = false;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public boolean isScored() {
			return scored;
		}

		public void setScored(boolean scored) {
			this.scored = scored;
		}

		public void update() {
			y += TILE_SPEED;
		}
	}

	public void judge(char kc) {
		for (Tile tile : tiles) {
			boolean checkPress;
			try {
				checkPress = piano.isPressed.get(kc);
				if (checkPress && !tile.isScored() && tile.getY() >= 470 && tile.getY() <= 530) {
					tile.setScored(true);
					score++;
					System.out.println("현재 Y 값: " + tile.getY());
					System.out.println("점수: " + score);
				} else {
					// 조건이 충족되지 않을 때 처리할 내용
				}
			} catch (Exception ex) {
				// 예외 처리
			}
		}
	}
}