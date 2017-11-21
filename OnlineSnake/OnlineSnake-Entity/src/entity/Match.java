/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author ducba
 */
public class Match implements Serializable{
    private int matchId;
    private User user1;
    private User user2;
    private int point1;
    private int point2;
    private int temp;
    private int index;

    public Match() {
    }

    public Match(int matchId, User user1, User user2, int point1, int point2) {
        this.matchId = matchId;
        this.user1 = user1;
        this.user2 = user2;
        this.point1 = point1;
        this.point2 = point2;
    }

    public Match(User user1, User user2, int point1, int point2) {
        this.user1 = user1;
        this.user2 = user2;
        this.point1 = point1;
        this.point2 = point2;
    }

    public Match(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
    
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public int getPoint1() {
        return point1;
    }

    public void setPoint1(int point1) {
        this.point1 = point1;
    }

    public int getPoint2() {
        return point2;
    }

    public void setPoint2(int point2) {
        this.point2 = point2;
    }

    
}
