/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Message;
import entity.User;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author ducba
 */
public class ServerControl implements Runnable {

    private Map<User, ServerThread> listUserThread = new HashMap<>();
    private ServerSocket myServer;
    private int serverPort = 1996;

    public Map<User, ServerThread> getListUserThread() {
        return listUserThread;
    }

    @Override
    public void run() {
        try {
            myServer = new ServerSocket(serverPort);
            System.out.println("Listening on port 1996....");
            while (true) {
                Socket socket = myServer.accept();
                System.out.println("Accepted a client....");
                new Thread(new ServerThread(socket, this)).start();
//                for (Map.Entry<User, ServerThread> entry : listUserThread.entrySet()) {
//                    System.out.println(entry.getKey().getUsername());
//                }
                System.out.println(listUserThread.size());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*Tim nguoi duoc thach dau*/
    public void findUser2(Message message) {
        ServerThread serverThread = this.listUserThread.get(message.getMatch().getUser2());
        if (serverThread != null) {
            serverThread.sendData(message);
        }
    }

    /*Gui tra ve thang thach dau*/
    public void findUser1(Message message) {
        ServerThread serverThread = this.listUserThread.get(message.getMatch().getUser1());
        if (serverThread != null) {
            serverThread.sendData(message);
        }
    }

//    public void respondEndGame1(Message message) {
//
//        if (message.getMatch().getIndex() == 1) {
//            ServerThread serverThreadWinner = this.listUserThread.get(message.getMatch().getUser2());
//            if (serverThreadWinner != null) {
//                message.setAction(Message.RESPOND_END_GAME_1);
//                serverThreadWinner.sendData(message);
//            }
//        } else {
//            ServerThread serverThreadWinner = this.listUserThread.get(message.getMatch().getUser1());
//            if (serverThreadWinner != null) {
//                message.setAction(Message.RESPOND_END_GAME_1);
//                serverThreadWinner.sendData(message);
//            }
//        }
//
//    }

    /*Gửi về kết quả của game*/
    public void respondResult(Message message) {
        /*nếu thằng 1 mà thắng*/
        if (message.getMatch().getPoint1() > message.getMatch().getPoint2()) {
            ServerThread serverThreadWinner = this.listUserThread.get(message.getMatch().getUser1());
            if (serverThreadWinner != null) {
                message.setAction(Message.RESPOND_WINNER);
                serverThreadWinner.sendData(message);
            }
            ServerThread serverThreadLoser = this.listUserThread.get(message.getMatch().getUser2());
            if (serverThreadLoser != null) {
                message.setAction(Message.RESPOND_LOSER);
                serverThreadLoser.sendData(message);
            }
        } else {
            ServerThread serverThreadWinner = this.listUserThread.get(message.getMatch().getUser2());
            if (serverThreadWinner != null) {
                message.setAction(Message.RESPOND_WINNER);
                serverThreadWinner.sendData(message);
            }
            ServerThread serverThreadLoser = this.listUserThread.get(message.getMatch().getUser1());
            if (serverThreadLoser != null) {
                message.setAction(Message.RESPOND_LOSER);
                serverThreadLoser.sendData(message);
            }
        }
    }

    public void notifySetOnlineUser() {

        Vector<User> listOnlineUser = new Vector<>();

        for (Map.Entry<User, ServerThread> entry : listUserThread.entrySet()) {
            listOnlineUser.add(entry.getKey());
        }

        for (Map.Entry<User, ServerThread> entry : listUserThread.entrySet()) {
            entry.getValue().sendData(new Message(Message.REPOND_ONLINE_USER, listOnlineUser));
        }
    }

}
