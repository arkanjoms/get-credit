package io.github.arkanjoms.getcreditanalysisapi.service;

import io.github.arkanjoms.getcreditanalysisapi.model.EnumEstadoCivil;
import io.github.arkanjoms.getcreditanalysisapi.model.EnumSexo;
import io.github.arkanjoms.getcreditanalysisapi.model.PessoaPropostaDTO;
import org.springframework.stereotype.Service;

@Service
public class SexoAvaliacao implements Avaliacao {

    @Override
    public int avaliar(PessoaPropostaDTO proposta) {
        int peso = 0;

        if (isMasculino(proposta)) {
            peso += 1;
        }

        if (isMasculinoSolteiro(proposta)) {
            peso += 2;
        } else if (isMasculinoCasado(proposta) || isMasculinoDivorciado(proposta)) {
            peso += 3;
        } else if (isFemininoDivorciada(proposta)) {
            peso += 2;
        }

        return peso;
    }

    private boolean isFemininoDivorciada(PessoaPropostaDTO proposta) {
        return isFeminino(proposta) && EnumEstadoCivil.DIVORCIADO.equals(proposta.getEstadoCivil());
    }

    private boolean isMasculinoDivorciado(PessoaPropostaDTO proposta) {
        return isMasculino(proposta) && EnumEstadoCivil.DIVORCIADO.equals(proposta.getEstadoCivil());
    }

    private boolean isFeminino(PessoaPropostaDTO proposta) {
        return EnumSexo.F.equals(proposta.getSexo());
    }

    private boolean isMasculinoCasado(PessoaPropostaDTO proposta) {
        return isMasculino(proposta) && EnumEstadoCivil.CASADO.equals(proposta.getEstadoCivil());
    }

    private boolean isMasculinoSolteiro(PessoaPropostaDTO proposta) {
        return isMasculino(proposta) && EnumEstadoCivil.SOLTEIRO.equals(proposta.getEstadoCivil());
    }

    private boolean isMasculino(PessoaPropostaDTO proposta) {
        return EnumSexo.M.equals(proposta.getSexo());
    }
}
