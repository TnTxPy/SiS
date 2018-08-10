/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Conexao.BancoDeDados;
import Objetos.Senha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lucas TESTAR
 */
public class ControleSenha {
    BancoDeDados conex = new BancoDeDados();
    public void Criar(Senha se) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        con = conex.get();
        stmt = con.prepareStatement("INSERT INTO senhas (des) VALUES (?) ");
        stmt.setString(1, se.getDesc());
        stmt.execute();
        con.close();
    }
    public void Apagar(Senha se) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        con = conex.get();
        stmt = con.prepareStatement("DELETE * FROM senhas WHERE senha = ?");
        stmt.setInt(1, se.getSenha());
        stmt.execute();
        conex.close(con, stmt);
    }
    public void Resetar() throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        con = conex.get();
        stmt = con.prepareStatement("TRUNCATE TABLE senhas");
        stmt.execute();
        conex.close(con, stmt);
    }
    public Senha buscar(Senha se) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        con = conex.get();
        stmt = con.prepareStatement("SELECT * FROM senhas WHERE senha = ?");
        stmt.setInt(1, se.getSenha());
        rs = stmt.executeQuery();
        Senha re = new Senha();
        re.setSenha(rs.getInt("senha"));
        re.setDesc(rs.getString("des"));
        conex.close(con,stmt,rs);
        return re;
    }
    public ArrayList<Senha> buscar(String se) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        con = conex.get();
        stmt = con.prepareStatement("SELECT * FROM senhas WHERE des = ?");
        stmt.setString(1, se);
        rs = stmt.executeQuery();
        Senha re = new Senha();
        ArrayList<Senha> senhasR = new ArrayList<Senha>();
        while(rs.next()){
            re.setSenha(rs.getInt("senha"));
            re.setDesc(rs.getString("des"));
            senhasR.add(re);
        }
        conex.close(con,stmt,rs);
        return senhasR;
    }
}
