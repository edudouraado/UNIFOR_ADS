/**
 * Exercício 4: Nomes de variáveis abreviados e não descritivos
 * Problema: O código abaixo usa abreviações e nomes que não comunicam a intenção.
 */
function an_Data(dt, fmt) {
  const res = {};

  // Extrai estatísticas dos dados de vendas
  const tsv = dt.reduce((acc, curr) => acc + curr.val, 0);
  const cnt = dt.length;
  const avg = cnt > 0 ? tsv / cnt : 0;

  // Encontra o maior valor
  let mx = dt.length > 0 ? dt[0].val : 0;
  for (let i = 1; i < dt.length; i++) {
    if (dt[i].val > mx) {
      mx = dt[i].val;
    }
  }

  // Encontra o menor valor
  let mn = dt.length > 0 ? dt[0].val : 0;
  for (let i = 1; i < dt.length; i++) {
    if (dt[i].val < mn) {
      mn = dt[i].val;
    }
  }

  res.tot = tsv;
  res.cnt = cnt;
  res.avg = avg;
  res.mx = mx;
  res.mn = mn;

  // Filtra apenas os meses recentes se solicitado
  if (fmt === "rc") {
    res.rcData = dt.filter((item) => {
      const cdt = new Date();
      const idt = new Date(item.dt);
      return (cdt - idt) / (24 * 60 * 60 * 1000) <= 30;
    });
  }

  return res;
}

console.log(an_Data([{dt: "2024-01-01", val: 100}, {dt: "2024-01-02", val: 200}], "rc"));
