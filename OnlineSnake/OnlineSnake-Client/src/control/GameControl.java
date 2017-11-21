/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Match;
import entity.Message;
import entity.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.GameFrm;
import view.GameScreen;

/**
 *
 * @author ducba
 */
public class GameControl {

    private GameFrm gameFrm;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Match match;
    private static int dem = 0;

    public GameControl(GameFrm gameFrm, ObjectInputStream ois, ObjectOutputStream oos, Match match) {
        this.gameFrm = gameFrm;
        gameFrm.setVisible(true);
        gameFrm.setSize(650, 500);
        gameFrm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        if (match.getIndex() == 1) {
            gameFrm.setTitle("Màn hình của " + match.getUser1().getUsername());
        } else {
            gameFrm.setTitle("Màn hình của " + match.getUser2().getUsername());
        }
        this.ois = ois;
        this.oos = oos;
        this.match = match;
        listeningGameOver();
    }

    public void listeningGameOver() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Thread kiem tra gameover co dc chay");
                    if (gameFrm.getGameScreen().getSnake().isIsGameOver()) {
                        gameFrm.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Điểm của bạn là: " + gameFrm.getGameScreen().getSnake().getPoint()
                                + "\nĐợi đối thủ đánh xong game!");
                        try {
                            if (match.getIndex() == 2) {
                                match.setPoint2(gameFrm.getGameScreen().getSnake().getPoint());
//                                oos.writeObject(new Message(Message.REQUEST_END_GAME_2, match));
                                oos.writeObject(new Message(1000, match));
                                System.out.println("Thằng 2 gửi " + match.getPoint2() + " điểm");
                                break;
                            } else {
                                match.setPoint1(gameFrm.getGameScreen().getSnake().getPoint());
                                oos.writeObject(new Message(Message.REQUEST_END_GAME_1, match));
                                System.out.println("Thằng 1 gửi " + match.getPoint1() + " điểm");
                                break;
                            }

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            }
        }).start();
    }

}
