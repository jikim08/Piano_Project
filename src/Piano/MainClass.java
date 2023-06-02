package Piano;

import javax.swing.JFrame;

public class MainClass extends JFrame {

    public MainClass() {
        setTitle("Play Piano");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,800);
        setVisible(true);

        // Piano 스레드 시작
        Piano piano = new Piano(this);
        piano.start();
    }

    public static void main(String[] args) {
        new MainClass();
    }
}
