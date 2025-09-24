-- Exibe o nome do estudante e o nome do curso que ele está inscrito, mesmo que não esteja inscrito em nenhum curso.
SELECT e.nome,
    c.nome_curso
FROM ESTUDANTES e
    FULL OUTER JOIN INSCRICOES i ON e.id_estudante = i.id_estudante
    FULL OUTER JOIN CURSOS c ON i.id_curso = c.id_curso;