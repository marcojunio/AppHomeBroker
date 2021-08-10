/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.services;

import br.vianna.aula.jsf.entidades.Conta;
import br.vianna.aula.jsf.entidades.Movimentacao;
import br.vianna.aula.jsf.entidades.dao.ContaDao;
import br.vianna.aula.jsf.entidades.dao.MovimentacaoDao;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marco
 */
@Component
public class TaxasAdicionaisService {

    @Autowired
    private MovimentacaoDao movimentacaoDao;
    @Autowired
    private ContaDao contaDao;
    private double taxa;

    public double taxasAdicionais(double total, String tipo) {

        Movimentacao movimentacao = new Movimentacao();
        Conta conta = contaDao.getContaById(7);

        if (tipo.equals("COMPRA")) {

            if (total <= 5000) {

                movimentacao.setContaid(conta);
                movimentacao.setData(new Date());
                movimentacao.setTipo("TAXA DE CORRETAGEM SOB COMPRA");
                movimentacao.setValor(15.0);

                movimentacaoDao.salvar(movimentacao);

                contaDao.creditarSaldo(15, 7);
                
                taxa = 15.0;
                
                return taxa;

            } else {

                movimentacao.setContaid(conta);
                movimentacao.setData(new Date());
                movimentacao.setTipo("TAXA DE CORRETAGEM SOB COMPRA");
                movimentacao.setValor(total * 0.05 + 10.21);

                movimentacaoDao.salvar(movimentacao);

                contaDao.creditarSaldo(total * 0.05 + 10.21, 7);
                
                taxa = total * 0.05 + 10.21;
                
                return taxa;
            }
        } else {
            if (total <= 5000) {

                movimentacao.setContaid(conta);
                movimentacao.setData(new Date());
                movimentacao.setTipo("TAXA DE CORRETAGEM SOB VENDA");
                movimentacao.setValor(15.0);

                movimentacaoDao.salvar(movimentacao);

                contaDao.creditarSaldo(15, 7);
                taxa = 15.0;
                
                return taxa;
                
            } else {

                movimentacao.setContaid(conta);
                movimentacao.setData(new Date());
                movimentacao.setTipo("TAXA DE CORRETAGEM SOB VENDA");
                movimentacao.setValor(total * 0.05 + 10.21);

                contaDao.creditarSaldo(total * 0.05 + 10.21, 7);
                
                taxa = total * 0.05 + 10.21;
                
                movimentacaoDao.salvar(movimentacao);
                
                return taxa;
            }

        }
    }
}
