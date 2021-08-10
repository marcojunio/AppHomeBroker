/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.controller.login;

import br.vianna.aula.jsf.controller.external.HomeMB;
import br.vianna.aula.jsf.entidades.Acao;
import br.vianna.aula.jsf.entidades.Conta;
import br.vianna.aula.jsf.entidades.Usuario;
import br.vianna.aula.jsf.entidades.dao.CarteiraDao;
import br.vianna.aula.jsf.entidades.dao.ContaDao;
import br.vianna.aula.jsf.entidades.dao.UsuarioDao;
import br.vianna.aula.jsf.helper.HelperLogin;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marco
 */
@Component("criarLoginMb")
@RequestScoped
public class CriarLoginMb implements Serializable {

    private Usuario user;
    @Autowired
    UsuarioDao userDao;
    @Autowired
    LoginMb login;
    @Autowired
    ContaDao contaDAO;
    @Autowired
    CarteiraDao carteiraDAO;
    @Autowired
    HomeMB homeMb;
    private boolean erro;
    private String msg;

    public CriarLoginMb() {
        user = new Usuario();
        erro = false;
        msg = "";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public String criarUsuario() {

        homeMb.setLoginIndex(null);
            
        if (!user.getCpf().equals("") && !user.getEndereco().equals("") && !user.getLogin().equals("") && !user.getSenha().equals("") && !user.getSobrenome().equals("") && !user.getNome().equals("")) {
            user.setSenha(HelperLogin.Md5(user.getSenha()));
            user.setAdmin(false);
            userDao.salvar(user);

            Conta conta = new Conta();
            conta.setUsuarioid(user);
            conta.setSaldo(0.0);
            contaDAO.salvar(conta);

            user = new Usuario();
            
            erro = false;
            msg = "";
            
            return viewLogin();
        } 
        else {
            erro = true;
            
            user.setCpf("");
            user.setEndereco("");
            user.setLogin("");
            user.setNome("");
            user.setProfissao("");
            user.setSenha("");
            user.setSobrenome("");
            
            msg = "Todos os campos devem estar preenchidos, favor verifique e tente novamente.";
        }

        return "";
    }
    
    
    
    public String viewLogin() {
        return "login?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return user;
    }

    public void setUsuario(Usuario user) {
        this.user = user;
    }
}
