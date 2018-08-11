/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Controle.ControleSenha;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *Objeto da Senha, em breve 100% utilizavel
 * 
 */
public class Senha {
    private int senha;
    private String desc;

    /**
     * @return the senha
     */
    public int getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(int senha) {
        this.senha = senha;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void criar(String desc){
        this.setDesc(desc);
        ControleSenha co = new ControleSenha();
        co.Criar(this);
        
    }
}
