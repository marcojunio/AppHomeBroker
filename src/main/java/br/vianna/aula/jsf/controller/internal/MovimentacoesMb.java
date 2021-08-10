/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.controller.internal;

import br.vianna.aula.jsf.controller.login.LoginMb;
import br.vianna.aula.jsf.entidades.dao.MovimentacaoDao;
import br.vianna.aula.jsf.requestmodel.UltimaMovimentacao;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marco
 */
@Component("movimentacaoMb")
@RequestScoped
public class MovimentacoesMb {

    @Autowired
    private MovimentacaoDao movimentacaoDao;
    @Autowired
    private LoginMb loginMb;
    private UltimaMovimentacao totalDeposito;
    private UltimaMovimentacao totalSaque;
    private List<UltimaMovimentacao> ultimas3;
    private List<UltimaMovimentacao> movimentacoesDiarias;

    public List<UltimaMovimentacao> getUltimas3Movimentacoes() {
        ultimas3 = movimentacaoDao.ultimas3Movimentacoes(loginMb.getUser().getConta().getId());
        return ultimas3;
    }

    public UltimaMovimentacao getMovimentacoesDepositoTotalDiario() {
        totalDeposito = movimentacaoDao.getMovimetacoesDepositoDiario(loginMb.getUser().getConta().getId());
        return totalDeposito;
    }

    public UltimaMovimentacao getMovimentacoesSaqueTotalDiario() {
        totalSaque = movimentacaoDao.getMovimetacoesSaqueDiario(loginMb.getUser().getConta().getId());
        return totalSaque;
    }
    
    public List<UltimaMovimentacao> getMovimentacoesDiarias() {
        movimentacoesDiarias = movimentacaoDao.movimentacoesDiarias(loginMb.getUser().getConta().getId());
        return movimentacoesDiarias;
    }

}
