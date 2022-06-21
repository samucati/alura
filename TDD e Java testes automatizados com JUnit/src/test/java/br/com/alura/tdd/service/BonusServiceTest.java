package br.com.alura.tdd.service;

import br.com.alura.tdd.service.modelo.Funcionario;
import br.com.alura.tdd.service.service.BonusService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BonusServiceTest {
    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto(){
        BonusService service = new BonusService();

        //assertThrows(IllegalArgumentException.class,
        //        () -> service.calcularBonus(new Funcionario("Samuel", LocalDate.now(), new BigDecimal("21000"))));
        //ou
        try{
            service.calcularBonus(new Funcionario("Samuel", LocalDate.now(), new BigDecimal("21000")));
            fail("Exception não executada");
        } catch (Exception e) {
            Assertions.assertEquals("Funcionario co salario muito alto", e.getMessage());
        }
    }

    @Test
    void bonusDeveriaSerDezPorCentoDoSalario(){
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Samuel", LocalDate.now(), new BigDecimal("2500")));

        assertEquals(new BigDecimal("250.00"), bonus);
    }

    @Test
    void bonusDeveriaSerDezPorCentoParaSalarioDeDezMil(){
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Samuel", LocalDate.now(), new BigDecimal("10000")));

        assertEquals(new BigDecimal("1000.00"), bonus);
    }
}
