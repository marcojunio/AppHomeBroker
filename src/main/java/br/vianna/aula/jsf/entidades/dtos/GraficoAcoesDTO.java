/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.entidades.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Programming
 */
public class GraficoAcoesDTO {

    // Precisa das Ações Compradas pelo Usuário Logado - PREFIXO
    // Precisa da Quantidade de Ações Compradas pelo Usuário Logado - QUANTIDADE 
    
    List<Number> quantidade = new ArrayList<>();
    List<String> prefixos = new ArrayList<>();

    public GraficoAcoesDTO() {
    }
    
    

}
