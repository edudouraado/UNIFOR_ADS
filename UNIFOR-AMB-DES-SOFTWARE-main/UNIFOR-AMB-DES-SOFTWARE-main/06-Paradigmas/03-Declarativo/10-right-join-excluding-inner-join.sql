-- Exibir os cursos que não possuem inscrições
SELECT c.nome_curso
FROM CURSOS c
    RIGHT JOIN INSCRICOES i ON c.id_curso = i.id_curso
WHERE i.id_curso IS NULL;