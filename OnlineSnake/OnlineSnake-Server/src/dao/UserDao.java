/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.OpenDB;

/**
 *
 * @author ducba
 */
public class UserDao {

    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;

    public UserDao() {
        con = OpenDB.getConnection();
    }

    public boolean login(User user) {
        String sql = "SELECT * FROM tbl_user WHERE username = ? AND password = ?";
        try {
            pr = con.prepareStatement(sql);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            rs = pr.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

//    public void online(User user) {
//        String sql = "UPDATE tbl_user SET online = ? WHERE username = ?";
//        try {
//            pr = con.prepareStatement(sql);
//            pr.setInt(1, 1);
//            pr.setString(2, user.getUsername());
//            pr.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

//    public void offline(User user) {
//        String sql = "UPDATE tbl_user SET online = ? WHERE username = ?";
//        try {
//            pr = con.prepareStatement(sql);
//            pr.setInt(1, 0);
//            pr.setString(2, user.getUsername());
//            pr.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

//    public Vector<User> getListOnlineUser(User user) {
//        Vector<User> list = new Vector<>();
//        String sql = "SELECT * FROM tbl_user WHERE online = ? AND username <> ?";
//        try {
//            pr = con.prepareStatement(sql);
//            pr.setInt(1, 1);
//            pr.setString(2, user.getUsername());
//            rs = pr.executeQuery();
//            while (rs.next()) {
//                    list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }

}
