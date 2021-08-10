/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.entidades.dao;

import br.vianna.aula.jsf.entidades.Carteira;
import br.vianna.aula.jsf.entidades.dtos.CarteiraDto;
import br.vianna.aula.jsf.services.BolsaDeValoresService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author marco
 */
@Component
public class CarteiraDao {

    @Autowired
    private EntityManager _entityManager;
    private String src = "br.vianna.aula.jsf.entidades.dtos.";

    @Transactional
    public void salvar(Carteira carteira) {
        _entityManager.persist(carteira);
    }

    @Transactional
    public boolean ExisteCarteira(String prefixo, Integer id) {

        Long resultado = new Long(0L);
        Query q = _entityManager.createQuery("SELECT COUNT(c) FROM Carteira c WHERE c.prefixoAcao = :prefixo and c.contaid.id = :id");

        q.setParameter("id", id);
        q.setParameter("prefixo", prefixo);

        resultado = (Long) q.getSingleResult();

        if (resultado == 0) {
            return false;
        }

        return true;
    }

    @Transactional
    public Carteira getCarteiraByIdAndPrefix(Integer id, String prefix) {

        Query q = _entityManager.createQuery("SELECT c FROM Carteira c WHERE c.contaid.id = :id and c.prefixoAcao = :prefix");

        q.setParameter("id", id);
        q.setParameter("prefix", prefix);

        return (Carteira) q.getSingleResult();
    }

    @Transactional
    public void atualizarQuantidade(int quantidade, String prefix, Integer id, String tipoVenda) {
        Query q = _entityManager.createQuery("SELECT c FROM Carteira c WHERE c.contaid.id = :id and c.prefixoAcao = :prefix");

        q.setParameter("id", id);
        q.setParameter("prefix", prefix);

        Carteira carteira = (Carteira) q.getSingleResult();

        if (tipoVenda.equals("COMPRA")) {
            int acumular = carteira.getQuantidade() + quantidade;
            carteira.setQuantidade(acumular);
            _entityManager.merge(carteira);

        } else {
            int acumular = carteira.getQuantidade() - quantidade;
            if (acumular == 0) {
                _entityManager.remove(carteira);
            } else {
                carteira.setQuantidade(acumular);
                _entityManager.merge(carteira);
            }
        }

    }

    @Transactional
    public List<CarteiraDto> getCarteira(Integer id) {

        try {
            Query q = _entityManager.createQuery("SELECT NEW " + src + "CarteiraDto(c.prefixoAcao,c.quantidade) FROM Carteira c "
                    + "WHERE c.contaid.id = :id");

            q.setParameter("id", id);

            List<CarteiraDto> carteiraDto = q.getResultList();

            if (!carteiraDto.isEmpty()) {
                carteiraDto.forEach(it -> {
                    double valor = BolsaDeValoresService.getValorAcao(it.getPrefixo());
                    it.setCotacaoAtual(valor);
                });

                return carteiraDto;
            }

            return null;
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

}
