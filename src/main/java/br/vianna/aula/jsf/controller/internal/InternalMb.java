/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.controller.internal;

import br.vianna.aula.jsf.controller.login.LoginMb;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marco
 */
@Component
@RequestScoped
public class InternalMb {

    @Autowired
    LoginMb loginMb;

    @PostConstruct
    public void init() {
        if (!loginMb.isLogado()) {
            try {
                loginMb.setMsg("Acesso não autorizado.");
                FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/login/login.xhtml");
            } catch (IOException ex) {

            }
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/pages/external_pages/home?faces-redirect=true";
    }

    public String getNomeLogado() {
        return loginMb.getUser().getNome() + " " + loginMb.getUser().getSobrenome();
    }
}
