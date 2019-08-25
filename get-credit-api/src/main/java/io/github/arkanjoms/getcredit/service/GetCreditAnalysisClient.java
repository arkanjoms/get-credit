package io.github.arkanjoms.getcredit.service;

import io.github.arkanjoms.getcredit.model.dto.PessoaPropostaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${get-credit-analysis-api.url}", name = "GetCreditAnalysisApi")
public interface GetCreditAnalysisClient {

    @PostMapping("/avaliar")
    PessoaPropostaDTO analisarCredito(PessoaPropostaDTO proposta);
}
