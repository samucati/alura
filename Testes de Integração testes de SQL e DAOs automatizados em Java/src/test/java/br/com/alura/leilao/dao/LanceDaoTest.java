package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.builder.LanceBuilder;
import br.com.alura.leilao.util.builder.LeilaoBuilder;
import br.com.alura.leilao.util.builder.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

class LanceDaoTest {

    private LeilaoDao dao;
    private LanceDao lanceDao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new LeilaoDao(em);
        this.lanceDao = new LanceDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void deveriaEncontrarMaiorLance(){
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

        Lance lance1 = new LanceBuilder()
                .comUsuario(usuario)
                .comData(LocalDate.now())
                .comValor("100")
                .comLeilao(leilaoSalvo)
                .criar();

        em.persist(lance1);

        Lance lance2 = new LanceBuilder()
                .comUsuario(usuario)
                .comData(LocalDate.now())
                .comValor("200")
                .comLeilao(leilaoSalvo)
                .criar();

        em.persist(lance2);

        Lance lance = lanceDao.buscarMaiorLanceDoLeilao(leilaoSalvo);

       Assertions.assertEquals(new BigDecimal("200"), lance.getValor());
    }
}
