package br.vianna.aula.jsf.entidades.dao;

import br.vianna.aula.jsf.entidades.Usuario;
import br.vianna.aula.jsf.entidades.dtos.UsuarioDto;
import br.vianna.aula.jsf.helper.HelperLogin;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UsuarioDao {

    @Autowired
    private EntityManager _entityManager;

    private String src = "br.vianna.aula.jsf.entidades.dtos.";

    @Transactional
    public Usuario salvar(Usuario usuario) {
        _entityManager.persist(usuario);
        return usuario;
    }

    public UsuarioDto verificarUsuario(String login, String senha) {

        try {
              
            Query q = _entityManager.createQuery("SELECT NEW "+src+"UsuarioDto(u.nome,u.cpf,u.sobrenome,u.login,u.admin,c,ca) "
                    + "FROM Usuario u "
                    + "JOIN Conta c ON u.id = c.usuarioid.id "
                    + "LEFT JOIN Carteira ca ON ca.contaid.id = c.id "
                    + "WHERE u.login = :login and u.senha = :senha");
            
            q.setParameter("login", login);
            q.setParameter("senha", HelperLogin.Md5(senha));
            
            return (UsuarioDto) q.getSingleResult();
            
        } catch (Exception e) {
            return null;
        }

    }
}
