/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Match;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.OpenDB;

/**
 *
 * @author ducba
 */
public class MatchDao {

    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;

    public MatchDao() {
        con = OpenDB.getConnection();
    }
    
    public boolean addMatch(Match match){
        String sql = "INSERT INTO tbl_match(match_id, username1, username2, point1, point2) VALUES(?,?,?,?,?)";
        try {
            pr = con.prepareStatement(sql);
            pr.setInt(1, match.getMatchId());
            pr.setString(2, match.getUser1().getUsername());
            pr.setString(3, match.getUser2().getUsername());
            pr.setInt(4, match.getPoint1());
            pr.setInt(5, match.getPoint2());
            int check = pr.executeUpdate();
            if(check > 0){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        new MatchDao().addMatch(new Match(1, new User("a", "a"), new User("b", "b"), 12, 13));
    }

}
