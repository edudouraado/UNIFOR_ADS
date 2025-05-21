/**
 * Exercício 3: Métodos com nomes não descritivos
 * Problema: Os métodos e propriedades abaixo têm nomes que não descrevem bem o que fazem.
 */
class gerenciar_usuario {
  constructor(bDados) {
    this.bDados = bDados;
  }

  proc(e, s) {
    const u = this.bDados.buscar_Por_Email(e);
    if (!u) {
      return { sucesso: false, mensagem: "Usuário não encontrado" };
    }

    // Verifica se a senha coincide com a do banco de dados
    if (this.bDados.vf_Senha(u.id, s)) {
      // Cria token de autenticação
      const t = this.bDados.g_Token(u.id);
      return { sucesso: true, t };
    } else {
      return { sucesso: false, mensagem: "Senha incorreta" };
    }
  }

  get(id) {
    // Busca usuários por ID, exclui dados sensíveis
    const u = this.bDados.b_Dados(id);
    if (u) {
      const { s, pin, ...d_p } = u;
      return d_p;
    }
    return null;
  }

  exec(id) {
    // Remove usuário da base de dados
    return this.bDados.ru(id);
  }
}
