/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.controller.internal;

import javax.enterprise.context.RequestScoped;
import org.springframework.stereotype.Component;

/**
 *
 * @author Programming
 */
@Component("nav")
@RequestScoped
public class NavegacaoMB {
    
    public String dashboardIndex(){
        return "index?faces-redirect=true";
    }
    
    public String acoes(){
        return "acoes?faces-redirect=true";
    }
    
    public String carteira_digital(){
        return "minhacarteiradigital?faces-redirect=true";
    }
    
    public String operacoes(){
        return "operacoes?faces-redirect=true";
    }
    
    public String minha_conta(){
        return "minhacontadigital?faces-redirect=true";
    }
    
    public String movimentacoes(){
        return "movimentacoes?faces-redirect=true";
    }
    
    public String configuracoesusuario(){
        return "movimentacoes?faces-redirect=true";
    }
    
}
