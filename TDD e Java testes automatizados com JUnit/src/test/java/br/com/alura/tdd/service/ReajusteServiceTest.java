package br.com.alura.tdd.service;

import br.com.alura.tdd.service.modelo.Desempenho;
import br.com.alura.tdd.service.modelo.Funcionario;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReajusteServiceTest {

    private ReajusteService service;
    private Funcionario funcionario;

    @BeforeEach
    public void inicializar(){
        System.out.println("Inicializar");
        this.service = new ReajusteService();
        this.funcionario = new Funcionario("Maria", LocalDate.now(), new BigDecimal("1000.00"));
    }

   @AfterEach
   public void finalizar() {
        System.out.println("Finalizar: " + this.funcionario.getSalario());
    }

    @BeforeAll
    public static void antesDeTodos(){
        System.out.println("Antes de Todos");
    }

    @AfterAll
    public static void depoisDeTodos(){
        System.out.println("Depois de Todos");
    }

    @Test
    void reajusteDeveriaSerdeTresPorcendoQuandoDesempenhoForADesejar(){
        service.concederReajuste(funcionario, Desempenho.A_DESEJAR);
        Assertions.assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
    }

    @Test
    void reajusteDeQuinzePorcendoQuandoDesempenhoForBom(){
        service.concederReajuste(funcionario, Desempenho.BOM);
        Assertions.assertEquals(new BigDecimal("1150.00"), funcionario.getSalario());
    }

    @Test
    void reajusteDeVintePorcendoQuandoDesempenhoForOtimo(){
        service.concederReajuste(funcionario, Desempenho.OTIMO);
        Assertions.assertEquals(new BigDecimal("1200.00"), funcionario.getSalario());
    }
}
