package game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ducba
 */
public class Snake {

    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;

    private int derection = Snake.GO_RIGHT;

    private int length = 3;
    private int x[];
    private int y[];
    private int timeDelay;
    private int point;

    public Snake() {
        timeDelay = 250;
        x = new int[20];
        y = new int[20];

        /*Vi tri dau tien cua con ran*/
        x[0] = 5;
        y[0] = 4;
        x[1] = 5;
        y[1] = 3;
        x[2] = 5;
        y[2] = 2;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setTimeDelay(int timeDelay) {
        this.timeDelay = timeDelay;
    }

    public int getTimeDelay() {
        return timeDelay;
    }

    public void setDerection(int derection) {
        if (this.derection != -derection) {
            this.derection = derection;
        }
    }

    public Point toaDoMoi() {
        Random random = new Random();
        int x;
        int y;
            x = random.nextInt(19);
            y = random.nextInt(19);
        return new Point(x, y);
    }

    public void update() {

        /*Khi cắn đc mồi*/
        if (GameScreen.backGround[x[0]][y[0]] == 2) {
            length++;
            GameScreen.backGround[x[0]][y[0]] = 0;
            GameScreen.backGround[toaDoMoi().x][toaDoMoi().y] = 2;
            increase();
            speedUp();
        }

        /*Cho cả con rắn gắn vào nhau*/
        for (int i = length - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        /*Xử lý đổi hướng rắn*/
        if (derection == Snake.GO_UP) {
            y[0]--;
        }
        if (derection == Snake.GO_DOWN) {
            y[0]++;
        }
        if (derection == Snake.GO_LEFT) {
            x[0]--;
        }
        if (derection == Snake.GO_RIGHT) {
            x[0]++;
        }

        /*Xử lý khi chạm tường*/
        if (x[0] < 0) {
            x[0] = 19;
        }
        if (x[0] > 19) {
            x[0] = 0;
        }

        if (y[0] < 0) {
            y[0] = 19;
        }
        if (y[0] > 19) {
            y[0] = 0;
        }

        /*Khi đầu rắn cắn vào thân*/
        for (int i = 1; i < length; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {
                GameScreen.isGameOver = true;
                GameScreen.isPause = true;
            }
        }
    }

    public void speedUp(){
        setTimeDelay(timeDelay - 10);
    }
    
    /*Chơi lại*/
    public void resetGame() {
        x = new int[20];
        y = new int[20];

        /*Vi tri dau tien cua con ran*/
        x[0] = 5;
        y[0] = 4;
        x[1] = 5;
        y[1] = 3;
        x[2] = 5;
        y[2] = 2;
        length = 3;
        point = 0;
        timeDelay = 250;
    }
    
    /*Tăng điểm*/
    public void increase(){
        point++;
    }

    public void paintSnake(Graphics g) {
        g.setColor(Color.red);
        for (int i = 0; i < length; i++) {
//            g.fillRect(x[i] * 20 + 1, y[i] * 20 + 1, 18, 18);
            g.drawImage(Data.imageBody, x[i] * 20, y[i] * 20, null);
            g.drawImage(Data.imageHead, x[0] * 20 - 6, y[0] * 20 - 6, null);
        }

    }
}
