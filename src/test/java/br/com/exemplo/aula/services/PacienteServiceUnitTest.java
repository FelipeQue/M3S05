package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.PacienteRequestDTO;
import br.com.exemplo.aula.entities.Paciente;
import br.com.exemplo.aula.repositories.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceUnitTest {

    @Mock
    PacienteRepository pacienteRepository;

    @InjectMocks
    PacienteService pacienteService;

    Paciente paciente;

    @BeforeEach
    public void setup(){
        paciente = new Paciente(
                1L,
                "Nome de paciente",
                LocalDate.of(1980, 1, 1),
                "000.000.000-00",
                "(48) 99999-9999",
                "email@email.com"
        );
    }

    @Test
    @DisplayName("Deve retornar lista de pacientes")
    void deveListarPacientes() {
        // Setup
        List<Paciente> pacientes = new ArrayList<Paciente>();
        pacientes.add(paciente);
        // Given
        when(pacienteRepository.findAll()).thenReturn(pacientes);
        // When
        var resultado = pacienteService.listarPacientes();
        // Then
        assertNotNull(resultado);
        assertEquals(pacientes.get(0).getId(), resultado.get(0).getId());
        assertEquals(pacientes.get(0).getNome(), resultado.get(0).getNome());
        verify(pacienteRepository).findAll();
    }

    @Test
    @DisplayName("Deve retornar paciente por id")
    void deveBuscarPaciente() {
        // Given
        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(paciente));
        // When
        var resultado = pacienteService.buscarPaciente(1L);
        // Then
        assertNotNull(resultado);
        assertEquals(paciente.getNome(), resultado.getNome());
        verify(pacienteRepository).findById(anyLong());
    }

    @Test
    @DisplayName("Deve salvar paciente e retornar dados salvos")
    void deveSalvarPaciente() {
        PacienteRequestDTO request = new PacienteRequestDTO(
                "Nome de paciente",
                LocalDate.of(1980, 1, 1),
                "000.000.000-00",
                "(48) 99999-9999",
                "email@email.com",
                1L);
        // Given
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);
        // When
        var resultado = pacienteService.salvarPaciente(request);
        // Then
        assertNotNull(resultado);
        assertEquals(request.getNome(), resultado.getNome());
        verify(pacienteRepository).save(any(Paciente.class));
    }

    @Test
    @DisplayName("Deve atualizar paciente existente via id e retornar dados atualizados")
    void deveAtualizarPaciente() {
        PacienteRequestDTO request = new PacienteRequestDTO(
                "Nome de paciente",
                LocalDate.of(1980, 1, 1),
                "000.000.000-00",
                "(48) 99999-9999",
                "email@email.com",
                1L);
        // Given
        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(paciente));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);
        // When
        var resultado = pacienteService.atualizarPaciente(1L, request);
        // Then
        assertNotNull(resultado);
        assertEquals(request.getNome(), resultado.getNome());
        verify(pacienteRepository).findById(anyLong());
        verify(pacienteRepository).save(any(Paciente.class));
    }

    @Test
    @DisplayName("Deve remover paciente via id")
    void deveRemoverPaciente() {
        // Given
        doNothing().when(pacienteRepository).deleteById(anyLong());
        // Then
        assertDoesNotThrow(
                () -> pacienteService.removerPaciente(1L)
        );
        verify(pacienteRepository).deleteById(anyLong());
    }
}