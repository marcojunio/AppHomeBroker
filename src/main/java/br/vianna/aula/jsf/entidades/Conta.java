/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marco
 */
@Entity
@Table(name = "conta", catalog = "home_broker", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c"),
    @NamedQuery(name = "Conta.findById", query = "SELECT c FROM Conta c WHERE c.id = :id"),
    @NamedQuery(name = "Conta.findBySaldo", query = "SELECT c FROM Conta c WHERE c.saldo = :saldo")})
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo", precision = 22, scale = 0)
    private Double saldo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaid", fetch = FetchType.LAZY)
    private List<Movimentacao> movimentacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaid", fetch = FetchType.LAZY)
    private List<Carteira> carteiraList;
    @JoinColumn(name = "Usuario_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioid;

    public Conta() {
    }

    public Conta(Integer id, Double saldo, Usuario usuarioid) {
        this.id = id;
        this.saldo = saldo;
        this.usuarioid = usuarioid;
    }

    public Conta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @XmlTransient
    public List<Movimentacao> getMovimentacaoList() {
        return movimentacaoList;
    }

    public void setMovimentacaoList(List<Movimentacao> movimentacaoList) {
        this.movimentacaoList = movimentacaoList;
    }

    @XmlTransient
    public List<Carteira> getCarteiraList() {
        return carteiraList;
    }

    public void setCarteiraList(List<Carteira> carteiraList) {
        this.carteiraList = carteiraList;
    }

    public Usuario getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Usuario usuarioid) {
        this.usuarioid = usuarioid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.vianna.aula.jsf.entidades.Conta[ id=" + id + " ]";
    }
    
}
