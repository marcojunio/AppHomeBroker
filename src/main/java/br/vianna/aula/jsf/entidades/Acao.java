/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.entidades;

import java.io.Serializable;
import java.util.Date;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author marco
 */
public class Acao implements Serializable {

    private String codigo;
    private String nome;
    private Date data;
    private double preco;

    public Acao(String codigo, String nome,Date data, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.data = data;
        this.preco = preco;
    }

    public Acao() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }
    
    public double getPreco() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api-bolsa.sytes.net/bolsa/" + this.codigo + "";
        this.setPreco(restTemplate.getForObject(url, Acao.class).preco);
        return preco;
    }

    private void setPreco(double preco) {
        this.preco = preco;
    }
}
