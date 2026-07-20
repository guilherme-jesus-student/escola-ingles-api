package com.escola.escolainglesapi.service;

import com.escola.escolainglesapi.dto.DadosCadastroProvaDTO;
import com.escola.escolainglesapi.dto.ProvaDTO;
import com.escola.escolainglesapi.model.Aluno;
import com.escola.escolainglesapi.model.Prova;
import com.escola.escolainglesapi.repository.AlunoRepository;
import com.escola.escolainglesapi.repository.ProvaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProvaService {

    private static final BigDecimal NOTA_CORTE = new BigDecimal("7.0");

    private final ProvaRepository provaRepository;
    private final AlunoRepository alunoRepository;

    public ProvaService(ProvaRepository provaRepository, AlunoRepository alunoRepository) {
        this.provaRepository = provaRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public ProvaDTO criar(DadosCadastroProvaDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + dto.alunoId()));

        // Regra de Negócio: Se a nota for maior ou igual a 7.0, está aprovado!
        boolean aprovado = dto.nota().compareTo(NOTA_CORTE) >= 0;

        Prova prova = new Prova(dto.materia(), dto.nota(), aprovado, aluno);
        provaRepository.save(prova);

        return new ProvaDTO(prova);
    }

    public List<ProvaDTO> listarTodas() {
        return provaRepository.findAll()
                .stream()
                .map(ProvaDTO::new)
                .toList();
    }

    public ProvaDTO buscarPorId(Long id) {
        Prova prova = provaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prova não encontrada com id: " + id));
        return new ProvaDTO(prova);
    }

    @Transactional
    public ProvaDTO atualizar(Long id, DadosCadastroProvaDTO dto) {
        Prova prova = provaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prova não encontrada com id: " + id));

        Aluno aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + dto.alunoId()));

        boolean aprovado = dto.nota().compareTo(NOTA_CORTE) >= 0;

        prova.setMateria(dto.materia());
        prova.setNota(dto.nota());
        prova.setAprovado(aprovado);
        prova.setAluno(aluno);

        return new ProvaDTO(prova);
    }

    @Transactional
    public void deletar(Long id) {
        if (!provaRepository.existsById(id)) {
            throw new RuntimeException("Prova não encontrada com id: " + id);
        }
        provaRepository.deleteById(id);
    }
}