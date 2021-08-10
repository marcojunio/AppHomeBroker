/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.controller.internal;

import br.vianna.aula.jsf.controller.login.LoginMb;
import br.vianna.aula.jsf.entidades.Acao;
import br.vianna.aula.jsf.entidades.Carteira;
import br.vianna.aula.jsf.entidades.Movimentacao;
import br.vianna.aula.jsf.entidades.Operacao;
import br.vianna.aula.jsf.entidades.dao.CarteiraDao;
import br.vianna.aula.jsf.entidades.dao.ContaDao;
import br.vianna.aula.jsf.entidades.dao.MovimentacaoDao;
import br.vianna.aula.jsf.entidades.dao.OperacaoDao;
import br.vianna.aula.jsf.requestmodel.ContaSaldo;
import br.vianna.aula.jsf.services.BolsaDeValoresService;
import br.vianna.aula.jsf.services.TaxasAdicionaisService;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marco
 */
@Component
@RequestScoped
public class MercadoAcaoMb {

    private List<Acao> acoes;
    private List<Acao> hotAcoes;
    private Date atualizacao;
    private int quantidade;
    @Autowired
    private LoginMb loginMb;
    @Autowired
    private CarteiraDao carteiraDao;
    private boolean sucesso;
    private boolean alerta;
    @Autowired
    private MovimentacaoDao movimentacaoDao;
    private ContaSaldo contaSaldo;
    @Autowired
    private ContaDao contaDao;
    @Autowired
    private OperacaoDao operacaoDao;
    @Autowired
    private TaxasAdicionaisService taxasService;

    @PostConstruct
    public void init() {
        acoes = BolsaDeValoresService.getAll();
        hotAcoes = BolsaDeValoresService.getHotAcoes();
        atualizacao = new Date();
    }

    public List<Acao> getListaAcoes() {
        return acoes;
    }

    public List<Acao> getHotAcoes() {
        return hotAcoes;
    }

    public Date getDateAtualizacao() {
        return atualizacao;
    }

    public boolean isAlerta() {
        return alerta;
    }

    public void setAlerta(boolean alerta) {
        this.alerta = alerta;
    }

    public String comprarAcao(String prefix, double preco) {

        double subTotal = preco * quantidade;
        DecimalFormat df = new DecimalFormat("#,###.00");

        if (quantidade != 0 && getSaldo() >= subTotal) {

            if (carteiraDao.ExisteCarteira(prefix, loginMb.getUser().getConta().getId())) {

                Carteira carteira = carteiraDao.getCarteiraByIdAndPrefix(loginMb.getUser().getConta().getId(), prefix);

                Operacao operacao = new Operacao();
                operacao.setCarteiraid(carteira);
                operacao.setData(new Date());
                operacao.setQuantidade(quantidade);
                operacao.setTipo("COMPRA");
                operacao.setValorUnitario(preco);
                operacaoDao.salvar(operacao);

                double taxa = taxasService.taxasAdicionais(subTotal, operacao.getTipo());

                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setContaid(loginMb.getUser().getConta());
                movimentacao.setData(new Date());
                movimentacao.setTipo("COMPRA: " + prefix + " QNTD: " + quantidade + " TAXA: R$" + df.format(taxa) + "");
                movimentacao.setValor(subTotal + taxa);
                movimentacaoDao.salvar(movimentacao);

                carteiraDao.atualizarQuantidade(quantidade, prefix, loginMb.getUser().getConta().getId(), operacao.getTipo());
                contaDao.debitarSaldo(subTotal, loginMb.getUser().getConta().getId());

                quantidade = 0;
                sucesso = true;
                return "";

            } else {
                Carteira carteira = new Carteira();
                carteira.setContaid(loginMb.getUser().getConta());
                carteira.setPrefixoAcao(prefix);
                carteira.setQuantidade(quantidade);
                carteiraDao.salvar(carteira);

                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setContaid(loginMb.getUser().getConta());
                movimentacao.setData(new Date());
                movimentacao.setTipo("COMPRA DA AÇÃO " + prefix + " QUANTIDADE: " + quantidade + "");
                movimentacao.setValor(subTotal);
                movimentacaoDao.salvar(movimentacao);

                Operacao operacao = new Operacao();
                operacao.setCarteiraid(carteira);
                operacao.setData(new Date());
                operacao.setQuantidade(quantidade);
                operacao.setTipo("COMPRA");
                operacao.setValorUnitario(preco);
                operacaoDao.salvar(operacao);

                contaDao.debitarSaldo(subTotal, loginMb.getUser().getConta().getId());

                quantidade = 0;
                sucesso = true;
                return "";

            }

        } else {
            alerta = true;
            quantidade = 0;
        }

        return "";
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSaldo() {
        contaSaldo = contaDao.getSaldoById(loginMb.getUser().getConta().getId());
        return contaSaldo.getSaldo();
    }
}
