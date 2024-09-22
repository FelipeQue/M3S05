package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.entities.Consulta;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.entities.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Exercício 7 - Teste de integração
@AutoConfigureMockMvc
@ActiveProfiles("Test")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsultaControllerTest {

    @Autowired
    MockMvc mvc;

    Consulta consulta;
    Paciente paciente;
    Nutricionista nutricionista;

    @BeforeEach
    void setUp() {

        paciente = new Paciente();
//        paciente.setNome("Nome de paciente");
//        paciente.setCpf("000.000.000-00");
//        paciente.setDataNascimento(LocalDate.of(1980, 10, 10));
//        paciente.setEmail("paciente@email.com");
//        paciente.setTelefone("(48) 99999-9999");

        nutricionista = new Nutricionista();
//        nutricionista.setNome("Nome de nutricionista");
//        nutricionista.setMatricula("Número de matrícula");
//        nutricionista.setCrn("Número de CRN");
//        nutricionista.setEspecialidade("Especialidade");
//        nutricionista.setTempoExperiencia(1);

        consulta = new Consulta(
                1L,
                nutricionista,
                paciente,
                LocalDate.of(2023, 10, 10),
                "Texto de observações."
        );
    }

    @Test
    void listarConsultas() throws Exception {
        mvc.perform(get("/consultas"))
                .andExpect(status().isOk());
    }

    @Test
    void salvarConsulta() throws Exception {

        mvc.perform(post("/nutricionistas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "nome": "Nome de nutricionista",
                                "matricula": "Número de matrícula",
                                "tempoExperiencia": 1,
                                "crn": "Número de CRN",
                                "especialidade": "Especialidade"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Nome de nutricionista"));
    }


//    @Test
//    void salvarConsulta() throws Exception {
//
//        mvc.perform(post("/nutricionistas")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("""
//                        {
//                        "nome": "Nome de nutricionista",
//                        "matricula": "Número de matrícula",
//                        "tempoExperiencia": 1,
//                        "crn": "Número de CRN",
//                        "especialidade": "Especialidade"
//                        }
//                        """))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.nome").value("Nome de nutricionista"));
//
//        mvc.perform(post("/pacientes")
//                .contentType("application/json")
//                .content("""
//                        {
//                        "nome": "Nome de paciente",
//                        "dataNascimento": "10/10/1980",
//                        "cpf": "000.000.000-00",
//                        "telefone": "(48) 99999-9999",
//                        "email": "paciente@email.com",
//                        """))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.nome").value("Nome de paciente"));
//
//        mvc.perform(post("/consultas")
//                .contentType("application/json")
//                .content("""
//                        {
//                        "idNutricionista": 1,
//                        "idPaciente": 1,
//                        "dataConsulta": "10/10/2023",
//                        "observacoes": "Texto de observações."
//                        }
//                        """))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.observacoes").value("Texto de observações."));
//
//}

//    @Test
//    void buscarConsulta() throws Exception {
//        mvc.perform(post("/consultas")
//                        .header("Authorization", "token")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {
//                                    "idNutricionista": 1,
//                                    "idPaciente": 1,
//                                    "data": "10/10/2023",
//                                    "observacoes": "Texto de observações."
//                                }"""))
//                .andExpect(status().isOk());
//
//        mvc.perform(get("/consultas/1")
//                        .header("Authorization", "token"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(consulta.getId()));
//
//    }

@Test
void atualizarConsulta() {
}

@Test
void deletarConsulta() {
}
}