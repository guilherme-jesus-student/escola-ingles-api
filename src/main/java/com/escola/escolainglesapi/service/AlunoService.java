package com.escola.escolainglesapi.service;

import com.escola.escolainglesapi.dto.AlunoDTO;
import com.escola.escolainglesapi.model.Aluno;
import com.escola.escolainglesapi.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    // Injeção por construtor
    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    // 1. Listar todos
    public List<AlunoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // 2. Buscar por ID
    public AlunoDTO buscarPorId(Long id) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + id));
        return toDTO(aluno);
    }

    // 3. Salvar / Criar
    public AlunoDTO criar (AlunoDTO dto) {
        Aluno aluno = toEntity(dto);
        Aluno alunoSalvo = repository.save(aluno);
        return toDTO(alunoSalvo);
    }

    // 4. Atualizar
    public AlunoDTO atualizar(Long id, AlunoDTO dto) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + id));

        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setIdade(dto.getIdade());
        aluno.setEndereco(dto.getEndereco());
        aluno.setHorarioAulas(dto.getHorarioAulas());

        Aluno alunoAtualizado = repository.save(aluno);
        return toDTO(alunoAtualizado);
    }

    // 5. Deletar
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Aluno não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }

    // --- Métodos de Conversão (Entity <-> DTO) ---
    private AlunoDTO toDTO(Aluno aluno) {
        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getIdade(),
                aluno.getEndereco(),
                aluno.getHorarioAulas()
        );
    }

    private Aluno toEntity(AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setId(dto.getId());
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setIdade(dto.getIdade());
        aluno.setEndereco(dto.getEndereco());
        aluno.setHorarioAulas(dto.getHorarioAulas());
        return aluno;
    }
}