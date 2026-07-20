package com.escola.escolainglesapi.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record DadosCadastroProvaDTO(

        @NotBlank(message = "A matéria é obrigatória")
        String materia,

        @NotNull(message = "A nota é obrigatória")
        @DecimalMin(value = "0.0", message = "A nota mínima é 0.0")
        @DecimalMax(value = "10.0", message = "A nota máxima é 10.0")
        BigDecimal nota,

        @NotNull(message = "O ID do aluno é obrigatório")
        Long alunoId
) {}