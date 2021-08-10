/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.entidades.dtos;

import br.vianna.aula.jsf.entidades.Conta;
import br.vianna.aula.jsf.entidades.Movimentacao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Programming
 */
public class GraficoMovimentacoesDTO {
    
    List<Number> depositos = new ArrayList<>();
    List<Number> saque = new ArrayList<>();
    List<String> meses = new ArrayList<>();

    public GraficoMovimentacoesDTO() {
    }

    public List<Number> preencherListDepositos(List<Movimentacao> movimentacoes){

        return null;
    }
    
    public List<Number> preencherListSaque(){
        
        return null;
    }
    
    public List<Number> preencherListMeses(){
        
        return null;
    }
    
    
    public List<Number> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<Number> depositos) {
        this.depositos = depositos;
    }

    public List<Number> getSaque() {
        return saque;
    }

    public void setSaque(List<Number> saque) {
        this.saque = saque;
    }

    public List<String> getMeses() {
        return meses;
    }

    public void setMeses(List<String> meses) {
        this.meses = meses;
    }
    
    
    
}
