package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BancoDeDados {
    final static String DRIVER ="com.mysql.jdbc.Driver";
    final static String URL = "jdbc:mysql://localhost:3306/SiS";
    final static String USER = "root";
    final static String PASS = "";
    public Connection get() throws SQLException{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = null;
        con = DriverManager.getConnection(URL,USER,PASS);
        return con;
    }
    public void close(Connection con){
        try {
            if(con!= null){
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void close(Connection con,PreparedStatement stmt){
        close(con);
        try {
            if(stmt!= null){
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void close(Connection con,PreparedStatement stmt,ResultSet rs){
        close(con,stmt);
        try {
            if(rs!= null){
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
