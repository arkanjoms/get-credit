package io.github.arkanjoms.getcredit.service;

import io.github.arkanjoms.getcredit.exception.PessoaNaoEncontradaException;
import io.github.arkanjoms.getcredit.model.Pessoa;
import io.github.arkanjoms.getcredit.model.Proposta;
import io.github.arkanjoms.getcredit.model.dto.PessoaDTO;
import io.github.arkanjoms.getcredit.model.dto.PessoaPropostaCadastroDTO;
import io.github.arkanjoms.getcredit.model.dto.PessoaPropostaDTO;
import io.github.arkanjoms.getcredit.repository.PessoaRepository;
import io.github.arkanjoms.getcredit.transformer.PessoaPropostaCadastroTransformer;
import io.github.arkanjoms.getcredit.transformer.PessoaPropostaTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PropostaService propostaService;

    @Autowired
    private PessoaPropostaTransformer pessoaPropostaTransformer;

    @Autowired
    private GetCreditAnalysisClient getCreditAnalysisClient;

    @Autowired
    private PessoaPropostaCadastroTransformer pessoaPropostaCadastroTransformer;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void salvar(PessoaPropostaCadastroDTO pessoaDTO) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoaPropostaCadastroTransformer.toPessoaEntity(pessoaDTO));
        PessoaPropostaDTO propostaDTO = getCreditAnalysisClient.analisarCredito(pessoaPropostaTransformer.toDTO(pessoaSalva));

        Proposta propostaEdicao = pessoaPropostaTransformer.toPropostaEntity(propostaDTO);
        Proposta proposta = propostaService.buscarPropostaPorPessoa(pessoaSalva);
        if (Objects.nonNull(proposta)) {
            propostaEdicao.setId(proposta.getId());
        }

        propostaService.salvar(propostaEdicao);
    }

    public PessoaPropostaDTO buscarPorCpf(String cpf) {
        Pessoa pessoa = pessoaRepository.findById(cpf).orElseThrow(PessoaNaoEncontradaException::new);
        Proposta proposta = propostaService.buscarPropostaPorPessoa(pessoa);
        return pessoaPropostaTransformer.toDTO(pessoa, proposta);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void remover(String cpf) {
        Pessoa pessoa = pessoaRepository.findById(cpf).orElseThrow(PessoaNaoEncontradaException::new);

        propostaService.removerPorPessoa(pessoa);
        pessoaRepository.delete(Pessoa.builder().cpf(cpf).build());
    }

    public List<PessoaDTO> filtrar(String cpf) {
        if (StringUtils.isEmpty(cpf)) {
            return pessoaRepository.findAllAsDTO();
        }
        return pessoaRepository.findAllByCpfLikeAsDTO(cpf);
    }
}
