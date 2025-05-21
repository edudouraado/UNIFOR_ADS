-- Exibir o nome dos estudantes e o nome dos cursos que eles estão inscritos, incluindo os estudantes que não estão inscritos em nenhum curso.
SELECT e.nome,
    c.nome_curso
FROM ESTUDANTES e
    RIGHT JOIN INSCRICOES i ON e.id_estudante = i.id_estudante
    RIGHT JOIN CURSOS c ON i.id_curso = c.id_curso;