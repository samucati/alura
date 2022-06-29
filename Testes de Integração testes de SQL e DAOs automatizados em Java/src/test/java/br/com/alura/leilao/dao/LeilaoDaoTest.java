package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.builder.UsuarioBuilder;
import br.com.alura.leilao.util.builder.LeilaoBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

class LeilaoDaoTest {

    private LeilaoDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void deveriaCadastrarUmLeilao(){
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@teste.com")
                .comSenha("12345")
                .criar();
        em.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorinicial("500")
                .comUsuario(usuario)
                .comData(LocalDate.now())
                .criar();
        leilao = dao.salvar(leilao);

        Leilao leilaoSalvo =  dao.buscarPorId(leilao.getId());
        Assertions.assertNotNull(leilaoSalvo);
    }

    @Test
    void deveriaAtualizarUmLeilao(){
        Usuario usuario = criarUsuario();
        em.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorinicial("500")
                .comUsuario(usuario)
                .comData(LocalDate.now())
                .criar();

        leilao = dao.salvar(leilao);

        leilao.setNome("Celular");
        leilao.setValorInicial(new BigDecimal("400"));
        leilao = dao.salvar(leilao);

        Leilao leilaoSalvo =  dao.buscarPorId(leilao.getId());
        Assertions.assertEquals("Celular", leilaoSalvo.getNome());
        Assertions.assertEquals(new BigDecimal("400"), leilaoSalvo.getValorInicial());

    }

    private Usuario criarUsuario() {
        return new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@teste.com")
                .comSenha("12345")
                .criar();
    }
}
