-- Exibe os estudantes que não estão inscritos em nenhum curso e os cursos que não possuem estudantes inscritos.
SELECT e.nome,
    c.nome_curso
FROM ESTUDANTES e
    FULL OUTER JOIN INSCRICOES i ON e.id_estudante = i.id_estudante
    FULL OUTER JOIN CURSOS c ON i.id_curso = c.id_curso
WHERE e.id_estudante IS NULL
    OR c.id_curso IS NULL;