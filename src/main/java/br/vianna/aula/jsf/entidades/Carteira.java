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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marco
 */
@Entity
@Table(name = "carteira", catalog = "home_broker", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carteira.findAll", query = "SELECT c FROM Carteira c"),
    @NamedQuery(name = "Carteira.findById", query = "SELECT c FROM Carteira c WHERE c.id = :id"),
    @NamedQuery(name = "Carteira.findByQuantidade", query = "SELECT c FROM Carteira c WHERE c.quantidade = :quantidade"),
    @NamedQuery(name = "Carteira.findByPrefixoAcao", query = "SELECT c FROM Carteira c WHERE c.prefixoAcao = :prefixoAcao")})
public class Carteira implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Size(max = 15)
    @Column(name = "prefixo_acao", length = 15)
    private String prefixoAcao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carteiraid", fetch = FetchType.LAZY)
    private List<Operacao> operacaoList;
    @JoinColumn(name = "Conta_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conta contaid;

    public Carteira() {
    }

    public Carteira(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getPrefixoAcao() {
        return prefixoAcao;
    }

    public void setPrefixoAcao(String prefixoAcao) {
        this.prefixoAcao = prefixoAcao;
    }

    @XmlTransient
    public List<Operacao> getOperacaoList() {
        return operacaoList;
    }

    public void setOperacaoList(List<Operacao> operacaoList) {
        this.operacaoList = operacaoList;
    }

    public Conta getContaid() {
        return contaid;
    }

    public void setContaid(Conta contaid) {
        this.contaid = contaid;
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
        if (!(object instanceof Carteira)) {
            return false;
        }
        Carteira other = (Carteira) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.vianna.aula.jsf.entidades.Carteira[ id=" + id + " ]";
    }
    
}
