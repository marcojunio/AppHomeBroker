package br.vianna.aula.jsf.controller.login;

import br.vianna.aula.jsf.entidades.dtos.UsuarioDto;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import org.springframework.stereotype.Component;

@Component("loginMb")
@SessionScoped
public class LoginMb implements Serializable {

    private String login, senha, msg;
    private boolean logado;
    private UsuarioDto user;

    public LoginMb() {
        logado = false;
        msg = "";
        user = null;
    }

    public String viewCadastro() {
        return "criar-conta?faces-redirect=true";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public UsuarioDto getUser() {
        return user;
    }

    public void setUser(UsuarioDto user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String viewHomeExterna() {
        return "/pages/external_pages/home?faces-redirect=true";
    }
}
