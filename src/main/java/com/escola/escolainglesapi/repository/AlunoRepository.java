package com.escola.escolainglesapi.repository;

import com.escola.escolainglesapi.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // Métodos prontos que o Spring Data JPA nos fornece:
    // - save()
    // - findAll()
    // - findById()
    // - delete()
}