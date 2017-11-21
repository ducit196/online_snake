/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.MatchDao;
import dao.UserDao;
import entity.Match;
import entity.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author ducba
 */
public class ServerThread implements Runnable {

    ServerControl serverControl;
    private Socket clientSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private static Match match = new Match();
    private static int dem = 0;

    public ServerThread(Socket clientSocket, ServerControl serverControl) {
        this.clientSocket = clientSocket;
        this.serverControl = serverControl;
    }

    public void sendData(Message message) {
        try {
            this.oos.writeObject(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Message readData() {
        try {
            return (Message) this.ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            while (true) {
                Object o = ois.readObject();
                if (o instanceof Message) {
                    switch (((Message) o).getAction()) {
                        case Message.LOGIN:
                            boolean check = new UserDao().login(((Message) o).getUser());
                            if (check == true) {
                                /*Them tai khoan va luong tuong ung vao map*/
                                this.serverControl.getListUserThread().put(((Message) o).getUser(), this);
                                oos.writeObject("Ok");
                                serverControl.notifySetOnlineUser();
                            } else {
                                oos.writeObject("Fail");
                            }
                            break;
                        case Message.LOGOUT:
                            this.serverControl.getListUserThread().remove(((Message) o).getUser());
                            System.out.println("username " + ((Message) o).getUser().getUsername());
                            System.out.println("siz=" + this.serverControl.getListUserThread().size());
                            serverControl.notifySetOnlineUser();
                            break;
                        case Message.REQUEST_CHALLENGE:
                            ((Message) o).setAction(Message.RESPOND_CHALLENGE);
                            this.serverControl.findUser2((Message) o);
                            break;

                        case Message.REQUEST_ACCEPT_CHALLENGE:
                            ((Message) o).setAction(Message.RESPOND_ACCEPT_CHALLENGE);
                            this.serverControl.findUser1((Message) o);
                            System.out.println("Co xac nhan duoc loi moi");
                            break;

//                        case Message.REQUEST_END_GAME_2:
                        case 1000:
                            System.out.println("Thằng 2 xong rồi");
                            dem++;
                            match.setUser2(((Message) o).getMatch().getUser2());
                            match.setPoint2(((Message) o).getMatch().getPoint2());
                            match.setPoint2(3);
                            System.out.println("THằng 2 gửi " + match.getPoint2() + " điểm");

                            if (dem % 2 == 0 && dem != 0) {
                                System.out.println("Hai thang no danh xong roi");
                                Match rs = new Match((int) new Date().getTime(), match.getUser1(), match.getUser2(),
                                        match.getPoint1(), match.getPoint2());
                                boolean addMatch = new MatchDao().addMatch(rs);
                                System.out.println("Them thanh cong");
                                ((Message) o).setMatch(match);
                                this.serverControl.respondResult((Message) o);
                            }
                            break;

                        case Message.REQUEST_END_GAME_1:
                            System.out.println("Thang 1 xong");
                            dem++;
                            match.setUser1(((Message) o).getMatch().getUser1());
                            match.setPoint1(((Message) o).getMatch().getPoint1());
                            System.out.println("THằng 1 gửi " + ((Message) o).getMatch().getPoint1() + " điểm");
                            if (dem % 2 == 0 && dem != 0) {
                                System.out.println("Hai thang no danh xong roi");
                                Match rs = new Match((int) new Date().getTime(), match.getUser1(), match.getUser2(),
                                        match.getPoint1(), match.getPoint2());
                                boolean addMatch = new MatchDao().addMatch(rs);
                                System.out.println("Them thanh cong");
                                ((Message) o).setMatch(match);
                                this.serverControl.respondResult((Message) o);
                            }
                            break;


                        /*Khi thang 1 thang*/
//                            Match rs = new Match(match1.getUser1(), match2.getUser2(), match1.getPoint1(), match2.getPoint2());
//                            this.serverControl.respondWinner((Message) o);
//                            Match save = new Match((int) new Date().getTime(), match1.getUser1(), match2.getUser2(), match1.getPoint1(), match2.getPoint2());
//                            boolean addMatch = new MatchDao().addMatch(save);
//                            if (addMatch == true) {
//                                System.out.println("Luu thanh cong");
//                            } else {
//                                System.out.println("Luu that bai");
//                            }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
