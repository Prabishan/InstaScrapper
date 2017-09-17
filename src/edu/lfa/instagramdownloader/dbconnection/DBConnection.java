
package edu.lfa.instagramdownloader.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnection {
    private Connection conn  = null;
    private PreparedStatement stmt =null;
    public void openConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/db_business", "root", "");
    }
    
    public PreparedStatement init(String sql) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt;
    }
    
    public int executeUpdate() throws SQLException{
        return stmt.executeUpdate();
    }
    
    public ResultSet executeQuery() throws SQLException{
        return stmt.executeQuery();
    }
    
    public void closeConnection() throws SQLException{
        if(!conn.isClosed() && conn != null){
            conn.close();
            conn = null;
        }
    }
}
