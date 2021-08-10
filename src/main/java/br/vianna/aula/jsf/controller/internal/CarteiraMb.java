/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.controller.internal;

import br.vianna.aula.jsf.controller.login.LoginMb;
import br.vianna.aula.jsf.entidades.Carteira;
import br.vianna.aula.jsf.entidades.Movimentacao;
import br.vianna.aula.jsf.entidades.Operacao;
import br.vianna.aula.jsf.entidades.dao.CarteiraDao;
import br.vianna.aula.jsf.entidades.dao.ContaDao;
import br.vianna.aula.jsf.entidades.dao.MovimentacaoDao;
import br.vianna.aula.jsf.entidades.dao.OperacaoDao;
import br.vianna.aula.jsf.entidades.dtos.CarteiraDto;
import br.vianna.aula.jsf.services.TaxasAdicionaisService;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marco
 */
@Component
@RequestScoped
public class CarteiraMb {

    @Autowired
    private LoginMb loginMb;
    private List<CarteiraDto> carteiraDto;
    @Autowired
    private CarteiraDao carteiraDao;
    private int quantidadeASerVendida;
    private boolean alerta;
    private boolean sucesso;
    @Autowired
    private MovimentacaoDao movimentacaoDao;
    @Autowired
    private ContaDao contaDao;
    @Autowired
    private OperacaoDao operacaoDao;
    @Autowired
    private TaxasAdicionaisService taxasService;

    public CarteiraMb() {
        alerta = false;
        sucesso = false;
    }

    public List<CarteiraDto> getCarteira() {
        carteiraDto = carteiraDao.getCarteira(loginMb.getUser().getConta().getId());
        return carteiraDto;
    }

    public String vendeAcao(int quantidadeAtual, double preco, String prefixo) {
        
         DecimalFormat df = new DecimalFormat("#,###.00");
        double subTotal = quantidadeASerVendida * preco;

        if (!(quantidadeASerVendida <= quantidadeAtual)) {
            alerta = true;
            quantidadeASerVendida = 0;
            return "";
        } else {

            Carteira carteira = carteiraDao.getCarteiraByIdAndPrefix(loginMb.getUser().getConta().getId(), prefixo);
           

            Operacao operacao = new Operacao();
            operacao.setCarteiraid(carteira);
            operacao.setData(new Date());
            operacao.setQuantidade(quantidadeASerVendida);
            operacao.setTipo("VENDA");
            operacao.setValorUnitario(preco);
            operacaoDao.salvar(operacao);
            
             double taxa = taxasService.taxasAdicionais(subTotal, operacao.getTipo());
            
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setContaid(loginMb.getUser().getConta());
            movimentacao.setData(new Date());
            movimentacao.setTipo("VENDA " + prefixo + " QNTD: " + quantidadeASerVendida + " TAXA: R$" + df.format(taxa) + "");
            movimentacao.setValor(subTotal + taxa);
            movimentacaoDao.salvar(movimentacao);

            carteiraDao.atualizarQuantidade(quantidadeASerVendida, prefixo, loginMb.getUser().getConta().getId(), operacao.getTipo());
            contaDao.creditarSaldo(subTotal, loginMb.getUser().getConta().getId());

            quantidadeAtual = 0;
            sucesso = true;
            return "";
        }
    }

    public int getQuantidadeASerVendida() {
        return quantidadeASerVendida;
    }

    public void setQuantidadeASerVendida(int quantidadeASerVendida) {
        this.quantidadeASerVendida = quantidadeASerVendida;
    }

    public boolean isAlerta() {
        return alerta;
    }

    public void setAlerta(boolean alerta) {
        this.alerta = alerta;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }
}
