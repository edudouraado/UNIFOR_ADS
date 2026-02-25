const apiURL = "https://jsonplaceholder.typicode.com/todos";
const taskList = document.getElementById("taskList");
const taskInput = document.getElementById("taskInput");


async function fetchTasks() {
    try {
        const response = await fetch(`${apiURL}?_limit=10`);
        const tasks = await response.json();
        //console.log(tasks);
        tasks.forEach(task => {
            renderTasks(task.title, task.id);
        });
    } catch (error) {
        console.error("Erro ao buscar as tarefas:", error);
    }
}

function renderTasks(title, id) {
    const li = document.createElement("li");
    li.setAttribute('data-id', id);
    li.innerHTML = 
    `<span>${title}</span>
    <button class="btn-delete" onclick="deleteTask(${id})">Excluir</button>`;
    taskList.appendChild(li);
}

fetchTasks();