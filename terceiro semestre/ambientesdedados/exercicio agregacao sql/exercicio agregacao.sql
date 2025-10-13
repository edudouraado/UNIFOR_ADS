-- função de agregação
use controle_financeiro;
SELECT MAX(c.saldo) FROM conta c INNER JOIN tipo_conta tc ON c.codigo_tipo_conta = tc.codigo WHERE tc.descricao = 'Conta Poupança';

SELECT AVG(MEDIA) FROM movimentacao WHERE descricao LIKE '%alimentação%';

SELECT COUNT(*) FROM movimentacao where data_movimentacao between '2023-04-15' and '2023-05-12';

SELECT count(*) FROM usuario where cep is not null;

SELECT AVG(valor),max(valor),min(valor) FROM movimentacao WHERE valor < 30;

SELECT sum(valor) from movimentacao where data_movimentacao between '2023-11-01' and '2023-12-31';

select * from movimentacao;

-- agregação
select sum(saldo) from conta;

select avg(valor) from movimentacao;

select avg(valor) from movimentacao group by codigo_conta;

select count(codigo) from movimentacao group by codigo_conta;

select * from movimentacao;

-- join
select * from usuario;
select * from movimentacao;

select u.codigo, u.nome, c.saldo from usuario u INNER JOIN conta c ON u.codigo = c.codigo_usuario;

select u.codigo, u.nome from usuario u INNER JOIN conta c ON u.codigo = c.codigo_usuario;