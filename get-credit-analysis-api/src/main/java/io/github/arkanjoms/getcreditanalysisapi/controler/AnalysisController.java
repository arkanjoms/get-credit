package io.github.arkanjoms.getcreditanalysisapi.controler;

import io.github.arkanjoms.getcreditanalysisapi.model.PessoaPropostaDTO;
import io.github.arkanjoms.getcreditanalysisapi.service.AvaliarCreditoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("API Rest para realizar análise de proposta de crédito")
@RestController
@RequestMapping
public class AnalysisController {

    private AvaliarCreditoService avaliarCreditoService;

    public AnalysisController(AvaliarCreditoService avaliarCreditoService) {
        this.avaliarCreditoService = avaliarCreditoService;
    }

    @ApiOperation("Realiza avaliação de crédito para a proposta informada.")
    @PostMapping(value = "/avaliar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PessoaPropostaDTO> analisarCredito(@RequestBody PessoaPropostaDTO proposta) {
        return ResponseEntity.ok(avaliarCreditoService.avaliarCredito(proposta));
    }
}
