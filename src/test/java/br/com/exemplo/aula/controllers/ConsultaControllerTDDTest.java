package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.repositories.ConsultaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConsultaControllerTDDTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ConsultaRepository consultaRepository;

//    @Test
//    void buscarConsultaPorId() throws Exception {
//        mvc.perform(get("/consultas/1"))
//                .header("Authorization", "token")
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void alterarConsultaPorId() throws Exception {
//        mvc.perform(put("/consultas/1"))
//                .andExpect(status().isOk());
//    }

}