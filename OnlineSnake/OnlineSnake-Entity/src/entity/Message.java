/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ducba
 */
public class Message implements Serializable {

    public static final int LOGIN = 1;
    public static final int LOGOUT = 2;
    public static final int REQUEST_CHALLENGE = 3;
    public static final int RESPOND_CHALLENGE = 4;
    public static final int REQUEST_ACCEPT_CHALLENGE = 5;
    public static final int RESPOND_ACCEPT_CHALLENGE = 6;
    public static final int REPOND_ONLINE_USER = 7;
    public static final int REQUEST_END_GAME_1 = 8;
    public static final int REQUEST_END_GAME_2 = 9;
    public static final int RESPOND_WINNER = 10;
    public static final int RESPOND_LOSER = 11;
    
    private int action;
    private User user;
    private List<User> listOnlineUser;
    private Match match;
    
    
    public Message() {
    }

    public Message(int action, User user, Match match) {
        this.action = action;
        this.user = user;
        this.match = match;
    }

    public Message(int action) {
        this.action = action;
    }

    public Message(int action, List<User> listOnlineUser) {
        this.action = action;
        this.listOnlineUser = listOnlineUser;
    }

    
    public Message(int action, User user) {
        this.action = action;
        this.user = user;
    }

    public Message(int action, Match match) {
        this.action = action;
        this.match = match;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<User> getListOnlineUser() {
        return listOnlineUser;
    }

    public void setListOnlineUser(List<User> listOnlineUser) {
        this.listOnlineUser = listOnlineUser;
    }
    
}
