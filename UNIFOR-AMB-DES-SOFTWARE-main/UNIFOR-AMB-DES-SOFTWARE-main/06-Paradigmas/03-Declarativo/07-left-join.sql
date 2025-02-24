-- Exibe o nome de todos os estudantes e o nome do curso que estão inscritos, caso estejam inscritos.
SELECT e.nome,
    c.nome_curso
FROM ESTUDANTES e
    LEFT JOIN INSCRICOES i ON e.id_estudante = i.id_estudante
    LEFT JOIN CURSOS c ON i.id_curso = c.id_curso;