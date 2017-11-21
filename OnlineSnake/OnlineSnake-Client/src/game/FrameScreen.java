package game;


import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ducba
 */
/*Chứa màn hình chơi game*/
public class FrameScreen extends JFrame {

    private GameScreen gameScreen;

    public FrameScreen() throws HeadlessException {
        gameScreen = new GameScreen();
        add(gameScreen);
        this.addKeyListener(new handler());
    }

//    public static void main(String[] args) {
//        FrameScreen frameScreen = new FrameScreen();
//        frameScreen.setVisible(true);
//        frameScreen.setSize(650, 500);
//        frameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }

    /*Bắt sự kiện phím*/
    private class handler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            /*Phi dung game va tiep tuc*/
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                GameScreen.isPause = !GameScreen.isPause;
                
                /*Xử lý chơi lại game*/
                if(GameScreen.isGameOver == true){
                    GameScreen.isGameOver = false;
                    gameScreen.getSnake().resetGame();
                }
            }

            /*Cac phim di chuyen*/
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                gameScreen.getSnake().setDerection(Snake.GO_UP);
                GameScreen.isPause = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                gameScreen.getSnake().setDerection(Snake.GO_DOWN);
                GameScreen.isPause = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                gameScreen.getSnake().setDerection(Snake.GO_LEFT);
                GameScreen.isPause = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gameScreen.getSnake().setDerection(Snake.GO_RIGHT);
                GameScreen.isPause = false;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

}
