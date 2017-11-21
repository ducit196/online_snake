/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import control.ServerControl;
import view.ServerView;

/**
 *
 * @author ducba
 */
public class ServerRun {

    public static void main(String[] args) {
        ServerControl serverControl = new ServerControl();
        new Thread(serverControl).start();
    }
}
