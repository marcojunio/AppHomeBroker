package br.vianna.aula.jsf.entidades.dao;

import br.vianna.aula.jsf.entidades.Conta;
import br.vianna.aula.jsf.requestmodel.ContaSaldo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class ContaDao {

    @Autowired
    private EntityManager _entityManager;
    private String src = "br.vianna.aula.jsf.requestmodel.";

    @Transactional
    public void salvar(Conta conta) {
        _entityManager.persist(conta);
    }

    @Transactional
    public void realizarMovimentacao(Integer id, double valor, String tipo) {
        double total = 0;
        Query q = _entityManager.createQuery("SELECT c FROM Conta c WHERE c.id = :id");

        q.setParameter("id", id);
        Conta conta = (Conta) q.getSingleResult();

        if (tipo.equals("DEPÓSITO")) {
            total = conta.getSaldo() + valor;
        } else {
            total = conta.getSaldo() - valor;
        }

        conta.setSaldo(total);

        _entityManager.merge(conta);
    }

    @Transactional
    public ContaSaldo getSaldoById(Integer id) {

        Query q = _entityManager.createQuery("SELECT NEW " + src + "ContaSaldo(c.saldo) FROM Conta c WHERE c.id = :id");

        q.setParameter("id", id);

        return (ContaSaldo) q.getSingleResult();
    }

    @Transactional
    public void debitarSaldo(double valor, Integer id) {

        Query q = _entityManager.createQuery("SELECT c FROM Conta c WHERE c.id = :id");

        q.setParameter("id", id);

        Conta conta = (Conta) q.getSingleResult();
        double totalDebitado = conta.getSaldo() - valor;
        conta.setSaldo(totalDebitado);

        _entityManager.merge(conta);
    }

    @Transactional
    public void creditarSaldo(double valor, Integer id) {

        Query q = _entityManager.createQuery("SELECT c FROM Conta c WHERE c.id = :id");

        q.setParameter("id", id);

        Conta conta = (Conta) q.getSingleResult();
        double totalDebitado = conta.getSaldo() + valor;
        conta.setSaldo(totalDebitado);

        _entityManager.merge(conta);
    }
    
    @Transactional
    public Conta getContaById(int id){
        
        Query q = _entityManager.createNamedQuery("Conta.findById");
        
        q.setParameter("id", id);
        
        return (Conta) q.getSingleResult();
    }
}
