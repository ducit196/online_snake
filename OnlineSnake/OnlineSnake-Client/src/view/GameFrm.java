package view;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
public class GameFrm extends JFrame {

    private GameScreen gameScreen;
    private boolean gameOver = false;

    public GameFrm() throws HeadlessException {
        this.gameScreen = new GameScreen();
        add(gameScreen);
        this.addKeyListener(new handler());
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    /*Bắt sự kiện phím*/
    private class handler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            /*Phi dung game va tiep tuc*/
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//                GameScreen.isPause = !GameScreen.isPause;
                gameScreen.getSnake().setIsPause(!gameScreen.getSnake().isIsPause());

                /*Xử lý chơi lại game*/
//                if (GameScreen.isGameOver == true) {
//                    GameScreen.isGameOver = false;
//                    gameScreen.getSnake().resetGame();
//                }
            }

            /*Cac phim di chuyen*/
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                gameScreen.getSnake().setDerection(Snake.GO_UP);
                gameScreen.getSnake().setIsPause(false);
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                gameScreen.getSnake().setDerection(Snake.GO_DOWN);
                gameScreen.getSnake().setIsPause(false);
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                gameScreen.getSnake().setDerection(Snake.GO_LEFT);
                gameScreen.getSnake().setIsPause(false);

            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gameScreen.getSnake().setDerection(Snake.GO_RIGHT);
                gameScreen.getSnake().setIsPause(false);

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

}
