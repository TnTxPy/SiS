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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class ControleSenha {
    BancoDeDados conex = new BancoDeDados();
    public void Criar(Senha se) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        con = conex.get();
        stmt = con.prepareStatement("INSERT INTO senhas (senha,desc) VALUES (?,?) ");
        stmt.setInt(1, se.getSenha());
        stmt.setString(2, se.getDesc());
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
        stmt = con.prepareStatement("DELETE * FROM senhas");
        stmt.execute();
        conex.close(con, stmt);
    }
}
