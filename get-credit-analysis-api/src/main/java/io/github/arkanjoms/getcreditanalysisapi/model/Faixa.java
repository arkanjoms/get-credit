package io.github.arkanjoms.getcreditanalysisapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Faixa limite da Proposta")
public class Faixa implements Serializable {

    @ApiModelProperty("Piso para a faixa de limite da proposta")
    private BigDecimal piso;

    @ApiModelProperty("Teto para a faixa de limite da proposta")
    private BigDecimal teto;
}
