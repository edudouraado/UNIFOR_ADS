class SupercarManager {
    constructor() {
        this.cars = JSON.parse(localStorage.getItem('supercars')) || [];
        this.init();
    }

    init() {
        this.renderCars();
        this.setupEventListeners();
    }

    setupEventListeners() {
        const addButton = document.getElementById('addButton');
        const carInput = document.getElementById('carInput');

        // Botão Adicionar
        addButton.addEventListener('click', () => {
            this.addCar();
        });

        // Enter no input
        carInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                this.addCar();
            }
        });
    }

    addCar() {
        const input = document.getElementById('carInput');
        const carName = input.value.trim();

        // Validação
        if (carName === '') {
            this.showNotification('⚠️ Digite o nome de um supercarro!', 'warning');
            return;
        }

        // Verificar se já existe
        if (this.cars.some(car => car.name.toLowerCase() === carName.toLowerCase())) {
            this.showNotification('🚫 Este carro já está na lista!', 'error');
            return;
        }

        // Criar novo carro
        const newCar = {
            id: Date.now(),
            name: carName,
            seen: false,
            addedDate: new Date().toLocaleDateString('pt-BR')
        };

        // Adicionar à lista
        this.cars.unshift(newCar);
        this.saveToLocalStorage();
        this.renderCars();
        
        // Limpar input
        input.value = '';
        this.showNotification('✅ Supercarro adicionado com sucesso!', 'success');
    }

    toggleSeen(carId) {
        this.cars = this.cars.map(car => 
            car.id === carId ? { ...car, seen: !car.seen } : car
        );
        this.saveToLocalStorage();
        this.renderCars();
    }

    deleteCar(carId, event) {
        event.stopPropagation(); // Impedir que clique propague para o item
        
        if (confirm('Tem certeza que deseja remover este supercarro da lista?')) {
            this.cars = this.cars.filter(car => car.id !== carId);
            this.saveToLocalStorage();
            this.renderCars();
            this.showNotification('🗑️ Supercarro removido!', 'info');
        }
    }

    renderCars() {
        const carList = document.getElementById('carList');
        const totalCars = document.getElementById('totalCars');
        const seenCars = document.getElementById('seenCars');

        carList.innerHTML = '';

        if (this.cars.length === 0) {
            carList.innerHTML = `
                <div class="empty-state">
                    <p>🎯 Sua lista está vazia!</p>
                    <p>Adicione alguns supercarros que você deseja conhecer.</p>
                </div>
            `;
        } else {
            this.cars.forEach(car => {
                const carItem = document.createElement('li');
                carItem.className = `car-item ${car.seen ? 'seen' : ''}`;
                carItem.innerHTML = `
                    <div class="car-info">
                        <div class="car-name">${car.name}</div>
                        <div class="car-date">Adicionado em: ${car.addedDate}</div>
                    </div>
                    <div class="car-actions">
                        <button class="delete-btn" onclick="supercarManager.deleteCar(${car.id}, event)">
                            🗑️ Remover
                        </button>
                    </div>
                `;

                // Clique para marcar como visto
                carItem.addEventListener('click', (e) => {
                    if (!e.target.classList.contains('delete-btn')) {
                        this.toggleSeen(car.id);
                    }
                });

                carList.appendChild(carItem);
            });
        }

        // Atualizar estatísticas
        totalCars.textContent = `Total: ${this.cars.length}`;
        const seenCount = this.cars.filter(car => car.seen).length;
        seenCars.textContent = `Vistos: ${seenCount}`;
    }

    saveToLocalStorage() {
        localStorage.setItem('supercars', JSON.stringify(this.cars));
        console.log('Dados salvos no localStorage:', this.cars);
    }

    showNotification(message, type) {
        // Remover notificações anteriores
        const existingNotifications = document.querySelectorAll('.notification');
        existingNotifications.forEach(notif => notif.remove());

        // Criar nova notificação
        const notification = document.createElement('div');
        notification.className = 'notification';
        notification.textContent = message;
        
        // Cores baseadas no tipo
        const colors = {
            success: '#10ac84',
            error: '#ff6b6b',
            warning: '#feca57',
            info: '#48dbfb'
        };

        notification.style.background = colors[type] || colors.info;
        document.body.appendChild(notification);

        // Remover após 3 segundos
        setTimeout(() => {
            if (notification.parentNode) {
                notification.parentNode.removeChild(notification);
            }
        }, 3000);
    }

    // Método para debug - ver dados salvos
    debug() {
        console.log('Dados atuais:', this.cars);
        console.log('LocalStorage:', localStorage.getItem('supercars'));
    }
}

// Inicializar a aplicação quando a página carregar
document.addEventListener('DOMContentLoaded', function() {
    window.supercarManager = new SupercarManager();
    
    // Para debug - descomente a linha abaixo se quiser ver os dados no console
    // supercarManager.debug();
});