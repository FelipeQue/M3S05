package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.services.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PacienteController.class)
@AutoConfigureMockMvc
class PacienteControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PacienteService pacienteService;

    @Test
    void salvarPaciente() throws Exception {

        when(pacienteService.salvarPaciente(any())).thenReturn(new PacienteResponseDTO(
                1L,
                "Nome de paciente",
                LocalDate.of(1980, 10, 10),
                "000.000.000-00",
                "(48) 99999-9999",
                "email@email.com"));

        mvc.perform(post("/pacientes")
                        .header("Authorization", "Token vai aqui")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "nome": "Nome de paciente",
                                "dataNascimento": "10/10/1980",
                                "cpf": "000.000.000-00",
                                "telefone": "(48) 99999-9999",
                                "email": "email@email.com",
                                "idEndereco": 1
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Nome de paciente"));
        verify(pacienteService).salvarPaciente(any());
    }

    @Test
    void listarPacientes() throws Exception {

        PacienteResponseDTO pacienteResponse = new PacienteResponseDTO(
                1L,
                "Nome de paciente",
                LocalDate.of(1980, 1, 1),
                "000.000.000-00",
                "(48) 99999-9999",
                "email@email.com"
        );

        when(pacienteService.listarPacientes()).thenReturn(List.of(pacienteResponse));

        mvc.perform(get("/pacientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].nome").value(pacienteResponse.getNome()));

        verify(pacienteService).listarPacientes();
    }

    @Test
    void search() throws Exception {
        when(pacienteService.buscarPaciente(any())).thenReturn(new PacienteResponseDTO(
                1L,
                "Nome de paciente",
                LocalDate.of(1980, 10, 10),
                "000.000.000-00",
                "(48) 99999-9999",
                "email@email.com"));

        mvc.perform(get("/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nome de paciente"));

        verify(pacienteService).buscarPaciente(any());
    }

    @Test
    void remove() throws Exception {
        mvc.perform(delete("/pacientes/1"))
                .andExpect(status().isNoContent());
        verify(pacienteService).removerPaciente(any());
    }

    @Test
    void update() throws Exception {
        when(pacienteService.atualizarPaciente(any(), any())).thenReturn(new PacienteResponseDTO(
                1L,
                "Nome de paciente",
                LocalDate.of(1980, 10, 10),
                "000.000.000-00",
                "(48) 99999-9999",
                "email@email.com"));

        mvc.perform(put("/pacientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "nome": "Nome de paciente",
                                "dataNascimento": "10/10/1980",
                                "cpf": "000.000.000-00",
                                "telefone": "(48) 99999-9999",
                                "email": "email@email.com"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nome de paciente"));
        verify(pacienteService).atualizarPaciente(any(), any());

    }
}