package io.github.arkanjoms.getcreditanalysisapi.service;

import io.github.arkanjoms.getcreditanalysisapi.exception.ReprovadaPoliticaCreditoException;
import io.github.arkanjoms.getcreditanalysisapi.model.Faixa;
import io.github.arkanjoms.getcreditanalysisapi.model.PessoaPropostaDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalcularFaixa {

    private static final BigDecimal RAZAO = BigDecimal.valueOf(500.0);

    public Faixa calcular(PessoaPropostaDTO proposta, int peso) {
        BigDecimal desconto = RAZAO.multiply(BigDecimal.valueOf(peso));
        BigDecimal limite = proposta.getRenda().subtract(desconto);

        Faixa faixa = new Faixa();

        if (limite.compareTo(BigDecimal.valueOf(2000)) > 0) {
            faixa.setPiso(BigDecimal.valueOf(2000));
        } else if (limite.compareTo(BigDecimal.valueOf(1500)) > 0) {
            faixa.setPiso(BigDecimal.valueOf(1500));
            faixa.setTeto(BigDecimal.valueOf(2000));
        } else if (limite.compareTo(BigDecimal.valueOf(1000)) > 0) {
            faixa.setPiso(BigDecimal.valueOf(1000));
            faixa.setTeto(BigDecimal.valueOf(1500));
        } else if (limite.compareTo(BigDecimal.valueOf(500)) > 0) {
            faixa.setPiso(BigDecimal.valueOf(500));
            faixa.setTeto(BigDecimal.valueOf(1000));
        } else if (limite.compareTo(BigDecimal.valueOf(100)) > 0) {
            faixa.setPiso(BigDecimal.valueOf(100));
            faixa.setTeto(BigDecimal.valueOf(500));
        } else {
            throw new ReprovadaPoliticaCreditoException();
        }

        return faixa;
    }
}
