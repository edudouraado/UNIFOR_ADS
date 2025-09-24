/**
 * Exercício 6: Função complexa com parâmetros e variáveis mal nomeados
 * Problema: Esta função complexa tem muitos problemas de nomenclatura, incluindo:
 *   - Nomes não descritivos para a função e seus parâmetros
 *   - Variáveis intermediárias sem significado claro
 *   - Nomeação inconsistente (mistura de inglês e português)
 *   - Uso de letras únicas para variáveis importantes
 */
function processData(d, o) {
  const r = [];

  // Aplicar filtros conforme as opções
  let fd = [...d];

  if (o.filterActive && o.f) {
    fd = fd.filter((item) => {
      for (const k in o.f) {
        if (o.f[k] && item[k] !== o.f[k]) {
          return false;
        }
      }
      return true;
    });
  }

  // Aplicar transformações aos dados
  for (let i = 0; i < fd.length; i++) {
    const item = fd[i];
    const temp = { ...item };

    // Calcular valores derivados
    if (item.tipo === "venda") {
      const v = item.valor;
      const t = item.taxa || 0;
      const d = item.desconto || 0;

      temp.vl_bruto = v;
      temp.vl_taxa = v * (t / 100);
      temp.vl_desconto = d;
      temp.valorFinal = v + temp.vl_taxa - d;

      // Calcular status com base no valor
      if (temp.valorFinal > 1000) {
        temp.prio = "alto";
      } else if (temp.valorFinal > 500) {
        temp.prio = "medio";
      } else {
        temp.prio = "baixo";
      }
    } else if (item.tipo === "servico") {
      const v = item.valor;
      const h = item.horas || 1;

      temp.vl_bruto = v * h;
      temp.vl_taxa = 0;
      temp.vl_desconto = 0;
      temp.valorFinal = temp.vl_bruto;

      // Calcular status com base nas horas
      if (h > 10) {
        temp.prio = "alto";
      } else if (h > 5) {
        temp.prio = "medio";
      } else {
        temp.prio = "baixo";
      }
    }

    // Adicionar metadados
    temp.updated = new Date().toISOString();
    temp.processado = true;

    r.push(temp);
  }

  // Ordenar resultados se necessário
  if (o.s && o.s.field) {
    const sf = o.s.field;
    const dir = o.s.dir === "desc" ? -1 : 1;

    r.sort((a, b) => {
      if (a[sf] < b[sf]) return -1 * dir;
      if (a[sf] > b[sf]) return 1 * dir;
      return 0;
    });
  }

  return r;
}
