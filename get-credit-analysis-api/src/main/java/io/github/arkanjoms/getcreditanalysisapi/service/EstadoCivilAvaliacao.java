package io.github.arkanjoms.getcreditanalysisapi.service;

import io.github.arkanjoms.getcreditanalysisapi.model.EnumEstadoCivil;
import io.github.arkanjoms.getcreditanalysisapi.model.PessoaPropostaDTO;
import org.springframework.stereotype.Service;

@Service
public class EstadoCivilAvaliacao implements Avaliacao {

    @Override
    public int avaliar(PessoaPropostaDTO proposta) {
        if (EnumEstadoCivil.CASADO.equals(proposta.getEstadoCivil()) || EnumEstadoCivil.DIVORCIADO.equals(proposta.getEstadoCivil())) {
            return 1;
        }
        return 0;
    }
}
