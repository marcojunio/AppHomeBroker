/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.entidades.dao;

import br.vianna.aula.jsf.entidades.Movimentacao;
import br.vianna.aula.jsf.requestmodel.UltimaMovimentacao;
import java.util.Date;
import java.util.LinkedList;
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
public class MovimentacaoDao {

    @Autowired
    private EntityManager _entityManager;
    private String src = "br.vianna.aula.jsf.requestmodel.";

    @Transactional
    public void salvar(Movimentacao movimentacao) {
        _entityManager.persist(movimentacao);
    }

    @Transactional
    public UltimaMovimentacao retornaUltimaMovimentacao(Integer id) {

        Integer idMovimentacao;
        Query q = _entityManager.createQuery("SELECT max(m.id) from Movimentacao m where m.contaid.id = :id order by m.id");

        q.setParameter("id", id);

        q.setMaxResults(1);

        idMovimentacao = (Integer) q.getSingleResult();

        if (idMovimentacao == null) {
            return null;
        }

        Query movimentacao = _entityManager.createQuery("SELECT NEW " + src + "UltimaMovimentacao(m.data,m.tipo,m.valor) "
                + "from Movimentacao m "
                + "where m.contaid.id = :id and m.id = :idmovi");

        movimentacao.setParameter("id", id);
        movimentacao.setParameter("idmovi", idMovimentacao);

        UltimaMovimentacao ultimaMovimentacao = (UltimaMovimentacao) movimentacao.getSingleResult();

        return ultimaMovimentacao;
    }

    @Transactional
    public List<UltimaMovimentacao> movimentacoesDiarias(Integer id) {

        try {
            List<Integer> idMovimentacao = new LinkedList<>();
            List<UltimaMovimentacao> ultimaMovimentacoes = new LinkedList<>();

            Query q = _entityManager.createQuery("SELECT m.id FROM Movimentacao m WHERE m.contaid.id = :id order by m.id desc");

            q.setParameter("id", id);

            idMovimentacao = q.getResultList();

            if (idMovimentacao.isEmpty()) {
                return null;
            }

            for (int i = 0; i < idMovimentacao.size(); i++) {
                Query movimentacao = _entityManager.createQuery("SELECT NEW " + src + "UltimaMovimentacao(m.data,m.tipo,m.valor) "
                        + "from Movimentacao m "
                        + "where m.contaid.id = :id and m.id = :idmovi");

                movimentacao.setParameter("id", id);
                movimentacao.setParameter("idmovi", idMovimentacao.get(i));

                ultimaMovimentacoes.add((UltimaMovimentacao) movimentacao.getSingleResult());
            }

            return ultimaMovimentacoes;
        } catch (NoResultException ex) {
            return null;
        }

    }

    @Transactional
    public List<UltimaMovimentacao> ultimas3Movimentacoes(Integer id) {

        try {
            List<Integer> idMovimentacao = new LinkedList<>();
            List<UltimaMovimentacao> ultimaMovimentacoes = new LinkedList<>();

            Query q = _entityManager.createQuery("SELECT m.id from Movimentacao m where m.contaid.id = :id order by m.id desc");

            q.setParameter("id", id);

            q.setMaxResults(3);

            idMovimentacao = q.getResultList();

            if (idMovimentacao.isEmpty()) {
                return null;
            }

            idMovimentacao.forEach(it -> {
                Query movimentacao = _entityManager.createQuery("SELECT NEW " + src + "UltimaMovimentacao(m.data,m.tipo,m.valor) "
                        + "from Movimentacao m "
                        + "where m.contaid.id = :id and m.id = :idmovi");

                movimentacao.setParameter("id", id);
                movimentacao.setParameter("idmovi", it);

                ultimaMovimentacoes.add((UltimaMovimentacao) movimentacao.getSingleResult());
            });

            return ultimaMovimentacoes;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Transactional
    public UltimaMovimentacao getMovimetacoesDepositoDiario(Integer id) {

        try {
            TypedQuery<UltimaMovimentacao> q = _entityManager.createQuery("SELECT NEW " + src + "UltimaMovimentacao(m.data,m.tipo,SUM(m.valor)) FROM Movimentacao m "
                    + "WHERE m.data >= CURRENT_DATE and m.tipo = :tipo and  m.contaid.id = :id", UltimaMovimentacao.class);

            q.setParameter("tipo", "DEPÓSITO");
            q.setParameter("id", id);

            UltimaMovimentacao ultimaMovimentacao = q.getSingleResult();

            return ultimaMovimentacao;

        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @Transactional
    public UltimaMovimentacao getMovimetacoesSaqueDiario(Integer id) {

        try {
            TypedQuery<UltimaMovimentacao> q = _entityManager.createQuery("SELECT NEW " + src + "UltimaMovimentacao(m.data,m.tipo,SUM(m.valor)) FROM Movimentacao m "
                    + "WHERE m.data >= CURRENT_DATE and m.tipo = :tipo and m.contaid.id = :id", UltimaMovimentacao.class);

            q.setParameter("tipo", "SAQUE");
            q.setParameter("id", id);

            UltimaMovimentacao ultimaMovimentacao = q.getSingleResult();

            return ultimaMovimentacao;

        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }
}
