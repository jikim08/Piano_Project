package Fullproject.Melody;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {
    //오디오 클립 사용을 위해 선언하였습니다.
    private Clip clip;
    private File audioFile;
    private AudioInputStream audioInputStream;
    private boolean isLoop;
    //Audio 클래스 생성자로 경로명과 반복 여부를 매개변수로 받습니다.
    public Audio(String pathName, boolean isLoop) {
        try {
            //오디오 재생을 위한 클립 객체를 가져옵니다.
            clip = AudioSystem.getClip();
            //지정된 경로에서 File 객체 생성
            audioFile = new File(pathName);
            //위에 선언된 audioFile을 기반으로 AudioInputStream 객체를 생성합니다.
            audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            //클립을 열고 오디오 데이터를 읽어옵니다.
            clip.open(audioInputStream);
        } //오디오 장치가 사용 불가능일때의 예외처리
        catch (LineUnavailableException e) {
            e.printStackTrace();
        } //지원되지 않는 오디오 파일일때의 예외처리
        catch (IOException e) {
            e.printStackTrace();
        } //오디오 데이터를 읽어올때 입출력 오류에 대한 예외처리
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    //클립을 시작하여 오디오 재생
    public void start() {
        clip.setFramePosition(0);
        clip.start();
        //true인 경우 반복 재생
        if (isLoop) clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    //클립 정지시켜 오디오 정지
    public void stop() {
        clip.stop();
    }
}