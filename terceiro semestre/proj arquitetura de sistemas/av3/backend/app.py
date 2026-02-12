from flask import Flask
from flask_cors import CORS
from controllers.carro_controller import carro_bp

app = Flask(__name__)
CORS(app) # Permite que o Front converse com o Back

# Registra as rotas
app.register_blueprint(carro_bp)

if __name__ == '__main__':
    app.run(debug=True, port=5000)