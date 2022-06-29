package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.builder.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

class UsuarioDaoTest {

    private UsuarioDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    private Usuario criarUsuario() {
        return new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@teste.com")
                .comSenha("12345")
                .criar();
    }

    @Test
    void deveriaEncontrarUsuarioCadastrado() {
        Usuario usuario = criarUsuario();
        em.persist(usuario);

        Usuario usuarioEncontrado = dao.buscarPorUsername(usuario.getNome());

        Assertions.assertNotNull(usuarioEncontrado);
    }

    @Test
    void naoDeveriaEncontrarUsuario() {
        Usuario usuario = criarUsuario();
        em.persist(usuario);

        Assertions.assertThrows(NoResultException.class,
                () -> this.dao.buscarPorUsername("beltrano"));
    }

    @Test
    void deveriaRemoverUmUsuario(){
        Usuario usuario = criarUsuario();
        em.persist(usuario);
        dao.deletar(usuario);

        Assertions.assertThrows(NoResultException.class,
                () -> this.dao.buscarPorUsername(usuario.getNome()));
    }
}
