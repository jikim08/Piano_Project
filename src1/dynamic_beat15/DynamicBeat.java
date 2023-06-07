package dynamic_beat15;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {
	private Image screenImage; // doubleBuffering을 위한 인스턴스
	private Graphics screenGraphic;

	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private int mouseX, mouseY; // mouse의 x,y좌표
	private boolean isMainScreen = false; // Main화면이면 true
	private boolean isGameScreen = false;

	ArrayList<Track> trackList = new ArrayList<Track>();
	private Music selectedMusic;
	private Image selectedImage;
	private Image titleImage;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0;	// 현재 선택된 트랙 번호
	
	public static Game game;
	
	public DynamicBeat() {
		// mp3파일을 가장 먼저 로드함
		// mp3파일을 중간에 로드하면 음원이 나오기전에 노트가 내려오거나 키보드입력 이벤트가 이루어질 수 있음
		trackList.add(new Track("Mighty Love Title Image.png","Mighty Love Start Image.png", "Mighty Love Game Image.jpg","Nocturne.mp3", "Nocturne.mp3","Nocturne"));
		trackList.add(new Track("Wild Flower Title Image.png","Wild Flower Start Image.png", "Wild Flower Game Image.jpg","Sailor's Song.mp3", "Sailor's Song.mp3","Sailor's Song"));
		trackList.add(new Track("Energy Title Image.png","Energy Start Image.png", "Energy Game Image.png","Sonatina No 2.mp3", "Sonatina No 2.mp3","Test Title"));
		
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // w,h 임의변경 불가하도록 고정 -> 창크기 맘대로 못바꿈
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		setBackground(new Color(0, 0, 0, 0)); // paintComponent시 배경이 회색->흰색
		setLayout(null); // 버튼 등 위치
		
		addKeyListener(new KeyListener());
		
		introMusic.start();
		
		// exitButton 설정
		exitButton.setBounds(1170, 0, 30, 30); // 메뉴바의 가장 오른쪽
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			// 마우스커서가 버튼에 올라왔을때
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서가 버튼에 올라가면 손가락 모양으로

				// 버튼에 커서 올라가면 소리남
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(50);
					// 종료스레드를 1000*0.001초 = 1초 동안 재움
				} catch (InterruptedException ex) {
					ex.printStackTrace();
					// 이건 솔직히 뭔지 모르겠음
				}
			}

			// 마우스커서가 버튼을 벗어났을때
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				// exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 버튼을 탈출하면 원래 커서로 돌려놓는 기능
				// 이거 안해도 원래 화살표모양으로 잘 돌아옴
			}

			@Override
			// 버튼을 클릭했을 때 창을 종료
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(500);
					// 종료스레드를 1000*0.001초 = 1초 동안 재움
				} catch (InterruptedException ex) {
					ex.printStackTrace();
					// 이건 솔직히 뭔지 모르겠음
				}
				System.exit(0);
			}
		});
		add(exitButton);
		
		startButton.setBounds(40, 200, 400, 100); // 메뉴바의 가장 오른쪽
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			// 마우스커서가 버튼에 올라왔을때
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서가 버튼에 올라가면 손가락 모양으로

				// 버튼에 커서 올라가면 소리남
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(50);
					// 종료스레드를 1000*0.001초 = 1초 동안 재움
				} catch (InterruptedException ex) {
					ex.printStackTrace();
					// 이건 솔직히 뭔지 모르겠음
				}

			}

			// 마우스커서가 버튼을 벗어났을때
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				// startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 버튼을 탈출하면 원래 커서로 돌려놓는 기능
				// 이거 안해도 원래 화살표모양으로 잘 돌아옴
			}

			@Override
			// 버튼을 클릭했을 때 창을 종료
			public void mousePressed(MouseEvent e)
			{
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterMain();

			}
		});
		add(startButton);
		
		quitButton.setBounds(40, 350, 400, 100); // 메뉴바의 가장 오른쪽
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			// 마우스커서가 버튼에 올라왔을때
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서가 버튼에 올라가면 손가락 모양으로

				// 버튼에 커서 올라가면 소리남
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();

			}

			// 마우스커서가 버튼을 벗어났을때
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				// quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 버튼을 탈출하면 원래 커서로 돌려놓는 기능
				// 이거 안해도 원래 화살표모양으로 잘 돌아옴
			}

			@Override
			// 버튼을 클릭했을 때 창을 종료
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(50);
					// 종료스레드를 1000*0.001초 = 1초 동안 재움
				} catch (InterruptedException ex) {
					ex.printStackTrace();
					// 이건 솔직히 뭔지 모르겠음
				}
				System.exit(0);
				
			}
		});
		add(quitButton);
		
		leftButton.setVisible(false); // 메인스크린에서는 보이지 않도록 처리
		leftButton.setBounds(140, 310, 60, 60); // 메뉴바의 가장 오른쪽
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			// 마우스커서가 버튼에 올라왔을때
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서가 버튼에 올라가면 손가락 모양으로

				// 버튼에 커서 올라가면 소리남
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				

			}

			// 마우스커서가 버튼을 벗어났을때
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				// leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 버튼을 탈출하면 원래 커서로 돌려놓는 기능
				// 이거 안해도 원래 화살표모양으로 잘 돌아옴
			}

			@Override
			// 버튼을 클릭했을 때 창을 종료
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectLeft();
				
			}
		});
		add(leftButton);
		
		rightButton.setVisible(false); // 메인스크린에서는 보이지 않도록 처리
		rightButton.setBounds(1100, 310, 60, 60); // 메뉴바의 가장 오른쪽
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			// 마우스커서가 버튼에 올라왔을때
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서가 버튼에 올라가면 손가락 모양으로

				// 버튼에 커서 올라가면 소리남
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				

			}

			// 마우스커서가 버튼을 벗어났을때
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				// rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 버튼을 탈출하면 원래 커서로 돌려놓는 기능
				// 이거 안해도 원래 화살표모양으로 잘 돌아옴
			}

			@Override
			// 버튼을 클릭했을 때 창을 종료
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectRight();
				
			}
		});
		add(rightButton);

		easyButton.setVisible(false); // 메인스크린에서는 보이지 않도록 처리
		easyButton.setBounds(375, 580, 250, 67); // 메뉴바의 가장 오른쪽
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			// 마우스커서가 버튼에 올라왔을때
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서가 버튼에 올라가면 손가락 모양으로

				// 버튼에 커서 올라가면 소리남
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				

			}

			// 마우스커서가 버튼을 벗어났을때
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				// easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 버튼을 탈출하면 원래 커서로 돌려놓는 기능
				// 이거 안해도 원래 화살표모양으로 잘 돌아옴
			}

			@Override
			// 버튼을 클릭했을 때 창을 종료
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy");
				
			}
		});
		add(easyButton);
		
		hardButton.setVisible(false); // 메인스크린에서는 보이지 않도록 처리
		hardButton.setBounds(650, 580, 250, 67); // 메뉴바의 가장 오른쪽
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			// 마우스커서가 버튼에 올라왔을때
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서가 버튼에 올라가면 손가락 모양으로

				// 버튼에 커서 올라가면 소리남
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				

			}

			// 마우스커서가 버튼을 벗어났을때
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				// hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 버튼을 탈출하면 원래 커서로 돌려놓는 기능
				// 이거 안해도 원래 화살표모양으로 잘 돌아옴
			}

			@Override
			// 버튼을 클릭했을 때 창을 종료
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard");
				
			}
		});
		add(hardButton);
		
		backButton.setVisible(false); // 메인스크린에서는 보이지 않도록 처리
		backButton.setBounds(20, 50, 60, 60); // 메뉴바의 가장 오른쪽
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			// 마우스커서가 버튼에 올라왔을때
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서가 버튼에 올라가면 손가락 모양으로

				// 버튼에 커서 올라가면 소리남
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				

			}

			// 마우스커서가 버튼을 벗어났을때
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				// backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 버튼을 탈출하면 원래 커서로 돌려놓는 기능
				// 이거 안해도 원래 화살표모양으로 잘 돌아옴
			}

			@Override
			// 버튼을 클릭했을 때 창을 종료
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				backMain();
				
			}
		});
		add(backButton);
		
		// 메뉴바 생성
		menuBar.setBounds(0, 0, 1280, 30); // 메뉴바의 위치&크기 설정
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) // 마우스의 현 좌표
			{
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) // 마우스의 현 위치로 창을 이동 // 드래그 시 마우스 위치로 창도 이동
			{
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar); // 메뉴바 삽입

	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		// 게임창 하단 텍스트 픽셀저하 개선 -> Graphics
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g)
	{
		g.drawImage(introBackground, 0, 0, null); // intro화면에서 Background이미지
		if(isMainScreen) // start버튼이 눌렸을 때
		{
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if(isGameScreen)
		{			
			game.screenDraw(g);
		}
		paintComponents(g); // 고정된이미지(메뉴바,버튼 등)는 painComponents로 하는게 좋음
		try
		{
			Thread.sleep(5);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		this.repaint();	
	}
	public void selectedTrack(int nowSelected)
	{
		if(selectedMusic != null)
		{
			selectedMusic.close();
		}
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(),true);
		selectedMusic.start();	
	}
	public void selectLeft()
	{
		if(nowSelected == 0)
		{
			nowSelected = trackList.size() -1;
		}
		else
			nowSelected--;
		selectedTrack(nowSelected);
	}
	public void selectRight()
	{
		if(nowSelected == trackList.size() - 1 )
		{
			nowSelected = 0;
		}
		else
			nowSelected++;
		selectedTrack(nowSelected);
	}
	public void gameStart(int nowSelected, String difficulty)
	{
		// 음악이 실행중이라면~
		if(selectedMusic != null)
		{
			// 현재 재생중인 음악 종료
			selectedMusic.close();
		}
		// 메인스크린이 아님
		// screenDraw에서 g.~가 실행되지 않음
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		introBackground = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;		
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);	// 키보드 포커스를 맞추기 위함
	}
	public void backMain()
	{
		isMainScreen = true;
		isGameScreen = false;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introBackground = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		selectedTrack(nowSelected);
		game.close();		
	}
	public void enterMain()
	{
		startButton.setVisible(false);
		quitButton.setVisible(false);
		introBackground = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectedTrack(0);
		isMainScreen = true;
	}
}
