SELECT SUM(valor) AS total_credito
FROM movimentacao
WHERE tipo_movimentacao = 'CREDITO'
  AND data_movimentacao >= '2023-11-01 00:00:00'
  AND data_movimentacao <= '2023-12-31 23:59:59';