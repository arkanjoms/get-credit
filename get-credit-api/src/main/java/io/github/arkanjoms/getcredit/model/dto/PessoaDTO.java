package io.github.arkanjoms.getcredit.model.dto;

import io.github.arkanjoms.getcredit.model.EnumMotivoNegativa;
import io.github.arkanjoms.getcredit.model.EnumResultado;
import io.github.arkanjoms.getcredit.model.Faixa;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "DTO para listagem de propostas")
public class PessoaDTO {

    @ApiModelProperty("Documento de identificação da Pessoa (CPF)")
    private String cpf;

    @ApiModelProperty("Nome do cliente")
    private String nome;

    @ApiModelProperty("UF do cliente")
    private String uf;

    @ApiModelProperty("Resultado da análise da proposta")
    private EnumResultado resultado;

    @ApiModelProperty("Motivo da negativa da proposta")
    private EnumMotivoNegativa motivoNegativa;

    @ApiModelProperty("Faixa de limite da proposta")
    private Faixa faixa;
}
