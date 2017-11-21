/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Match;
import entity.Message;
import entity.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import javax.swing.JOptionPane;
import view.*;

/**
 *
 * @author ducba
 */
public final class HomeControl {

    private Home homeFrm;

    private String serverHost;
    private int serverPort = 1996;
    private User currentUser;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public HomeControl() {
    }

    public HomeControl(Home homeFrm, User currentUser, ObjectOutputStream oos, ObjectInputStream ois) {
        this.homeFrm = homeFrm;
        this.currentUser = currentUser;
        this.oos = oos;
        this.ois = ois;
        this.homeFrm.setTitle("Xin chao " + this.currentUser.getUsername());


        /*Bat su kien*/
        setListener();
        listening();
    }

    public void setListener() {
        homeFrm.logoutListener(new HomeClickLogout());
        homeFrm.challengeListener(new HomeClickChallenge());
    }

    class HomeClickLogout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                oos.writeObject(new Message(Message.LOGOUT, homeFrm.getCurrentUser()));
                homeFrm.hide();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    class HomeClickChallenge implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            User user1 = homeFrm.getCurrentUser();
            User user2 = homeFrm.getListUserOnline().get(homeFrm.getJtbOnlineClient().getSelectedRow());
            System.out.println("user 1 = " + user1.getUsername());
            System.out.println("user 2 = " + user2.getUsername());
            try {
                oos.writeObject(new Message(Message.REQUEST_CHALLENGE, new Match(user1, user2)));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void listening() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Object o = ois.readObject();
                        if (o instanceof Message) {
                            switch (((Message) o).getAction()) {
                                case Message.RESPOND_CHALLENGE:
                                    int showConfirmDialog = homeFrm.showConfirmDialog(((Message) o).getMatch().getUser1());
                                    if (showConfirmDialog == 0) {
                                        Match match = ((Message) o).getMatch();
                                        match.setIndex(2);
                                        oos.writeObject(new Message(Message.REQUEST_ACCEPT_CHALLENGE, match));
                                        new GameControl(new GameFrm(), ois, oos, match);
                                    }
                                    break;

                                case Message.REPOND_ONLINE_USER:
                                    homeFrm.setCurrentUser(currentUser);
                                    Vector<User> list = (Vector<User>) ((Message) o).getListOnlineUser();
                                    list.remove(currentUser);
                                    homeFrm.setListUserOnline(list);
                                    homeFrm.showOnlineUser(list);
                                    break;
                                case Message.RESPOND_ACCEPT_CHALLENGE:
                                    Match match = ((Message) o).getMatch();
                                    match.setIndex(1);
                                    new GameControl(new GameFrm(), ois, oos, match);
                                    break;
                                case Message.RESPOND_WINNER:
                                    Match match1 = ((Message) o).getMatch();
                                    JOptionPane.showMessageDialog(null, "Bạn đã dành chiến thắng\nĐối thủ nó chỉ được " + Math.min(match1.getPoint1(), match1.getPoint2()) + " điểm!");
                                    break;
                                case Message.RESPOND_LOSER:
                                    Match match2 = ((Message) o).getMatch();
                                    JOptionPane.showMessageDialog(null, "Bạn đã thua cuộc\nĐối thủ nó được tận " + Math.max(match2.getPoint1(), match2.getPoint2()) + " điểm!");
                                    break;
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
