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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas TESTAR
 */
public class ControleSenha {
    BancoDeDados conex = new BancoDeDados();
    public void Criar(Senha se){
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conex.get();
            stmt = con.prepareStatement("INSERT INTO senhas (des) VALUES (?) ");
            stmt.setString(1, se.getDesc());
            stmt.execute();
            con.close();
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando CRIAR ERRO: \n "+ex);
        }
        
    }
    public void Apagar(Senha se){
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conex.get();
            stmt = con.prepareStatement("DELETE FROM senhas WHERE senha = ?");
            stmt.setInt(1, se.getSenha());
            stmt.execute();
            conex.close(con, stmt);
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando APAGAR ERRO: \n "+ex);
        }
        
    }
    public void Resetar() {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conex.get();
            stmt = con.prepareStatement("TRUNCATE TABLE senhas");
            stmt.execute();
            conex.close(con, stmt);
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando RESETAR ERRO: \n "+ex);
        }
        
    }
    public Senha buscar(Senha se) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Senha re = new Senha();
        try {
            con = conex.get();
            stmt = con.prepareStatement("SELECT * FROM senhas WHERE senha = ?");
            stmt.setInt(1, se.getSenha());
            rs = stmt.executeQuery();
            re.setSenha(rs.getInt("senha"));
            re.setDesc(rs.getString("des"));
            conex.close(con,stmt,rs);
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando BUSCAR(SE) ERRO: \n "+ex);
        }
        
        return re;
    }
    public Senha buscar(int se) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Senha re = new Senha();
        try {
            con = conex.get();
            stmt = con.prepareStatement("SELECT * FROM senhas WHERE senha = ?");
            stmt.setInt(1, se);
            rs = stmt.executeQuery();
            if(rs.next()){
                re.setSenha(rs.getInt("senha"));
                re.setDesc(rs.getString("des"));    
            }
            conex.close(con,stmt,rs);
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando BUSCAR(SE) ERRO: \n "+ex);
        }
        
        return re;
    }
    public ArrayList<Senha> buscar(String se){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Senha> senhasR = new ArrayList<Senha>();
        try {
            con = conex.get();
            stmt = con.prepareStatement("SELECT * FROM senhas WHERE des = ?");
            stmt.setString(1, se);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Senha re = new Senha();
                re.setSenha(rs.getInt("senha"));
                re.setDesc(rs.getString("des"));
                senhasR.add(re);
            }
        conex.close(con,stmt,rs);
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando BUSCAR ERRO: \n "+ex);
        }
        
        return senhasR;
    }
}
