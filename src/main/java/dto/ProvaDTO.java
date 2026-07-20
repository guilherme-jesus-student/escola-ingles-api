package com.escola.escolainglesapi.dto;

import com.escola.escolainglesapi.model.Prova;
import java.math.BigDecimal;

public record ProvaDTO(
        Long id,
        String materia,
        BigDecimal nota,
        Boolean aprovado,
        Long alunoId,
        String nomeAluno
) {
    public ProvaDTO(Prova prova) {
        this(
                prova.getId(),
                prova.getMateria(),
                prova.getNota(),
                prova.getAprovado(),
                prova.getAluno().getId(),
                prova.getAluno().getNome()
        );
    }
}