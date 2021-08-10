/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.controller.external;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import org.springframework.stereotype.Component;

/**
 *
 * @author Programming
 */
@Component("homeMB")
@SessionScoped
public class HomeMB implements Serializable {
    
    private String loginIndex;
    
    public HomeMB() {
    }

    public String viewLogin() {
        return "/pages/login/login?faces-redirect=true";
    }

    public String viewCadastro() {
        return "/pages/login/criar-conta?faces-redirect=true";
    }

    public String getLoginIndex() {
        return loginIndex;
    }

    public void setLoginIndex(String loginIndex) {
        this.loginIndex = loginIndex;
    }
}
