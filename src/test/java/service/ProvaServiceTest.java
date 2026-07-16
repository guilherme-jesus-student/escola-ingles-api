package com.escola.escolainglesapi.service;

import com.escola.escolainglesapi.dto.DadosCadastroProvaDTO;
import com.escola.escolainglesapi.dto.ProvaDTO;
import com.escola.escolainglesapi.model.Aluno;
import com.escola.escolainglesapi.model.Prova;
import com.escola.escolainglesapi.repository.AlunoRepository;
import com.escola.escolainglesapi.repository.ProvaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProvaServiceTest {

    @Mock
    private ProvaRepository provaRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private ProvaService provaService;

    @Test
    @DisplayName("Deve aprovar aluno quando nota for maior ou igual a 7.0")
    void deveAprovarAlunoQuandoNotaForMaiorOuIgualASete() {
        // ARRANGE
        Long alunoId = 1L;
        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        aluno.setNome("Guilherme");

        DadosCadastroProvaDTO dto = new DadosCadastroProvaDTO("English 101", new BigDecimal("7.5"), alunoId);

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        when(provaRepository.save(any(Prova.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        ProvaDTO resultado = provaService.criar(dto);

        // ASSERT
        assertNotNull(resultado);
        assertTrue(resultado.aprovado(), "Aluno deveria estar aprovado com nota 7.5");
        assertEquals("English 101", resultado.materia());
        verify(provaRepository, times(1)).save(any(Prova.class));
    }

    @Test
    @DisplayName("Deve reprovar aluno quando nota for menor que 7.0")
    void deveReprovarAlunoQuandoNotaForMenorQueSete() {
        // ARRANGE
        Long alunoId = 1L;
        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        aluno.setNome("Guilherme");

        DadosCadastroProvaDTO dto = new DadosCadastroProvaDTO("English 101", new BigDecimal("5.0"), alunoId);

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        when(provaRepository.save(any(Prova.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        ProvaDTO resultado = provaService.criar(dto);

        // ASSERT
        assertNotNull(resultado);
        assertFalse(resultado.aprovado(), "Aluno deveria estar reprovado com nota 5.0");
        verify(provaRepository, times(1)).save(any(Prova.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar criar prova para aluno inexistente")
    void deveLancarExcecaoQuandoAlunoNaoExiste() {
        // ARRANGE
        Long alunoId = 99L;
        DadosCadastroProvaDTO dto = new DadosCadastroProvaDTO("English 101", new BigDecimal("8.0"), alunoId);

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.empty());

        // ACT & ASSERT
        RuntimeException exception = assertThrows(RuntimeException.class, () -> provaService.criar(dto));
        assertEquals("Aluno não encontrado com id: " + alunoId, exception.getMessage());
        verify(provaRepository, never()).save(any(Prova.class));
    }
}