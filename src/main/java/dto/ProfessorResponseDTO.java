package dto;

import java.math.BigDecimal;

public class ProfessorResponseDTO {

    private Long id;
    private String nome;
    private Integer idade;
    private BigDecimal salario;
    private String horarioTrabalho;

    public ProfessorResponseDTO() {
    }

    public ProfessorResponseDTO(Long id, String nome, Integer idade, BigDecimal salario, String horarioTrabalho) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
        this.horarioTrabalho = horarioTrabalho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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