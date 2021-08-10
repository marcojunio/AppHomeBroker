package br.vianna.aula.jsf.controller.login;

import br.vianna.aula.jsf.entidades.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component("verificarLoginMb")
@RequestScope
public class VerificarLoginMb {

    @Autowired
    UsuarioDao user;

    @Autowired
    private LoginMb loginMB;

    public String verificarSenha() {
        loginMB.setUser(user.verificarUsuario(loginMB.getLogin(), loginMB.getSenha()));
        if (loginMB.getUser() != null) {
            loginMB.setLogado(true);
            return "/pages/internal_pages/index?faces-redirect=true";
        } else {
            loginMB.setMsg("Login ou senha incorreta.");
            loginMB.setLogin("");
            loginMB.setSenha("");
            return "";
        }
    }
}
