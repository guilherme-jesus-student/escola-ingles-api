package com.escola.escolainglesapi.repository;

import com.escola.escolainglesapi.model.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long> {

    // Método para buscar todas as provas cadastradas para um aluno específico
    List<Prova> findByAlunoId(Long alunoId);
}