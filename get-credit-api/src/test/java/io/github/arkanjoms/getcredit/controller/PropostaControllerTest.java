package io.github.arkanjoms.getcredit.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.arkanjoms.getcredit.model.EnumResultado;
import io.github.arkanjoms.getcredit.model.dto.PessoaDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class PropostaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Type pessoaDtoListType;

    private Gson gson;

    PropostaControllerTest() {
        pessoaDtoListType = new TypeToken<ArrayList<PessoaDTO>>() {
        }.getType();
        gson = new GsonBuilder().create();
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("delete from proposta");
        jdbcTemplate.execute("delete from pessoa");
    }

    @Test
    void buscarPessoas_lista_vazia() throws Exception {
        MvcResult result = mockMvc.perform(get("/propostas")).andReturn();

        List<PessoaDTO> dtos = gson.fromJson(result.getResponse().getContentAsString(), pessoaDtoListType);
        assertEquals(204, result.getResponse().getStatus());
        assertNull(dtos);
    }

    @Sql("classpath:db/insert-pessoas.sql")
    @Test
    void buscarPessoas_2_propostas() throws Exception {
        MvcResult result = mockMvc.perform(get("/propostas")).andReturn();

        List<PessoaDTO> dtos = gson.fromJson(result.getResponse().getContentAsString(), pessoaDtoListType);
        assertEquals(200, result.getResponse().getStatus());
        assertNotNull(dtos);
        assertEquals(2, dtos.size());
    }

    @Sql("classpath:db/insert-pessoas.sql")
    @Test
    void buscarPessoa_existente_por_cpf() throws Exception {
        String cpf = "65529049087";
        MvcResult result = mockMvc.perform(get("/propostas/" + cpf)).andReturn();

        PessoaDTO dto = gson.fromJson(result.getResponse().getContentAsString(), PessoaDTO.class);
        assertEquals(200, result.getResponse().getStatus());
        assertNotNull(dto);
        assertEquals(cpf, dto.getCpf());
        assertEquals("Lucas", dto.getNome());
        assertEquals(EnumResultado.APROVADO, dto.getResultado());
    }

    @Sql("classpath:db/insert-pessoas.sql")
    @Test
    void buscarPessoa_inexistente() throws Exception {
        String cpf = "00000000000";
        MvcResult result = mockMvc.perform(get("/propostas/" + cpf)).andReturn();

        PessoaDTO dto = gson.fromJson(result.getResponse().getContentAsString(), PessoaDTO.class);
        assertEquals(404, result.getResponse().getStatus());
        assertNull(dto);
    }
}
