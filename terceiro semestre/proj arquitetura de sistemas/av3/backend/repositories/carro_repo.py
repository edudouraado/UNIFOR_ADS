banco_de_dados = []

class CarroRepository:
    def salvar(self, carro):
        banco_de_dados.append(carro)
        return carro

    def listar_todos(self):
        return banco_de_dados

    def excluir(self, id_carro):
        # Filtra a lista mantendo apenas os carros que NÃO têm aquele ID
        global banco_de_dados
        banco_de_dados = [c for c in banco_de_dados if c['id'] != id_carro]
        return True