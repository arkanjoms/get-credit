package io.github.arkanjoms.getcreditanalysisapi.service;

import io.github.arkanjoms.getcreditanalysisapi.model.PessoaPropostaDTO;
import org.springframework.stereotype.Service;

@Service
public class DependentesAvaliacao implements Avaliacao {

    @Override
    public int avaliar(PessoaPropostaDTO proposta) {
        int peso = proposta.getDependentes();

        if (proposta.getDependentes() > 0) {
            peso += 2;
        }

        if (proposta.getDependentes() > 3) {
            peso += 1;
        }

        return peso;
    }
}
