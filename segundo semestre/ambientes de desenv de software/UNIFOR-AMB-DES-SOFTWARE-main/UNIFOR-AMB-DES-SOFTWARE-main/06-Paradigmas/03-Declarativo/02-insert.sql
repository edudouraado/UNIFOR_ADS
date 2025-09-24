
INSERT INTO ESTUDANTES (id_estudante, nome)
VALUES (1, 'Alice'),
    (2, 'Bruno'),
    (3, 'Carla');

INSERT INTO CURSOS (id_curso, nome_curso)
VALUES (101, 'Matemática'),
    (102, 'História'),
    (103, 'Computação');

INSERT INTO INSCRICOES (id_estudante, id_curso)
VALUES (1, 101),
    (2, 102),
    (3, 103),
    (1, 103);
    