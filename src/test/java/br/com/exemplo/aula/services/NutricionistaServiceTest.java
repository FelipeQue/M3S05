package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.NutricionistaRequestDTO;
import br.com.exemplo.aula.controllers.dto.NutricionistaResponseDTO;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.repositories.NutricionistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NutricionistaServiceTest {

    @Mock
    NutricionistaRepository nutricionistaRepository;

    @InjectMocks
    NutricionistaService nutricionistaService;

    Nutricionista nutricionista;

    @BeforeEach
    public void setup(){
        nutricionista = new Nutricionista(
                1L,
        "Nome de nutricionista",
        "Número de matrícula",
        1,
        "Número de CRN",
        "Especialidade",
                new HashSet<String>(Set.of("Certificação 1", "Certificação 2"))
        );

    }

    @Test
    @DisplayName("Solicita lista de nutricionistas retorna uma lista de DTO de resposta.")
    void listarNutricionistas() {

        // Setup
        List<Nutricionista> nutricionistas = new ArrayList<Nutricionista>();
        nutricionistas.add(nutricionista);

        // Given
        when(nutricionistaRepository.findAll()).thenReturn(nutricionistas);

        // When
        var resultado = nutricionistaService.listarNutricionistas();

        // Then
        assertNotNull(resultado);
        assertEquals(nutricionistas.get(0).getId(), resultado.get(0).getId());

        verify(nutricionistaRepository).findAll();
    }

    @Test
    @DisplayName("Busca nutricionista por Id e retorna um DTO de resposta.")
    void buscarNutricionista() {

        // Given
        when(nutricionistaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(nutricionista));

        // When
        NutricionistaResponseDTO resultado = nutricionistaService.buscarNutricionista(1L);

        // Then
        assertNotNull(resultado);
        assertEquals(nutricionista.getId(), resultado.getId());
    }

    @Test
    void salvarNutricionista() {

        NutricionistaRequestDTO request = new NutricionistaRequestDTO(
                "Nome de nutricionista",
                "Número de matrícula",
                1,
                1L,
                "Número de CRN",
                "Especialidade"
        );

        // Given
        when(nutricionistaRepository.save(Mockito.any(Nutricionista.class))).thenReturn(nutricionista);

        // When
        var resultado = nutricionistaService.salvarNutricionista(request);

        // Then
        assertNotNull(resultado);
        assertEquals(request.getNome(), resultado.getNome());

        verify(nutricionistaRepository).save(Mockito.any(Nutricionista.class));

    }

    @Test
    void atualizarNutricionista() {

        NutricionistaRequestDTO request = new NutricionistaRequestDTO(
                "Nome de nutricionista",
                "Número de matrícula",
                1,
                1L,
                "Número de CRN",
                "Especialidade"
        );

        // Given
        when(nutricionistaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(nutricionista));
        when(nutricionistaRepository.save(Mockito.any(Nutricionista.class))).thenReturn(nutricionista);

        // When
        var resultado = nutricionistaService.atualizarNutricionista(1L, request);

        // Then
        assertNotNull(resultado);
        assertEquals(request.getNome(), resultado.getNome());

        verify(nutricionistaRepository).findById(anyLong());
        verify(nutricionistaRepository).save(Mockito.any(Nutricionista.class));

    }

    @Test
    void removerNutricionista() {

    }

    @Test
    void adicionarAnoExperiencia() {
    }

    @Test
    void adicionarCertificacao() {
    }
}
