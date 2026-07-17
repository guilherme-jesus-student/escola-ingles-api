package com.escola.escolainglesapi.service;

import dto.ProfessorDTO;
import dto.ProfessorResponseDTO;
import com.escola.escolainglesapi.model.Professor;
import com.escola.escolainglesapi.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfessorServiceTest {

    @Mock
    private ProfessorRepository repository;

    @InjectMocks
    private ProfessorService service;

    private Professor professor;
    private ProfessorDTO professorDTO;

    @BeforeEach
    void setUp() {
        professor = new Professor(1L, "Girafales", 45, new BigDecimal("3500.00"), "08:00 - 12:00");
        professorDTO = new ProfessorDTO("Girafales", 45, new BigDecimal("3500.00"), "08:00 - 12:00");
    }

    @Test
    @DisplayName("Deve criar um professor com sucesso")
    void deveCriarProfessorComSucesso() {
        when(repository.save(any(Professor.class))).thenReturn(professor);

        ProfessorResponseDTO resultado = service.criar(professorDTO);

        assertNotNull(resultado);
        assertEquals("Girafales", resultado.getNome());
        assertEquals(45, resultado.getIdade());
        assertEquals(new BigDecimal("3500.00"), resultado.getSalario());
        verify(repository, times(1)).save(any(Professor.class));
    }

    @Test
    @DisplayName("Deve listar todos os professores")
    void deveListarTodosOsProfessores() {
        when(repository.findAll()).thenReturn(List.of(professor));

        List<ProfessorResponseDTO> resultado = service.listarTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Girafales", resultado.get(0).getNome());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar professor por ID com sucesso")
    void deveBuscarProfessorPorIdComSucesso() {
        when(repository.findById(1L)).thenReturn(Optional.of(professor));

        ProfessorResponseDTO resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Girafales", resultado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar ID inexistente")
    void deveLancarExcecaoAoBuscarIdInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));

        assertTrue(exception.getMessage().contains("Professor não encontrado"));
    }

    @Test
    @DisplayName("Deve deletar professor por ID com sucesso")
    void deveDeletarProfessorComSucesso() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.deletar(1L));
        verify(repository, times(1)).deleteById(1L);
    }
}