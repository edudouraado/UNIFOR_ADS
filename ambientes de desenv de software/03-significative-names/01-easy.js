/**
 * Exercício 1: Variáveis com nomes ruins
 * Problema: As variáveis abaixo têm nomes ruins que não comunicam sua intenção.
 */
function ct(a, d, t) {
  let r = 0;
  for (let i = 0; i < a.length; i++) {
    const v = a[i].p * a[i].q;
    if (a[i].t === "alimento") {
      r += v * (1 + t.alim);
    } else {
      r += v * (1 + t.geral);
    }
  }
  return r - d;
}

console.log(
  ct(
    [
      { p: 10, q: 2, t: "alimento" },
      { p: 10, q: 2, t: "alimento" },
    ],
    10,
    { alim: 0.1, geral: 0.05 }
  )
);
