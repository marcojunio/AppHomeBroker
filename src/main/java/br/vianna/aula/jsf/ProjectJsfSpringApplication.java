package br.vianna.aula.jsf;


import br.vianna.aula.jsf.entidades.Conta;
import br.vianna.aula.jsf.entidades.Usuario;
import br.vianna.aula.jsf.entidades.Movimentacao;
import br.vianna.aula.jsf.entidades.dao.UsuarioDao;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectJsfSpringApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(ProjectJsfSpringApplication.class, args);
    }
    
    @Autowired
    UsuarioDao userD;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Servidor On!");
        
        Usuario u = new Usuario(0, "Gabriel", "Teste", "Teste", "Teste", "123", "GabrielTeste", "123", false);
        Conta c = new Conta(0, 0.0, u);
        Movimentacao m1 = new Movimentacao(0, 150.0, "Depósito", new Date());
        Movimentacao m2 = new Movimentacao(0, 150.0, "Depósito", new Date());
        
        m1.setContaid(c);
        m2.setContaid(c);
//        
        System.out.println("Usuário: "+ u.getNome());
        System.out.println("Movimentação 2: "+ m1.getValor());
        System.out.println("Movimentação 1: "+ m2.getValor());
        
        System.out.println("Dados: "+ c.getUsuarioid().getNome());
        

    }
}
