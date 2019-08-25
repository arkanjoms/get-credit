package io.github.arkanjoms.getcreditanalysisapi.service;

import io.github.arkanjoms.getcreditanalysisapi.model.EnumEstadoCivil;
import io.github.arkanjoms.getcreditanalysisapi.model.EnumMotivoNegativa;
import io.github.arkanjoms.getcreditanalysisapi.model.EnumResultado;
import io.github.arkanjoms.getcreditanalysisapi.model.EnumSexo;
import io.github.arkanjoms.getcreditanalysisapi.model.PessoaPropostaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AvaliarCreditoServiceTest {

    private AvaliarCreditoService avaliarCreditoService;

    @BeforeEach
    void setUp() {
        this.avaliarCreditoService = new AvaliarCreditoService();
        this.avaliarCreditoService.setDependentesAvaliacao(new DependentesAvaliacao());
        this.avaliarCreditoService.setEstadoCivilAvaliacao(new EstadoCivilAvaliacao());
        this.avaliarCreditoService.setIdadeAvaliacao(new IdadeAvaliacao());
        this.avaliarCreditoService.setSalarioAvaliacao(new SalarioAvaliacao());
        this.avaliarCreditoService.setSexoAvaliacao(new SexoAvaliacao());
        this.avaliarCreditoService.setCalcularFaixa(new CalcularFaixa());
    }

    @Test
    void lucas_aprovado_500_1000() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("65529049087")
                .nome("Lucas")
                .idade(28)
                .sexo(EnumSexo.M)
                .estadoCivil(EnumEstadoCivil.SOLTEIRO)
                .uf("SC")
                .dependentes(0)
                .renda(new BigDecimal("2500"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Lucas", resultado.getNome());
        assertEquals(EnumResultado.APROVADO, resultado.getResultado());
        assertEquals(new BigDecimal("500"), resultado.getFaixa().getPiso());
        assertEquals(new BigDecimal("1000"), resultado.getFaixa().getTeto());
    }

    @Test
    void ana_aprovado_100_500() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("31997850001")
                .nome("Ana")
                .idade(17)
                .sexo(EnumSexo.F)
                .estadoCivil(EnumEstadoCivil.SOLTEIRO)
                .uf("SP")
                .dependentes(0)
                .renda(new BigDecimal("1000"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Ana", resultado.getNome());
        assertEquals(EnumResultado.APROVADO, resultado.getResultado());
        assertEquals(new BigDecimal("100"), resultado.getFaixa().getPiso());
        assertEquals(new BigDecimal("500"), resultado.getFaixa().getTeto());
    }

    @Test
    void pedro_aprovado_1500_2000() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("37624532093")
                .nome("Pedro")
                .idade(68)
                .sexo(EnumSexo.M)
                .estadoCivil(EnumEstadoCivil.CASADO)
                .uf("SC")
                .dependentes(3)
                .renda(new BigDecimal("8000"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Pedro", resultado.getNome());
        assertEquals(EnumResultado.APROVADO, resultado.getResultado());
        assertEquals(new BigDecimal("1500"), resultado.getFaixa().getPiso());
        assertEquals(new BigDecimal("2000"), resultado.getFaixa().getTeto());
    }

    @Test
    void paula_aprovado_1000_1500() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("74661510003")
                .nome("Paula")
                .idade(61)
                .sexo(EnumSexo.F)
                .estadoCivil(EnumEstadoCivil.CASADO)
                .uf("RJ")
                .dependentes(3)
                .renda(new BigDecimal("5000"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Paula", resultado.getNome());
        assertEquals(EnumResultado.APROVADO, resultado.getResultado());
        assertEquals(new BigDecimal("1000"), resultado.getFaixa().getPiso());
        assertEquals(new BigDecimal("1500"), resultado.getFaixa().getTeto());
    }

    @Test
    void joao_negado_politica_credito() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("97600324067")
                .nome("João")
                .idade(56)
                .sexo(EnumSexo.M)
                .estadoCivil(EnumEstadoCivil.DIVORCIADO)
                .uf("RJ")
                .dependentes(2)
                .renda(new BigDecimal("2000"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("João", resultado.getNome());
        assertEquals(EnumResultado.NEGADO, resultado.getResultado());
        assertEquals(EnumMotivoNegativa.POLITICA_CREDITO, resultado.getMotivoNegativa());
        assertNull(resultado.getFaixa());
    }

    @Test
    void maria_negado_politica_credito() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("77770375010")
                .nome("Maria")
                .idade(45)
                .sexo(EnumSexo.F)
                .estadoCivil(EnumEstadoCivil.DIVORCIADO)
                .uf("SP")
                .dependentes(1)
                .renda(new BigDecimal("2000"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Maria", resultado.getNome());
        assertEquals(EnumResultado.NEGADO, resultado.getResultado());
        assertEquals(EnumMotivoNegativa.POLITICA_CREDITO, resultado.getMotivoNegativa());
        assertNull(resultado.getFaixa());
    }

    @Test
    void jose_aprovado_superior_2000() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("58467756080")
                .nome("José")
                .idade(30)
                .sexo(EnumSexo.M)
                .estadoCivil(EnumEstadoCivil.CASADO)
                .uf("MA")
                .dependentes(2)
                .renda(new BigDecimal("8000"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("José", resultado.getNome());
        assertEquals(EnumResultado.APROVADO, resultado.getResultado());
        assertEquals(new BigDecimal("2000"), resultado.getFaixa().getPiso());
        assertNull(resultado.getFaixa().getTeto());
    }

    @Test
    void dinae_aprovado_superior_2000() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("69448050062")
                .nome("Dinae")
                .idade(33)
                .sexo(EnumSexo.F)
                .estadoCivil(EnumEstadoCivil.CASADO)
                .uf("SP")
                .dependentes(1)
                .renda(new BigDecimal("10000"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Dinae", resultado.getNome());
        assertEquals(EnumResultado.APROVADO, resultado.getResultado());
        assertEquals(new BigDecimal("2000"), resultado.getFaixa().getPiso());
        assertNull(resultado.getFaixa().getTeto());
    }

    @Test
    void marcos_negado_renda_baixa() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("08952373006")
                .nome("Marcos")
                .idade(19)
                .sexo(EnumSexo.M)
                .estadoCivil(EnumEstadoCivil.SOLTEIRO)
                .uf("SC")
                .dependentes(1)
                .renda(new BigDecimal("400"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Marcos", resultado.getNome());
        assertEquals(EnumResultado.NEGADO, resultado.getResultado());
        assertEquals(EnumMotivoNegativa.RENDA_BAIXA, resultado.getMotivoNegativa());
        assertNull(resultado.getFaixa());
    }

    @Test
    void suzan_negado_politica_credito() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("01729990061")
                .nome("Suzan")
                .idade(63)
                .sexo(EnumSexo.F)
                .estadoCivil(EnumEstadoCivil.VIUVO)
                .uf("MA")
                .dependentes(3)
                .renda(new BigDecimal("1500"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Suzan", resultado.getNome());
        assertEquals(EnumResultado.NEGADO, resultado.getResultado());
        assertEquals(EnumMotivoNegativa.POLITICA_CREDITO, resultado.getMotivoNegativa());
        assertNull(resultado.getFaixa());
    }

    @Test
    void luci_aprovado_100_500() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("91988874084")
                .nome("Luci")
                .idade(28)
                .sexo(EnumSexo.F)
                .estadoCivil(EnumEstadoCivil.SOLTEIRO)
                .uf("SC")
                .dependentes(2)
                .renda(new BigDecimal("2500"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Luci", resultado.getNome());
        assertEquals(EnumResultado.APROVADO, resultado.getResultado());
        assertEquals(new BigDecimal("100"), resultado.getFaixa().getPiso());
        assertEquals(new BigDecimal("500"), resultado.getFaixa().getTeto());
    }

    @Test
    void roberto_negado_renda_baixa() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("34300658013")
                .nome("Roberto")
                .idade(16)
                .sexo(EnumSexo.M)
                .estadoCivil(EnumEstadoCivil.SOLTEIRO)
                .uf("SP")
                .dependentes(0)
                .renda(new BigDecimal("500"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Roberto", resultado.getNome());
        assertEquals(EnumResultado.NEGADO, resultado.getResultado());
        assertEquals(EnumMotivoNegativa.RENDA_BAIXA, resultado.getMotivoNegativa());
        assertNull(resultado.getFaixa());
    }

    @Test
    void bruno_aprovado_1000_1500() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("77169900041")
                .nome("Bruno")
                .idade(30)
                .sexo(EnumSexo.M)
                .estadoCivil(EnumEstadoCivil.CASADO)
                .uf("MA")
                .dependentes(5)
                .renda(new BigDecimal("8000"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Bruno", resultado.getNome());
        assertEquals(EnumResultado.APROVADO, resultado.getResultado());
        assertEquals(new BigDecimal("1000"), resultado.getFaixa().getPiso());
        assertEquals(new BigDecimal("1500"), resultado.getFaixa().getTeto());
    }

    @Test
    void ariel_aprovado_superior_2000() {
        PessoaPropostaDTO pessoa = PessoaPropostaDTO.builder()
                .cpf("77334421094")
                .nome("Ariel")
                .idade(33)
                .sexo(EnumSexo.F)
                .estadoCivil(EnumEstadoCivil.VIUVO)
                .uf("SP")
                .dependentes(0)
                .renda(new BigDecimal("10000"))
                .build();
        PessoaPropostaDTO resultado = avaliarCreditoService.avaliarCredito(pessoa);

        assertEquals("Ariel", resultado.getNome());
        assertEquals(EnumResultado.APROVADO, resultado.getResultado());
        assertEquals(new BigDecimal("2000"), resultado.getFaixa().getPiso());
        assertNull(resultado.getFaixa().getTeto());
    }
}
