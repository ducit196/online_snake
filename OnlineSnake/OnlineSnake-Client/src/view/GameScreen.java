package view;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ducba
 */
public class GameScreen extends JPanel implements Runnable{

    static int[][] backGround = new int[20][20];
    static int WITH_SCREEN = 400;
    static int HEIGHT_SCREEN = 400;
    private Thread thread;
    private Snake snake;

    public GameScreen() {
        snake = new Snake();

        /*Load hinh anh con ran*/
        Data.loadImage();
        thread = new Thread(this);
        thread.start();
        backGround[10][10] = 2;

    }

    public Snake getSnake() {
        return snake;
    }

    @Override
    public void paint(Graphics g) {
        paintBackGround(g);
        drawFrame(g);
        snake.paintSnake(g);

        if (snake.isIsPause()) {
            g.setColor(Color.white);
            g.setFont(g.getFont().deriveFont(18.0f));
            g.drawString("PRESS SPACE TO PLAY GAME", 90, 192);
        }

        if (snake.isIsGameOver()) {
            g.setColor(Color.red);
            g.setFont(g.getFont().deriveFont(30.0f));
            g.drawString("GAME OVER!", 120, 150);
        }

        /*Ghi điểm*/
        g.setColor(Color.red);
        g.setFont(g.getFont().deriveFont(30.0f));
        g.drawString("POINT: " + snake.getPoint(), 435, 190);

    }

    public void run() {
        while (true) {
            if (!snake.isIsPause()) {
                snake.update();
            }
            repaint();
            try {
                thread.sleep(snake.getTimeDelay());
            } catch (InterruptedException ex) {
                Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void drawFrame(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.cyan);
        Stroke stroke = new BasicStroke(5);
        g2.setStroke(stroke);
        g2.drawRect(0, 0, WITH_SCREEN + 1, HEIGHT_SCREEN + 1);
        g2.drawRect(400, 0, 200, HEIGHT_SCREEN + 1);

        /*Ve khung ghi điểm*/
//        g.setColor(Color.black);
//        g2.drawRect(420, 0, 220, HEIGHT_SCREEN + 1);

    }

    /*Ve luoi choi*/
    public void paintBackGround(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(2, 2, WITH_SCREEN + 200, HEIGHT_SCREEN);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
//                g.fillRect(i * 20 + 1, j * 20 + 1, 18, 18);
                if (backGround[i][j] == 2) {
//                    g.setColor(Color.yellow);
//                    g.fillRect(i * 20 + 1, j * 20 + 1, 18, 18);
//                    g.setColor(Color.gray);
                    g.drawImage(Data.imageWorm, i * 20, j * 20, null);
                }
            }
        }
    }

}
