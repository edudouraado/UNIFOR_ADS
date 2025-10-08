class UnsplashImageSearch {
    constructor() {
        this.accessKey = 'YOUR_UNSPLASH_ACCESS_KEY'; // Você precisa criar conta no Unsplash
        this.baseURL = 'https://api.unsplash.com';
        this.currentPage = 1;
        this.currentQuery = '';
        this.isLoading = false;
        
        // Elementos DOM
        this.elements = {
            searchInput: document.getElementById('searchInput'),
            searchBtn: document.getElementById('searchBtn'),
            resultsGrid: document.getElementById('results'),
            loading: document.getElementById('loading'),
            error: document.getElementById('error'),
            errorMessage: document.getElementById('errorMessage'),
            resultsInfo: document.getElementById('resultsInfo'),
            resultsCount: document.getElementById('resultsCount'),
            searchTerm: document.getElementById('searchTerm'),
            loadMoreBtn: document.getElementById('loadMoreBtn'),
            loadMoreContainer: document.getElementById('loadMoreContainer'),
            imageModal: document.getElementById('imageModal'),
            modalImage: document.getElementById('modalImage'),
            modalTitle: document.getElementById('modalTitle'),
            modalAuthor: document.getElementById('modalAuthor'),
            modalLikes: document.getElementById('modalLikes'),
            modalViews: document.getElementById('modalViews'),
            modalDownloads: document.getElementById('modalDownloads'),
            modalDownload: document.getElementById('modalDownload')
        };

        this.init();
    }

    init() {
        this.bindEvents();
        this.loadRandomImages();
    }

    bindEvents() {
        // Eventos de pesquisa
        this.elements.searchBtn.addEventListener('click', () => this.search());
        this.elements.searchInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') this.search();
        });

        // Categorias rápidas
        document.querySelectorAll('.category-btn').forEach(btn => {
            btn.addEventListener('click', (e) => {
                const category = e.currentTarget.dataset.category;
                this.elements.searchInput.value = category;
                this.search();
            });
        });

        // Carregar mais imagens
        this.elements.loadMoreBtn.addEventListener('click', () => this.loadMore());

        // Modal
        document.querySelector('.close-btn').addEventListener('click', () => this.closeModal());
        this.elements.imageModal.addEventListener('click', (e) => {
            if (e.target === this.elements.imageModal) this.closeModal();
        });

        // Filtros
        document.getElementById('sortSelect').addEventListener('change', () => this.search());
        document.getElementById('colorFilter').addEventListener('change', () => this.search());
        document.getElementById('orientationFilter').addEventListener('change', () => this.search());
    }

    async search() {
        const query = this.elements.searchInput.value.trim();
        
        if (!query) {
            this.showError('Por favor, digite algo para pesquisar');
            return;
        }

        this.currentQuery = query;
        this.currentPage = 1;
        
        this.showLoading();
        this.clearResults();
        this.hideLoadMore();

        try {
            const data = await this.fetchImages(query, this.currentPage);
            this.displayResults(data);
            this.updateResultsInfo(data.total, query);
            
            if (data.total > data.results.length) {
                this.showLoadMore();
            }
        } catch (error) {
            this.showError('Erro ao buscar imagens: ' + error.message);
        } finally {
            this.hideLoading();
        }
    }

    async loadMore() {
        if (this.isLoading) return;

        this.currentPage++;
        this.elements.loadMoreBtn.disabled = true;
        this.elements.loadMoreBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Carregando...';

        try {
            const data = await this.fetchImages(this.currentQuery, this.currentPage);
            this.displayResults(data, true);
            
            if (data.results.length < 10) { // Unsplash retorna 10 por página
                this.hideLoadMore();
            }
        } catch (error) {
            this.showError('Erro ao carregar mais imagens');
            this.currentPage--; // Reverte em caso de erro
        } finally {
            this.elements.loadMoreBtn.disabled = false;
            this.elements.loadMoreBtn.innerHTML = '<i class="fas fa-plus"></i> Carregar Mais Imagens';
        }
    }

    async fetchImages(query, page = 1) {
        this.isLoading = true;

        const sort = document.getElementById('sortSelect').value;
        const color = document.getElementById('colorFilter').value;
        const orientation = document.getElementById('orientationFilter').value;

        let url = `${this.baseURL}/search/photos?query=${encodeURIComponent(query)}&page=${page}&per_page=12`;
        
        if (sort) url += `&order_by=${sort}`;
        if (color) url += `&color=${color}`;
        if (orientation) url += `&orientation=${orientation}`;

        const response = await fetch(url, {
            headers: {
                'Authorization': `Client-ID ${this.accessKey}`
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        this.isLoading = false;
        return await response.json();
    }

    async loadRandomImages() {
        try {
            const response = await fetch(`${this.baseURL}/photos/random?count=12`, {
                headers: {
                    'Authorization': `Client-ID ${this.accessKey}`
                }
            });

            if (response.ok) {
                const images = await response.json();
                this.displayRandomResults(images);
            }
        } catch (error) {
            console.log('Não foi possível carregar imagens aleatórias');
        }
    }

    displayResults(data, append = false) {
        if (!append) {
            this.clearResults();
        }

        if (!data.results || data.results.length === 0) {
            this.showError('Nenhuma imagem encontrada. Tente outros termos.');
            return;
        }

        data.results.forEach(photo => {
            const card = this.createImageCard(photo);
            this.elements.resultsGrid.appendChild(card);
        });
    }

    displayRandomResults(images) {
        images.forEach(photo => {
            const card = this.createImageCard(photo);
            this.elements.resultsGrid.appendChild(card);
        });
    }

    createImageCard(photo) {
        const card = document.createElement('div');
        card.className = 'image-card';
        card.innerHTML = `
            <img src="${photo.urls.regular}" alt="${photo.alt_description || 'Imagem'}">
            <div class="image-overlay">
                <div class="image-author">
                    <i class="fas fa-user"></i> ${photo.user.name}
                </div>
                <div class="image-stats">
                    <span><i class="fas fa-heart"></i> ${photo.likes}</span>
                    <span><i class="fas fa-eye"></i> ${photo.views || 0}</span>
                </div>
            </div>
        `;

        card.addEventListener('click', () => this.openModal(photo));
        return card;
    }

    openModal(photo) {
        this.elements.modalImage.src = photo.urls.regular;
        this.elements.modalImage.alt = photo.alt_description || 'Imagem';
        this.elements.modalTitle.textContent = photo.alt_description || 'Imagem sem título';
        this.elements.modalAuthor.textContent = `Por: ${photo.user.name}`;
        this.elements.modalLikes.textContent = photo.likes;
        this.elements.modalViews.textContent = photo.views || 0;
        this.elements.modalDownloads.textContent = photo.downloads || 0;
        this.elements.modalDownload.href = photo.links.download;
        
        this.elements.imageModal.classList.remove('hidden');
        document.body.style.overflow = 'hidden';
    }

    closeModal() {
        this.elements.imageModal.classList.add('hidden');
        document.body.style.overflow = 'auto';
    }

    updateResultsInfo(total, query) {
        this.elements.resultsCount.textContent = total;
        this.elements.searchTerm.textContent = query;
        this.elements.resultsInfo.classList.remove('hidden');
    }

    clearResults() {
        this.elements.resultsGrid.innerHTML = '';
        this.elements.resultsInfo.classList.add('hidden');
        this.hideError();
    }

    showLoading() {
        this.elements.loading.classList.remove('hidden');
    }

    hideLoading() {
        this.elements.loading.classList.add('hidden');
    }

    showError(message) {
        this.elements.errorMessage.textContent = message;
        this.elements.error.classList.remove('hidden');
    }

    hideError() {
        this.elements.error.classList.add('hidden');
    }

    showLoadMore() {
        this.elements.loadMoreContainer.classList.remove('hidden');
    }

    hideLoadMore() {
        this.elements.loadMoreContainer.classList.add('hidden');
    }
}

// Demo data para quando não há API key
class DemoImageSearch extends UnsplashImageSearch {
    constructor() {
        super();
        this.demoImages = this.generateDemoImages();
    }

    generateDemoImages() {
        const categories = ['nature', 'technology', 'animals', 'food', 'travel'];
        const demoData = [];
        
        for (let i = 1; i <= 24; i++) {
            const category = categories[Math.floor(Math.random() * categories.length)];
            demoData.push({
                id: i,
                urls: {
                    regular: `https://picsum.photos/400/300?random=${i}`
                },
                alt_description: `Imagem de exemplo ${i}`,
                user: {
                    name: `Fotógrafo ${i}`
                },
                likes: Math.floor(Math.random() * 1000),
                views: Math.floor(Math.random() * 5000),
                downloads: Math.floor(Math.random() * 1000),
                links: {
                    download: `https://picsum.photos/400/300?random=${i}`
                }
            });
        }
        
        return demoData;
    }

    async fetchImages(query, page = 1) {
        this.showLoading();
        
        // Simular delay de rede
        await new Promise(resolve => setTimeout(resolve, 1000));
        
        const start = (page - 1) * 12;
        const end = start + 12;
        const results = this.demoImages.slice(start, end);
        
        return {
            results: results,
            total: this.demoImages.length
        };
    }

    async loadRandomImages() {
        await new Promise(resolve => setTimeout(resolve, 500));
        this.displayRandomResults(this.demoImages.slice(0, 12));
    }
}

// Inicializar a aplicação
document.addEventListener('DOMContentLoaded', () => {
    // Use DemoImageSearch para teste sem API key
    // Para usar a API real, substitua por: new UnsplashImageSearch()
    new DemoImageSearch();
});