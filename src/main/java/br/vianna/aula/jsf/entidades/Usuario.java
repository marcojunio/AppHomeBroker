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
@Table(name = "usuario", catalog = "home_broker", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuario.findBySobrenome", query = "SELECT u FROM Usuario u WHERE u.sobrenome = :sobrenome"),
    @NamedQuery(name = "Usuario.findByProfissao", query = "SELECT u FROM Usuario u WHERE u.profissao = :profissao"),
    @NamedQuery(name = "Usuario.findByEndereco", query = "SELECT u FROM Usuario u WHERE u.endereco = :endereco"),
    @NamedQuery(name = "Usuario.findByCpf", query = "SELECT u FROM Usuario u WHERE u.cpf = :cpf"),
    @NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login = :login"),
    @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
    @NamedQuery(name = "Usuario.findByAdmin", query = "SELECT u FROM Usuario u WHERE u.admin = :admin")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(name = "nome", length = 45)
    private String nome;
    @Size(max = 45)
    @Column(name = "sobrenome", length = 45)
    private String sobrenome;
    @Size(max = 45)
    @Column(name = "profissao", length = 45)
    private String profissao;
    @Size(max = 45)
    @Column(name = "endereco", length = 45)
    private String endereco;
    @Size(max = 45)
    @Column(name = "cpf", length = 45)
    private String cpf;
    @Size(max = 45)
    @Column(name = "login", length = 45)
    private String login;
    @Size(max = 45)
    @Column(name = "senha", length = 45)
    private String senha;
    @Column(name = "admin")
    private boolean admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioid", fetch = FetchType.LAZY)
    private List<Conta> contaList;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String sobrenome, String profissao, String endereco, String cpf, String login, String senha, boolean admin) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.profissao = profissao;
        this.endereco = endereco;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.admin = admin;
    }

    public Usuario(Integer id, String nome, String sobrenome, String profissao, String endereco, String cpf, String login, String senha, boolean admin, List<Conta> contaList) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.profissao = profissao;
        this.endereco = endereco;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.admin = admin;
        this.contaList = contaList;
    }
    
    

    public Usuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @XmlTransient
    public List<Conta> getContaList() {
        return contaList;
    }

    public void setContaList(List<Conta> contaList) {
        this.contaList = contaList;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.vianna.aula.jsf.entidades.Usuario[ id=" + id + " ]";
    }
    
}
