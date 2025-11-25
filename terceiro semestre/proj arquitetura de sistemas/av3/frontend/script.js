async function salvarCarro() {
    const modelo = document.getElementById('modelo').value;
    const ano = document.getElementById('ano').value;
    const placa = document.getElementById('placa').value;

    // Chama o Back-end
    const resposta = await fetch('http://127.0.0.1:5000/carros', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ modelo, ano, placa })
    });

    if (resposta.ok) {
        alert("Carro salvo!");
        carregarCarros();
    } else {
        alert("Erro ao salvar");
    }
}

async function carregarCarros() {
    const resposta = await fetch('http://127.0.0.1:5000/carros');
    const carros = await resposta.json();
    
    const lista = document.getElementById('lista');
    lista.innerHTML = '';
    carros.forEach(c => {
        lista.innerHTML += `<li>${c.modelo} - ${c.ano}</li>`;
    });
}