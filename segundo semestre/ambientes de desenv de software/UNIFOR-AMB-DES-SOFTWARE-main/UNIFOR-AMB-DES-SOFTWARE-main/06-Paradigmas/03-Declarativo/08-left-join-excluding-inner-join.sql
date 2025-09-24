
-- Exibe os estudantes que não estão inscritos em nenhum curso.
SELECT e.nome
FROM ESTUDANTES e
    LEFT JOIN INSCRICOES i ON e.id_estudante = i.id_estudante
WHERE i.id_estudante IS NULL;


