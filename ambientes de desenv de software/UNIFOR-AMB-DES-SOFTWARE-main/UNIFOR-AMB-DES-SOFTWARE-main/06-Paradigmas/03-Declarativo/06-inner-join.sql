

-- Mostra o nome dos estudantes e o nome dos cursos em que estão inscritos
SELECT e.nome,
    c.nome_curso
FROM ESTUDANTES e
    INNER JOIN INSCRICOES i ON e.id_estudante = i.id_estudante
    INNER JOIN CURSOS c ON i.id_curso = c.id_curso;



    