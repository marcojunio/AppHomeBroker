/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.entidades.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author marco
 */
public class OperacaoDto implements Serializable{

    private Date data;
    private String prefixo;
    private String tipo;
    private double valor;
    private int quantidade;

    public OperacaoDto() {
    }

    public OperacaoDto(Date data, String prefixo, String tipo, double valor, int quantidade) {
        this.data = data;
        this.prefixo = prefixo;
        this.tipo = tipo;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
