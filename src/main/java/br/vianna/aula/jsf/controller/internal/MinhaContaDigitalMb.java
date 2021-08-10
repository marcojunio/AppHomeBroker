/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.controller.internal;

import br.vianna.aula.jsf.controller.login.LoginMb;
import br.vianna.aula.jsf.entidades.Movimentacao;
import br.vianna.aula.jsf.entidades.dao.ContaDao;
import br.vianna.aula.jsf.entidades.dao.MovimentacaoDao;
import br.vianna.aula.jsf.requestmodel.ContaSaldo;
import br.vianna.aula.jsf.entidades.enums.EStatusMinhaConta;
import br.vianna.aula.jsf.requestmodel.UltimaMovimentacao;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Programming
 */
@Component(value = "minhaconta")
@SessionScoped
public class MinhaContaDigitalMb implements Serializable {

    private EStatusMinhaConta status;
    private double valor;
    @Autowired
    private LoginMb loginMb;
    @Autowired
    private ContaDao contaDao;
    private boolean alerta;
    private boolean sucesso;
    @Autowired
    private MovimentacaoDao movimentacaoDao;
    private String nome;
    private String numeroCartao;
    private String dataExpiracao;
    private String cvv;
    private ContaSaldo conta;
    private UltimaMovimentacao ultima;
    
    public boolean isAlerta() {
        return alerta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(String dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setAlerta(boolean alerta) {
        this.alerta = alerta;
    }

    public MinhaContaDigitalMb() {
        alerta = false;
        sucesso = false;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String viewSaque() {
        status = EStatusMinhaConta.SAQUE;

        return "";
    }

    public String viewDeposito() {
        status = EStatusMinhaConta.DEPOSITO;

        return "";
    }

    public boolean isDeposito() {
        return status == EStatusMinhaConta.DEPOSITO;
    }

    public boolean isSaque() {
        return status == EStatusMinhaConta.SAQUE;
    }

    public EStatusMinhaConta getStatus() {
        return status;
    }

    public void setStatus(EStatusMinhaConta status) {
        this.status = status;
    }

    public String realizarDeposito() {

        if (valor > 0) {
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setContaid(loginMb.getUser().getConta());
            movimentacao.setData(new Date());
            movimentacao.setTipo("DEPÓSITO");
            movimentacao.setValor(valor);

            contaDao.realizarMovimentacao(loginMb.getUser().getConta().getId(), valor, movimentacao.getTipo());

            movimentacaoDao.salvar(movimentacao);

            alerta = false;
            sucesso = true;
            nome = "";
            cvv = "";
            dataExpiracao = "";
            numeroCartao = "";
            valor = 0;
            
            return "";
        } else {
            alerta = true;
            return "";
        }
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String realizarSaque() {

        if (getSaldoUsuario() >= valor && valor > 0) {
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setContaid(loginMb.getUser().getConta());
            movimentacao.setData(new Date());
            movimentacao.setTipo("SAQUE");
            movimentacao.setValor(valor);

            contaDao.realizarMovimentacao(loginMb.getUser().getConta().getId(), valor, movimentacao.getTipo());

            movimentacaoDao.salvar(movimentacao);

            nome = "";
            cvv = "";
            dataExpiracao = "";
            valor = 0;
            alerta = false;
            sucesso = true;
            return "";
        } else {
            alerta = true;
            return "";
        }
    }

    public double getSaldoUsuario() {
        conta = contaDao.getSaldoById(loginMb.getUser().getConta().getId());
        return conta.getSaldo();
    }

    public UltimaMovimentacao getUltimaMovimentacao() {
        ultima = movimentacaoDao.retornaUltimaMovimentacao(loginMb.getUser().getConta().getId());
        return ultima;
    }
}
