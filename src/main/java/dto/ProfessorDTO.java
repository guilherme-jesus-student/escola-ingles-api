package dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProfessorDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    @NotNull(message = "A idade é obrigatória.")
    @Min(value = 18, message = "O professor deve ter pelo menos 18 anos.")
    private Integer idade;

    @NotNull(message = "O salário é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O salário deve ser maior que zero.")
    private BigDecimal salario;

    @NotBlank(message = "O horário de trabalho é obrigatório.")
    private String horarioTrabalho;

    public ProfessorDTO() {
    }

    public ProfessorDTO(String nome, Integer idade, BigDecimal salario, String horarioTrabalho) {
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
        this.horarioTrabalho = horarioTrabalho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(String horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }
}