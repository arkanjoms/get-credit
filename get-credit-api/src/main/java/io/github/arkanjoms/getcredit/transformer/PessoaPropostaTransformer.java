package io.github.arkanjoms.getcredit.transformer;

import io.github.arkanjoms.getcredit.model.Pessoa;
import io.github.arkanjoms.getcredit.model.Proposta;
import io.github.arkanjoms.getcredit.model.dto.PessoaPropostaDTO;
import org.springframework.stereotype.Component;

@Component
public class PessoaPropostaTransformer {

    public PessoaPropostaDTO toDTO(Pessoa pessoa) {
        return toDTOPessoaBuilder(pessoa).build();
    }

    public PessoaPropostaDTO toDTO(Pessoa pessoa, Proposta proposta) {
        return toDTOPessoaBuilder(pessoa)
                .resultado(proposta.getResultado())
                .motivoNegativa(proposta.getMotivoNegativa())
                .faixa(proposta.getFaixa())
                .build();

    }

    public Pessoa toPessoaEntity(PessoaPropostaDTO dto) {
        return Pessoa.builder()
                .cpf(dto.getCpf())
                .nome(dto.getNome())
                .idade(dto.getIdade())
                .sexo(dto.getSexo())
                .estadoCivil(dto.getEstadoCivil())
                .uf(dto.getUf())
                .dependentes(dto.getDependentes())
                .renda(dto.getRenda())
                .build();
    }

    public Proposta toPropostaEntity(PessoaPropostaDTO dto) {
        return Proposta.builder()
                .pessoa(toPessoaEntity(dto))
                .resultado(dto.getResultado())
                .motivoNegativa(dto.getMotivoNegativa())
                .faixa(dto.getFaixa())
                .build();
    }

    private PessoaPropostaDTO.PessoaPropostaDTOBuilder toDTOPessoaBuilder(Pessoa pessoa) {
        return PessoaPropostaDTO.builder()
                .cpf(pessoa.getCpf())
                .nome(pessoa.getNome())
                .idade(pessoa.getIdade())
                .sexo(pessoa.getSexo())
                .estadoCivil(pessoa.getEstadoCivil())
                .uf(pessoa.getUf())
                .dependentes(pessoa.getDependentes())
                .renda(pessoa.getRenda());
    }
}
