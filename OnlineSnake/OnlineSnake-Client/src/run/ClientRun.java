/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import control.HomeControl;
import control.LoginControl;
import view.LoginFrm;

/**
 *
 * @author ducba
 */
public class ClientRun {
    public static void main(String[] args) {
        LoginFrm loginFrm = new LoginFrm();
        new LoginControl(loginFrm);
        loginFrm.setVisible(true);
    }
}
