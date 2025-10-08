#1 questão dml basico

select nome, data_de_nascimento from usuario;

#2 questão dml basico

select descricao, saldo from conta where codigo_usuario = 1;

#3 questão dml basico

select * from movimentacao;

#4 questão dml basico

insert into usuario (codigo, nome, rua, numero, bairro, cep, cidade, UF, data_de_nascimento) values (4, 'Eduardo', 'Rua Nunes Valente', '333', 'Meireles', '0000000', 'Fortaleza', 'CE', '2005-09-30 21:00:00');
select * from usuario;
insert into conta (codigo, descricao, codigo_tipo_conta, codigo_usuario, saldo) values (7, 'Conta poupança', 2, 4, 7500.00);
insert into conta (codigo, descricao, codigo_tipo_conta, codigo_usuario, saldo) values (8, 'Conta corrente', 1, 4, 30000.00);
select * from conta;
insert into movimentacao (codigo, descricao, tipo_movimentacao, valor, codigo_conta, data_movimentacao) values (188, 'Salário', 'CREDITO', 2000.00, 8, '2025-09-07 00:00:00');
select * from movimentacao;

#questao 5 dml basico

update usuario set cep = '60175145' where codigo = 2;
select * from usuario;

#questao 6 dml basico

select * from movimentacao where codigo_conta = 3;
delete from movimentacao where codigo_conta = 3;
select * from movimentacao where codigo_conta = 3;

#questao 7 dml basico

select * from usuario where month (data_de_nascimento) = 11;

#questao 8 dml basico

select * from usuario where dayofweek(data_de_nascimento) = 6;

#questao 9 dml basico

select codigo, nome, data_de_nascimento, timestampdiff( year, data_de_nascimento, curdate()) as Idade from usuario;

#questão 10 dml basico

select * from movimentacao order by data_movimentacao desc;

#questao 11 dml basico

select min(data_movimentacao) as movimentacao_mais_antiga from movimentacao;

#questao 12 dml basico
insert into usuario (codigo, nome, rua, numero, bairro, cep, cidade, UF, data_de_nascimento) values (5, 'Vitor', 'Rua Sla', '111', 'Quintino Cunha','', 'Fortaleza', 'CE', '2005-09-30 21:00:00');
update usuario set cep = null where codigo = 5;
select * from usuario where cep is null;
select * from usuario;