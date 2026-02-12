from repositories.carro_repo import CarroRepository
import uuid # Importante para gerar IDs únicos

class CarroService:
    def __init__(self):
        self.repo = CarroRepository()

    def registrar_carro(self, modelo, ano, placa):
        if int(ano) > 2025:
            raise Exception("Ano inválido! Não aceitamos carros do futuro.")
        
        # Gera um ID único e cria o dicionário
        novo_carro = {
            "id": str(uuid.uuid4()), # ID único
            "modelo": modelo,
            "ano": ano,
            "placa": placa
        }
        return self.repo.salvar(novo_carro)

    def buscar_carros(self):
        return self.repo.listar_todos()

    def excluir_carro(self, id_carro):
        return self.repo.excluir(id_carro)