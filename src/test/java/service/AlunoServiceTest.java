package service;

import com.escola.escolainglesapi.dto.AlunoDTO;
import com.escola.escolainglesapi.model.Aluno;
import com.escola.escolainglesapi.repository.AlunoRepository;
import com.escola.escolainglesapi.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository repository;

    @InjectMocks
    private AlunoService service;

    private Aluno aluno;
    private AlunoDTO alunoDTO;

    @BeforeEach
    void setUp() {
        aluno = new Aluno(1L, "Guilherme Jesus", "guilherme@email.com", 22, "Rua A", "Segunda 19h");
        alunoDTO = new AlunoDTO(1L, "Guilherme Jesus", "guilherme@email.com", 22, "Rua A", "Segunda 19h");
    }

    @Test
    @DisplayName("Deve listar todos os alunos com sucesso")
    void deveListarTodosOsAlunos() {
        when(repository.findAll()).thenReturn(List.of(aluno));

        List<AlunoDTO> resultado = service.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Guilherme Jesus", resultado.get(0).getNome());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar aluno por ID com sucesso")
    void deveBuscarAlunoPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(aluno));

        AlunoDTO resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Guilherme Jesus", resultado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar ID inexistente")
    void deveLancarExcecaoQuandoAlunoNaoEncontrado() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));
    }

    @Test
    @DisplayName("Deve salvar um novo aluno com sucesso")
    void deveSalvarAluno() {
        when(repository.save(any(Aluno.class))).thenReturn(aluno);

        AlunoDTO resultado = service.criar(alunoDTO);

        assertNotNull(resultado);
        assertEquals("Guilherme Jesus", resultado.getNome());
        verify(repository, times(1)).save(any(Aluno.class));
    }

    @Test
    @DisplayName("Deve deletar um aluno existente")
    void deveDeletarAluno() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.deletar(1L));
        verify(repository, times(1)).deleteById(1L);
    }
}