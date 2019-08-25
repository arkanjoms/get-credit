package io.github.arkanjoms.getcreditanalysisapi.service;

import io.github.arkanjoms.getcreditanalysisapi.exception.RendaBaixaException;
import io.github.arkanjoms.getcreditanalysisapi.exception.ReprovadaPoliticaCreditoException;
import io.github.arkanjoms.getcreditanalysisapi.model.EnumMotivoNegativa;
import io.github.arkanjoms.getcreditanalysisapi.model.EnumResultado;
import io.github.arkanjoms.getcreditanalysisapi.model.PessoaPropostaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliarCreditoService {

    private DependentesAvaliacao dependentesAvaliacao;

    private EstadoCivilAvaliacao estadoCivilAvaliacao;

    private IdadeAvaliacao idadeAvaliacao;

    private SalarioAvaliacao salarioAvaliacao;

    private SexoAvaliacao sexoAvaliacao;

    private CalcularFaixa calcularFaixa;

    public PessoaPropostaDTO avaliarCredito(PessoaPropostaDTO proposta) {

        try {
            int peso = calcularPeso(proposta);
            proposta.setFaixa(calcularFaixa.calcular(proposta, peso));
            proposta.setResultado(EnumResultado.APROVADO);
        } catch (RendaBaixaException e) {
            proposta.setResultado(EnumResultado.NEGADO);
            proposta.setMotivoNegativa(EnumMotivoNegativa.RENDA_BAIXA);
        } catch (ReprovadaPoliticaCreditoException e) {
            proposta.setResultado(EnumResultado.NEGADO);
            proposta.setMotivoNegativa(EnumMotivoNegativa.POLITICA_CREDITO);
        }

        return proposta;
    }

    private int calcularPeso(PessoaPropostaDTO proposta) {
        int peso = 0;
        peso += salarioAvaliacao.avaliar(proposta);
        peso += idadeAvaliacao.avaliar(proposta);
        peso += sexoAvaliacao.avaliar(proposta);
        peso += estadoCivilAvaliacao.avaliar(proposta);
        peso += dependentesAvaliacao.avaliar(proposta);

        return peso;
    }

    @Autowired
    public void setDependentesAvaliacao(DependentesAvaliacao dependentesAvaliacao) {
        this.dependentesAvaliacao = dependentesAvaliacao;
    }

    @Autowired
    public void setEstadoCivilAvaliacao(EstadoCivilAvaliacao estadoCivilAvaliacao) {
        this.estadoCivilAvaliacao = estadoCivilAvaliacao;
    }

    @Autowired
    public void setIdadeAvaliacao(IdadeAvaliacao idadeAvaliacao) {
        this.idadeAvaliacao = idadeAvaliacao;
    }

    @Autowired
    public void setSalarioAvaliacao(SalarioAvaliacao salarioAvaliacao) {
        this.salarioAvaliacao = salarioAvaliacao;
    }

    @Autowired
    public void setSexoAvaliacao(SexoAvaliacao sexoAvaliacao) {
        this.sexoAvaliacao = sexoAvaliacao;
    }

    @Autowired
    public void setCalcularFaixa(CalcularFaixa calcularFaixa) {
        this.calcularFaixa = calcularFaixa;
    }
}
