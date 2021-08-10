package br.vianna.aula.jsf.entidades.dtos;

import br.vianna.aula.jsf.entidades.Carteira;
import br.vianna.aula.jsf.entidades.Conta;
import java.io.Serializable;


public class UsuarioDto implements Serializable {
    
    private String nome,cpf,sobrenome,login;
    private boolean admin;
    private Conta conta;
    private Carteira carteira;

    public UsuarioDto(String nome, String cpf, String sobrenome, String login, boolean admin, Conta conta,Carteira carteira) {
        this.nome = nome;
        this.cpf = cpf;
        this.sobrenome = sobrenome;
        this.login = login;
        this.admin = admin;
        this.conta = conta;
        this.carteira = carteira;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }
    
    public UsuarioDto() {
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
