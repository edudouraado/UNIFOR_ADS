from flask import Blueprint, request, jsonify
from services.carro_service import CarroService

carro_bp = Blueprint('carro', __name__)
service = CarroService()

@carro_bp.route('/carros', methods=['POST'])
def criar():
    dados = request.json
    try:
        carro = service.registrar_carro(dados['modelo'], dados['ano'], dados['placa'])
        return jsonify(carro), 201
    except Exception as e:
        return jsonify({"erro": str(e)}), 400

@carro_bp.route('/carros', methods=['GET'])
def listar():
    return jsonify(service.buscar_carros())

# NOVA ROTA DE DELETE
@carro_bp.route('/carros/<id_carro>', methods=['DELETE'])
def deletar(id_carro):
    service.excluir_carro(id_carro)
    return jsonify({"mensagem": "Carro removido"}), 200