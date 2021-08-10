/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.entidades.dtos;

/**
 *
 * @author marco
 */
public class CarteiraDto {
    
    private String prefixo;
    private int quantidade;
    private double cotacaoAtual;

    public CarteiraDto() {
    }

    public CarteiraDto(String prefixo, int quantidade) {
        this.prefixo = prefixo;
        this.quantidade = quantidade;
        this.cotacaoAtual = cotacaoAtual;
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getCotacaoAtual() {
        return cotacaoAtual;
    }

    public void setCotacaoAtual(double cotacaoAtual) {
        this.cotacaoAtual = cotacaoAtual;
    }
     
}
