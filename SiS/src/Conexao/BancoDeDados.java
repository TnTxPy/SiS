package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BancoDeDados {
    
    // Declarações CONSTANTES
    // Driver de JDBC 
    // URL DE CONEXAO DB
    // USUARIO
    // SENHA
    
    final static String DRIVER ="com.mysql.jdbc.Driver";
    final static String URL = "jdbc:mysql://localhost:3306/SiS";
    final static String USER = "root";
    final static String PASS = "";
    
    
    //Comando para conexão com o banco de dados
    public Connection get() throws SQLException{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println("[ERRO] Comando conex.GET ERRO: \n "+ex);
        }
        Connection con = null;
        con = DriverManager.getConnection(URL,USER,PASS);
        return con;
    }
    //Comando para fechar a conexão
    public void close(Connection con){
        try {
            if(con!= null){
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando conex.CLOSE ERRO: \n "+ex);
        }
    }
    //Comando para fechar a conexão e stmt
    public void close(Connection con,PreparedStatement stmt){
        close(con);
        try {
            if(stmt!= null){
                stmt.close();
            }
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando conex.CLOSE STMT ERRO: \n "+ex);
        }
    }
    //Comando para fechar a conexão e stmt e Rs
    public void close(Connection con,PreparedStatement stmt,ResultSet rs){
        close(con,stmt);
        try {
            if(rs!= null){
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando conex.CLOSE RS ERRO: \n "+ex);
        }
    }
}
