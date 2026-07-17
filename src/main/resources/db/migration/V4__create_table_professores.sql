CREATE TABLE professores (
                             id BIGSERIAL PRIMARY KEY,
                             nome VARCHAR(100) NOT NULL,
                             idade INT NOT NULL,
                             salario DECIMAL(10,2) NOT NULL,
                             horario_trabalho VARCHAR(50) NOT NULL
);