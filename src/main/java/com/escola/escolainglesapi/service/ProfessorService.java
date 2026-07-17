package com.escola.escolainglesapi.service;

import dto.ProfessorDTO;
import dto.ProfessorResponseDTO;
import com.escola.escolainglesapi.model.Professor;
import com.escola.escolainglesapi.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public ProfessorResponseDTO criar(ProfessorDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setIdade(dto.getIdade());
        professor.setSalario(dto.getSalario());
        professor.setHorarioTrabalho(dto.getHorarioTrabalho());

        Professor salvo = repository.save(professor);
        return converterParaResponseDTO(salvo);
    }

    public List<ProfessorResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public ProfessorResponseDTO buscarPorId(Long id) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + id));
        return converterParaResponseDTO(professor);
    }

    public ProfessorResponseDTO atualizar(Long id, ProfessorDTO dto) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + id));

        professor.setNome(dto.getNome());
        professor.setIdade(dto.getIdade());
        professor.setSalario(dto.getSalario());
        professor.setHorarioTrabalho(dto.getHorarioTrabalho());

        Professor atualizado = repository.save(professor);
        return converterParaResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Professor não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    private ProfessorResponseDTO converterParaResponseDTO(Professor professor) {
        return new ProfessorResponseDTO(
                professor.getId(),
                professor.getNome(),
                professor.getIdade(),
                professor.getSalario(),
                professor.getHorarioTrabalho()
        );
    }
}