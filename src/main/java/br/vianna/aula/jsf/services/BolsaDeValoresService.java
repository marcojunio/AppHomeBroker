/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.services;

import br.vianna.aula.jsf.entidades.Acao;
import java.util.LinkedList;
import java.util.List;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author marco
 */
public class BolsaDeValoresService {

    public static List<Acao> getAll() {

        List<Acao> listAcoes = new LinkedList<>();
        String[] prefixos = {"vale3", "embr3", "azul4", "rent3","b3sa3","itub4","ntco3","abev3","bpac11","itsa4"};
        
        for (int i = 0; i < prefixos.length; i++) {
            Acao acaoApi = new Acao();
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://api-bolsa.sytes.net/bolsa/"+prefixos[i]+"";
            acaoApi = restTemplate.getForObject(url, Acao.class);
            
            listAcoes.add(acaoApi);
        }

        return listAcoes;
    }
    
    
    public static List<Acao> getHotAcoes() {

        List<Acao> listAcoes = new LinkedList<>();
        String[] prefixos = {"ntco3","abev3","bpac11","itsa4"};
        
        for (int i = 0; i < prefixos.length; i++) {
            Acao acaoApi = new Acao();
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://api-bolsa.sytes.net/bolsa/"+prefixos[i]+"";
            acaoApi = restTemplate.getForObject(url, Acao.class);
            
            listAcoes.add(acaoApi);
        }

        return listAcoes;
    }
    
    public static double getValorAcao(String prefixo){
        
        Acao acao = new Acao();
        RestTemplate rs = new RestTemplate();
        String url = "http://api-bolsa.sytes.net/bolsa/"+prefixo+"";
        acao = rs.getForObject(url, Acao.class);
        
        return acao.getPreco();
    }
}
