package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.*;
import Util.ConnectionUtil;

public class SocialMediaDAO {
    
    Connection conn;
    public SocialMediaDAO() throws SQLException {
        conn = ConnectionUtil.getConnection();
    }

    // returns username of newly-created account after querying to add to database
    public String registerUser(Account newUser) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into account (username, password) values (?, ?)");
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getPassword());
            ps.executeUpdate();
            return newUser.getUsername();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    } 

    public Account getAccountByUser(String username) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from account where username = ?;");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return (new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                ));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountByID(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from account where id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return (new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                ));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
