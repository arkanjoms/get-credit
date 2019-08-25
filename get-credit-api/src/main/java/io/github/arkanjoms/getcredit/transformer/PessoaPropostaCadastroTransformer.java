package io.github.arkanjoms.getcredit.transformer;

import io.github.arkanjoms.getcredit.model.Pessoa;
import io.github.arkanjoms.getcredit.model.dto.PessoaPropostaCadastroDTO;
import org.springframework.stereotype.Component;

@Component
public class PessoaPropostaCadastroTransformer {

    public PessoaPropostaCadastroDTO toDTO(Pessoa pessoa) {
        return PessoaPropostaCadastroDTO.builder()
                .cpf(pessoa.getCpf())
                .nome(pessoa.getNome())
                .idade(pessoa.getIdade())
                .sexo(pessoa.getSexo())
                .estadoCivil(pessoa.getEstadoCivil())
                .uf(pessoa.getUf())
                .dependentes(pessoa.getDependentes())
                .renda(pessoa.getRenda())
                .build();
    }

    public Pessoa toPessoaEntity(PessoaPropostaCadastroDTO dto) {
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
}
