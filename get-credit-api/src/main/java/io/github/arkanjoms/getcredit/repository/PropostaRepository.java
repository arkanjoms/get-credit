package io.github.arkanjoms.getcredit.repository;

import io.github.arkanjoms.getcredit.model.Pessoa;
import io.github.arkanjoms.getcredit.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    void deleteByPessoa(Pessoa pessoa);

    Proposta findByPessoa(Pessoa pessoa);
}
