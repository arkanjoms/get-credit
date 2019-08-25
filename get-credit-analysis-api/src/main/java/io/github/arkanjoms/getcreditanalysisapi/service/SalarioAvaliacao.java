package io.github.arkanjoms.getcreditanalysisapi.service;

import io.github.arkanjoms.getcreditanalysisapi.exception.RendaBaixaException;
import io.github.arkanjoms.getcreditanalysisapi.model.PessoaPropostaDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SalarioAvaliacao implements Avaliacao {

    @Override
    public int avaliar(PessoaPropostaDTO proposta) {
        if (proposta.getRenda().compareTo(new BigDecimal("500.0")) <= 0) {
            throw new RendaBaixaException();
        }
        return 0;
    }
}
