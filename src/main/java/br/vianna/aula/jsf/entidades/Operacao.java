/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.vianna.aula.jsf.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marco
 */
@Entity
@Table(name = "operacao", catalog = "home_broker", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operacao.findAll", query = "SELECT o FROM Operacao o"),
    @NamedQuery(name = "Operacao.findById", query = "SELECT o FROM Operacao o WHERE o.id = :id"),
    @NamedQuery(name = "Operacao.findByQuantidade", query = "SELECT o FROM Operacao o WHERE o.quantidade = :quantidade"),
    @NamedQuery(name = "Operacao.findByData", query = "SELECT o FROM Operacao o WHERE o.data = :data"),
    @NamedQuery(name = "Operacao.findByValorUnitario", query = "SELECT o FROM Operacao o WHERE o.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "Operacao.findByTipo", query = "SELECT o FROM Operacao o WHERE o.tipo = :tipo")})
public class Operacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_unitario", precision = 22, scale = 0)
    private Double valorUnitario;
    @Size(max = 15)
    @Column(name = "tipo", length = 15)
    private String tipo;
    @JoinColumn(name = "Carteira_id", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Carteira carteiraid;

    public Operacao() {
    }

    public Operacao(Integer id) {
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Carteira getCarteiraid() {
        return carteiraid;
    }

    public void setCarteiraid(Carteira carteiraid) {
        this.carteiraid = carteiraid;
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
        if (!(object instanceof Operacao)) {
            return false;
        }
        Operacao other = (Operacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.vianna.aula.jsf.entidades.Operacao[ id=" + id + " ]";
    }
    
}
