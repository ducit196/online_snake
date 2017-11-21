/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author ducba
 */
public class User implements Serializable {

    private String username;
    private String password;
    private String address;
    private String phone;
    private boolean isOnline;
    private boolean isInGame;

    public User() {
    }

    public User(String username, String password, String address, String phone, boolean isOnline, boolean isInGame) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.isOnline = isOnline;
        this.isInGame = isInGame;
    }

    public User(String username, String password, String address, String phone) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.isOnline = false;
        this.isInGame = false;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public boolean isIsInGame() {
        return isInGame;
    }

    public void setIsInGame(boolean isInGame) {
        this.isInGame = isInGame;
    }

    public Vector<String> getObject() {
        Vector<String> list = new Vector<>();
        list.add(username);
        return list;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return username.equals(((User) obj).username);
        }
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        return result;
    }

}
