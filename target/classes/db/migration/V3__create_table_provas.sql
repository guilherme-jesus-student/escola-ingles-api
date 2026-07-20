CREATE TABLE provas (
                        id BIGSERIAL PRIMARY KEY,
                        materia VARCHAR(100) NOT NULL,
                        nota DECIMAL(4,2) NOT NULL,
                        aprovado BOOLEAN NOT NULL,
                        aluno_id BIGINT NOT NULL,

                        CONSTRAINT fk_provas_aluno FOREIGN KEY (aluno_id) REFERENCES alunos(id) ON DELETE CASCADE
);