package io.github.arkanjoms.getcredit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pessoa implements Serializable {

    @Id
    private String cpf;

    private String nome;

    private Integer idade;

    @Enumerated(EnumType.STRING)
    private EnumSexo sexo;

    @Enumerated(EnumType.STRING)
    private EnumEstadoCivil estadoCivil;

    private String uf;

    private Integer dependentes;

    private BigDecimal renda;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
