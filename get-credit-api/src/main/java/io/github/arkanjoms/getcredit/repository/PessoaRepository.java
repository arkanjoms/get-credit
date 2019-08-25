package io.github.arkanjoms.getcredit.repository;

import io.github.arkanjoms.getcredit.model.Pessoa;
import io.github.arkanjoms.getcredit.model.dto.PessoaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {

    Pessoa findByCpf(String cpf);

    @Query("SELECT new io.github.arkanjoms.getcredit.model.dto.PessoaDTO(p.cpf,p.nome,p.uf,pr.resultado,pr.motivoNegativa,pr.faixa) FROM Pessoa p LEFT JOIN Proposta pr on pr.pessoa = p")
    List<PessoaDTO> findAllAsDTO();

    @Query("SELECT new io.github.arkanjoms.getcredit.model.dto.PessoaDTO(p.cpf,p.nome,p.uf,pr.resultado,pr.motivoNegativa,pr.faixa) FROM Pessoa p LEFT JOIN Proposta pr on pr.pessoa = p WHERE p.cpf like :cpf")
    List<PessoaDTO> findAllByCpfLikeAsDTO(String cpf);
}
