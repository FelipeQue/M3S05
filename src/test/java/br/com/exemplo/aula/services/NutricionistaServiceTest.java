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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
    @DisplayName("Deve retornar lista de nutricionistas")
    void deveListarNutricionistas() {

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
    @DisplayName("Deve retornar nutricionista por id")
    void deveBuscarNutricionista() {

        // Given
        when(nutricionistaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(nutricionista));

        // When
        NutricionistaResponseDTO resultado = nutricionistaService.buscarNutricionista(1L);

        // Then
        assertNotNull(resultado);
        assertEquals(nutricionista.getId(), resultado.getId());
        verify(nutricionistaRepository).findById(anyLong());
    }

    @Test
    @DisplayName("Deve salvar nutricionista e retornar dados salvos")
    void deveSalvarNutricionista() {

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
    @DisplayName("Deve atualizar nutricionista existente via id e retornar dados atualizados")
    void deveAtualizarNutricionista() {

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
    @DisplayName("Deve retornar nada ao solicitar remoção de nutricionista por id")
    void deveRemoverNutricionista() {

        // Given
        doNothing().when(nutricionistaRepository).deleteById(anyLong());

        // Then
        assertDoesNotThrow(
                () -> nutricionistaService.removerNutricionista(1L)
        );

        verify(nutricionistaRepository).deleteById(anyLong());
    }

    @Test
    @DisplayName("Deve adicionar 1 ano de experiência a nutricionista via id")
    void deveAdicionarAnoExperiencia() {

        // Given
        when(nutricionistaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(nutricionista));

        // When
        nutricionistaService.adicionarAnoExperiencia(anyLong());

        // Then
        assertEquals(nutricionista.getTempoExperiencia(), 2);
        verify(nutricionistaRepository).findById(anyLong());

    }

    @Test
    @DisplayName("Deve adicionar certificação a nutricionista")
    void deveAdicionarCertificacao() {

        // Given
        when(nutricionistaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(nutricionista));

        // When
        nutricionistaService.adicionarCertificacao("Certificação 3", 1L);

        // Then
        assertTrue(nutricionista.getCertificacoes().contains("Certificação 3"));
        verify(nutricionistaRepository).findById(anyLong());

    }
}
