package io.github.arkanjoms.getcreditanalysisapi.service;

import io.github.arkanjoms.getcreditanalysisapi.model.PessoaPropostaDTO;
import org.springframework.stereotype.Service;

@Service
public class IdadeAvaliacao implements Avaliacao {

    @Override
    public int avaliar(PessoaPropostaDTO proposta) {
        if (proposta.getIdade() > 65) {
            return 2;
        } else if (proposta.getIdade() >= 60 || proposta.getIdade() <= 18) {
            return 1;
        }
        return 0;
    }
}
