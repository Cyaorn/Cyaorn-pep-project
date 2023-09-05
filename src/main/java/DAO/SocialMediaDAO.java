package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import Model.*;
import Util.ConnectionUtil;

public class SocialMediaDAO {
    
    Connection conn;
    public SocialMediaDAO() throws SQLException {
        // conn = DriverManager.getConnection(null); // placeholder value
        conn = ConnectionUtil.getConnection();
    }

    public Account registerUserAccount() {
        return null;
    } 

}
