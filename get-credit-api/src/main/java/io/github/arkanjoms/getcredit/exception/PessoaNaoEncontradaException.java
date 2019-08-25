package io.github.arkanjoms.getcredit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pessoa não encontrada.")
public class PessoaNaoEncontradaException extends RuntimeException {
}
