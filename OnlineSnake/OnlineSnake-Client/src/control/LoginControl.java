/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Message;
import entity.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JOptionPane;
import view.Home;
import view.LoginFrm;

/**
 *
 * @author ducba
 */
public class LoginControl {

    private LoginFrm loginFrm;
    private String serverHost;
    private int serverPort = 1996;
    private Socket mySocket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public LoginControl(LoginFrm loginFrm) {
        this.loginFrm = loginFrm;

        try {
            mySocket = new Socket(serverHost, serverPort);
            oos = new ObjectOutputStream(mySocket.getOutputStream());
            ois = new ObjectInputStream(mySocket.getInputStream());

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /*Bắt sự kiện*/
        loginFrm.loginListener(new LoginFrmClickLogin());

    }

    class LoginFrmClickLogin implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                oos.writeObject(new Message(Message.LOGIN, loginFrm.getUser()));
                Object o = ois.readObject();
                if (o instanceof String) {
                    String mes = (String)o;
                    if(o.equals("Ok")){
                        new HomeControl(new Home(), loginFrm.getUser(), oos, ois);
                        loginFrm.setVisible(false);
                        
                    }else{
                        loginFrm.showMes("Sai tài khoản hoặc mật khẩu");
                    }
                } 
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {

                ex.printStackTrace();
            }
        }
    }

}
