const apiURL = 'https://jsonplaceholder.typicode.com/todos';

async function buscarTarefas(){
    try{
        const response = await fetch(`${apiURL}?_limit=5`);
        const json = await response.json();
        console.log(json);
    } catch (error) {
        alert('Não deu pra chamar o jsonplaceholder', error.message);
    }
}



buscarTarefas();