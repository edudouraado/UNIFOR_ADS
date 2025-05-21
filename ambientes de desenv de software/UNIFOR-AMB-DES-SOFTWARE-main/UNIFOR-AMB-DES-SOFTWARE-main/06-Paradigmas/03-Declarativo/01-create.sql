
CREATE TABLE ESTUDANTES (
    id_estudante INT PRIMARY KEY,
    nome VARCHAR(50)
);

CREATE TABLE CURSOS (
    id_curso INT PRIMARY KEY,
    nome_curso VARCHAR(50)
);

CREATE TABLE INSCRICOES (
    id_estudante INT,
    id_curso INT,
    PRIMARY KEY (id_estudante, id_curso),
    FOREIGN KEY (id_estudante) REFERENCES ESTUDANTES(id_estudante),
    FOREIGN KEY (id_curso) REFERENCES CURSOS(id_curso)
);