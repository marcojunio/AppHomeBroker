/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.requestmodel;

import java.util.Date;

/**
 *
 * @author marco
 */
public class UltimaMovimentacao {
    
    private Date data;
    private String tipo;
    private double valor;

    public UltimaMovimentacao(Date data, String tipo, double valor) {
        this.data = data;
        this.tipo = tipo;
        this.valor = valor;
    }
    
    public UltimaMovimentacao() {
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
}
