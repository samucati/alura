package br.com.alura.tdd.service;

import br.com.alura.tdd.service.modelo.Desempenho;
import br.com.alura.tdd.service.modelo.Funcionario;

import java.math.BigDecimal;

public class ReajusteService {
    public void concederReajuste(Funcionario funcionario, Desempenho desempenho) {
        //TODO aplicar padrão de projeto: (Aplicado o Strategy)
        BigDecimal percentual = desempenho.percentualReajuste();
        BigDecimal reajuste = funcionario.getSalario().multiply(percentual);
        funcionario.reajustarSalario(reajuste);
    }
}
