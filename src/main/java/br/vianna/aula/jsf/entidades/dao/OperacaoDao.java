/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.entidades.dao;

import br.vianna.aula.jsf.entidades.Operacao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.vianna.aula.jsf.entidades.dtos.OperacaoDto;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author marco
 */
@Component
public class OperacaoDao {

    @Autowired
    private EntityManager _entityManager;
    private String src = "br.vianna.aula.jsf.entidades.dtos.";

    @Transactional
    public void salvar(Operacao operacao) {
        _entityManager.persist(operacao);
    }

    @Transactional
    public List<OperacaoDto> getOperacoes(Integer idConta) {

        try {

            TypedQuery<OperacaoDto> q = _entityManager.createQuery("SELECT NEW " + src + "OperacaoDto(op.data,ca.prefixoAcao,op.tipo,m.valor,op.quantidade) FROM Conta c "
                    + "JOIN Carteira ca ON ca.contaid.id = c.id "
                    + "JOIN Movimentacao m ON m.contaid.id = c.id "
                    + "JOIN Operacao op ON op.carteiraid.id = ca.id "
                    + "WHERE c.id = :id and ORDER BY op.data desc", OperacaoDto.class);

            q.setParameter("id", idConta);

            return q.getResultList();

        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }
}
