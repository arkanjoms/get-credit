package io.github.arkanjoms.getcredit.model.dto;

import io.github.arkanjoms.getcredit.model.EnumEstadoCivil;
import io.github.arkanjoms.getcredit.model.EnumMotivoNegativa;
import io.github.arkanjoms.getcredit.model.EnumResultado;
import io.github.arkanjoms.getcredit.model.EnumSexo;
import io.github.arkanjoms.getcredit.model.Faixa;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "DTO para operações com propostas")
public class PessoaPropostaDTO implements Serializable {

    @ApiModelProperty("Documento de identificação da Pessoa (CPF)")
    private String cpf;

    @ApiModelProperty("Nome do cliente")
    private String nome;

    @ApiModelProperty("Idade do cliente")
    private Integer idade;

    @ApiModelProperty("Sexo do cliente")
    private EnumSexo sexo;

    @ApiModelProperty(notes = "Estado Civil do cliente")
    private EnumEstadoCivil estadoCivil;

    @ApiModelProperty("UF do cliente")
    private String uf;

    @ApiModelProperty("Quantidade de dependentes do cliente")
    private Integer dependentes;

    @ApiModelProperty("Renda do cliente")
    private BigDecimal renda;

    @ApiModelProperty("Resultado da análise da proposta")
    private EnumResultado resultado;

    @ApiModelProperty("Motivo da negativa da proposta")
    private EnumMotivoNegativa motivoNegativa;

    @ApiModelProperty("Faixa limite da Proposta")
    private Faixa faixa;
}
