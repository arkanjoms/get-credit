package io.github.arkanjoms.getcredit.service;

import io.github.arkanjoms.getcredit.model.Pessoa;
import io.github.arkanjoms.getcredit.model.Proposta;
import io.github.arkanjoms.getcredit.repository.PropostaRepository;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    private PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    public void salvar(Proposta proposta) {
        propostaRepository.save(proposta);
    }

    public void removerPorPessoa(Pessoa pessoa) {
        propostaRepository.deleteByPessoa(pessoa);
    }

    public Proposta buscarPropostaPorPessoa(Pessoa pessoa) {
        return propostaRepository.findByPessoa(pessoa);
    }
}
