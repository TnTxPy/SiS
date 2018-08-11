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
 * Controle de Senha, todas as operações seram feitas atraves dessa classe
 * Criar é feito pelo Criar(Senha)
 * Apagar é feito pelo Apagar(Senha)
 * Resetar é feito pelo Resetar()
 * Buscar é feito pelo Buscar(Senha)
 * Buscar é feito pelo Buscar(int)
 * Buscar é feito pelo Buscar(String)
 * Chamar é feito pelo Chamar(Senha)
 * Chamar é feito pelo Chamar(int)
 * Atual é feito pelo Atual()
 * BuscaGeral é feito pelo BuscaGeral()
 */
public class ControleSenha {
    BancoDeDados conex = new BancoDeDados();
    // Comando Criar, recebe Senha            
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
    // Comando Apagar, recebe Senha
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
    // Comando Resetar, não recebe atributo
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
    // Comando Buscar, recebe Senha
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
    // Comando Buscar, recebe int
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
    // Comando Buscar, recebe String
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
    // Comando Chamar, recebe Senha
    public void chamar(Senha se){
        Connection con = null;
        PreparedStatement stmt = null;
        Senha re = new Senha();
        
        try {
            
            con = conex.get();
            re = this.buscar(se);
            this.Apagar(re);
            stmt = con.prepareStatement("INSERT INTO chamada (senha,des) VALUES (?,?)");
            stmt.setInt(1, re.getSenha());
            stmt.setString(2,re.getDesc());
            stmt.execute();
            conex.close(con, stmt);
            
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando CHAMAR(SE) ERRO: \n "+ex);
        }
    }
    // Comando Chamar, recebe int
    public void chamar(int se){
        Connection con = null;
        PreparedStatement stmt = null;
        Senha re = new Senha();
        
        try {
            con = conex.get();
            re = this.buscar(se);
            this.Apagar(re);
            
            stmt = con.prepareStatement("TRUNCATE TABLE chamada");
            stmt.execute();
            
            stmt = con.prepareStatement("INSERT INTO chamada (senha,des) VALUES (?,?)");
            stmt.setInt(1, re.getSenha());
            stmt.setString(2,re.getDesc());
            stmt.execute();
            conex.close(con, stmt);
            
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando CHAMAR(INT) ERRO: \n "+ex);
        }
    }
    // Comando Atual, não recebe atributo
    public Senha atual(){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Senha re = new Senha();
        
        try {
            con = conex.get();
            stmt = con.prepareStatement("SELECT * FROM chamada");
            rs = stmt.executeQuery();
            if(rs.next()){
                re.setSenha(rs.getInt("senha"));
                re.setDesc(rs.getString("des"));
            }
            conex.close(con, stmt, rs);
                    
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando ATUAL ERRO: \n "+ex);
        }
        return re;
                
    }
    // Comando BuscaGeral, não recebe atributo
    public ArrayList<Senha> buscaGeral(){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Senha> resultado = new ArrayList<Senha>();
        
        try {
            con = conex.get();
            stmt = con.prepareStatement("SELECT * FROM senhas");
            rs = stmt.executeQuery();
            while(rs.next()){
                Senha re = new Senha();
                re.setSenha(rs.getInt("senha"));
                re.setDesc(rs.getString("des"));
                resultado.add(re);
            }
            conex.close(con, stmt, rs);
        } catch (SQLException ex) {
            System.out.println("[ERRO] Comando BUSCAGERAL ERRO: \n "+ex);
        }
        return resultado;
    }
}
