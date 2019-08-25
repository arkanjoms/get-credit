package io.github.arkanjoms.getcreditanalysisapi.service;

import io.github.arkanjoms.getcreditanalysisapi.model.PessoaPropostaDTO;

public interface Avaliacao {
    int avaliar(PessoaPropostaDTO proposta);
}
