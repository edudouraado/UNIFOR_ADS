/**
 * Exercício 2: Constantes mágicas
 * Problema: O código abaixo contém valores numéricos "mágicos" e métodos e outros valores sem significado claro.
 */
function cpf(p) {
  let pb = p.preco;

  if (p.tipo === 1) {
    return pb * 0.92;
  } else if (p.tipo === 2) {
    return pb * 0.94;
  } else if (p.tipo === 3) {
    return pb * 0.9;
  } else {
    return pb;
  }
}

console.log(cpf({preco: 10, tipo: 1}));
