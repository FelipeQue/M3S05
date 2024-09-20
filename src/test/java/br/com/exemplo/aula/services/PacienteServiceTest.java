package br.com.exemplo.aula.services;

import br.com.exemplo.aula.repositories.NutricionistaRepository;
import br.com.exemplo.aula.repositories.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    PacienteRepository pacienteRepository;

    @InjectMocks
    PacienteService pacienteService;

    @Test
    void listarPacientes() {
    }

    @Test
    void buscarPaciente() {
    }

    @Test
    void salvarPaciente() {
    }

    @Test
    void atualizarPaciente() {
    }

    @Test
    void removerPaciente() {
    }
}