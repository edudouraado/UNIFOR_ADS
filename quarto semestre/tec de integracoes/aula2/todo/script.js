const apiURL = 'https://jsonplaceholder.typicode.com/todos';
const taskList = document.getElementById('taskList');
const taskInput = document.getElementById('taskInput');

async function fetchTasks() {
    try{
        const response = await fetch(`${apiURL}?_limit=5`);
        const json = await response.json();
        json.forEach(task => renderTask(task.title, task.id));
    } catch (error) {
        alert('ERRRRRROUUUU', error.message);
    }
}

async function handleAddTask() {
    const novaTarefa = taskInput.value.trim();
    if(!novaTarefa) return alert("Digite uma tarefa no campo");

    try {
        const response = await fetch(`${apiURL}`, {
            method: "POST",
            body: JSON.stringify( { title: novaTarefa, completed: false, userId: 1 } ),
            headers: { 'Content-type' : 'application/json; charset=UTF-8' }
        });

        const newTask = await response.json();
        renderTask(newTask.title, newTask.id);
        taskInput.value = '';
    } catch (error) {
        alert("Erro ao adiciona uma nova tarefa.");
    }
}

async function deleteTask(id, element) {
    try {
        await fetch (`${apiURL}/${id}`, {method : 'DELETE'});
        element.remove();
    } catch (error) {
        alert("Erro! Não foi possível deletar a tarefa", error);
    }
}

async function editTask(id, element) {
    const span = element.querySelector('span');
    const editButton = element.querySelector('.btn-edit');

    // Se já estiver no modo de edição, salva a tarefa
    if (editButton.dataset.mode === 'editing') {
        const input = element.querySelector('input');
        const novoTitulo = input.value.trim();
        if (!novoTitulo) return alert('Digite uma tarefa válida.');

        try {
            await fetch(`${apiURL}/${id}`, {
                method: 'PATCH',
                headers: { 'Content-type': 'application/json; charset=UTF-8' },
                body: JSON.stringify({ title: novoTitulo })
            });

            span.textContent = novoTitulo;
            span.style.display = '';
            input.remove();
            editButton.textContent = 'Editar';
            editButton.dataset.mode = '';
        } catch (error) {
            alert('Erro ao editar a tarefa.');
        }

        return;
    }

    // Modo edição: trocar o span por um input
    const input = document.createElement('input');
    input.type = 'text';
    input.value = span.textContent;
    element.insertBefore(input, span);
    span.style.display = 'none';

    editButton.textContent = 'Salvar';
    editButton.dataset.mode = 'editing';
}

function renderTask(title, id, completed = false) {
    const li = document.createElement('li');
    li.innerHTML = `
        <span>${title}</span>
        <div class="actions">
            <button class="btn-edit" onclick="editTask(${id}, this.parentElement.parentElement)">Editar</button>
            <button class="btn-delete" onclick="deleteTask(${id}, this.parentElement.parentElement)">Deletar</button>
        </div>
        `;

    taskList.appendChild(li);

}

fetchTasks();