package com.escola.escolainglesapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    private Integer idade;

    private String endereco;

    @Column(name = "horario_aulas")
    private String horarioAulas;

    // Construtor padrão (obrigatório para a JPA)
    public Aluno() {
    }

    // Construtor completo
    public Aluno(Long id, String nome, String email, Integer idade, String endereco, String horarioAulas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.endereco = endereco;
        this.horarioAulas = horarioAulas;
    }

    // Getters e Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getHorarioAulas() {
        return horarioAulas;
    }

    public void setHorarioAulas(String horarioAulas) {
        this.horarioAulas = horarioAulas;
    }
}