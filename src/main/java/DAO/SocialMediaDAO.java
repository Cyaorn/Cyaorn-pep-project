package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.*;
import Util.ConnectionUtil;
import java.util.List;
import java.util.ArrayList;

public class SocialMediaDAO {
    
    Connection conn;
    public SocialMediaDAO() throws SQLException {
        conn = ConnectionUtil.getConnection();
    }

    public List<Account> getAllUsers() {
        List<Account> users = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from account;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                users.add(new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                ));
            }
            return users;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // returns username of newly-created account after querying to add to database
    public String registerUser(Account newUser) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into account (username, password) values (?, ?);");
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

    public Account getAccountByUserPass(Account user) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from account where username = ? and password = ?;");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
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

    public int sendMessage(Message newMsg) {
        try {
            String sql = "insert into message (posted_by, message_text, time_posted_epoch) values (?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, newMsg.getPosted_by());
            ps.setString(2, newMsg.getMessage_text());
            ps.setLong(3, newMsg.getTime_posted_epoch());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt("message_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Message getMessageByID(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from message where message_id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return (new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                ));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Message> getAllMsgs() {
        ArrayList<Message> msgs = new ArrayList<>();
        try {
            String sql = "select * from message;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                msgs.add(new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                ));
            }
            return msgs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msgs;
    }

}
