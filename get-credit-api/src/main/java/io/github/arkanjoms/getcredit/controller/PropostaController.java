package io.github.arkanjoms.getcredit.controller;

import io.github.arkanjoms.getcredit.model.dto.PessoaDTO;
import io.github.arkanjoms.getcredit.model.dto.PessoaPropostaCadastroDTO;
import io.github.arkanjoms.getcredit.model.dto.PessoaPropostaDTO;
import io.github.arkanjoms.getcredit.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/propostas")
@Api(value = "Api REST para propostas")
public class PropostaController {

    private PessoaService pessoaService;

    public PropostaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @ApiOperation("Buscar propostas")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Nenhuma proposta encontrada")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PessoaDTO>> buscarPessoas(@PathParam("cpf") String cpf) {
        List<PessoaDTO> pessoas = pessoaService.filtrar(cpf);
        if (CollectionUtils.isEmpty(pessoas)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(pessoas);
    }

    @ApiOperation("Recuperar cliente por CPF.")
    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PessoaPropostaDTO> buscarPessoa(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(pessoaService.buscarPorCpf(cpf));
    }

    @ApiOperation("Cadastra uma nova proposta e realiza avaliação de crédito")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity novo(@RequestBody PessoaPropostaCadastroDTO pessoa) {
        pessoaService.salvar(pessoa);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Edita um cadastro de um cliente")
    @PutMapping(value = "/{cpf}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity editar(@PathVariable("cpf") Long cpf, @RequestBody PessoaPropostaCadastroDTO pessoa) {
        pessoaService.salvar(pessoa);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Remove o cadastro de um cliente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Proposta removida"),
            @ApiResponse(code = 404, message = "Proposta não encontrada para o CPF informado")
    })
    @DeleteMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity remover(@PathVariable("cpf") String cpf) {
        pessoaService.remover(cpf);
        return ResponseEntity.ok().build();
    }
}
