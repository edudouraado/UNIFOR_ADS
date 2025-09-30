// Deck of Cards API Base URL
const DECK_API = 'https://deckofcardsapi.com/api/deck';

// Estado global do jogo
let gameState = {
    currentGame: 'blackjack',
    deckId: null,
    blackjack: {
        playerCards: [],
        dealerCards: [],
        playerScore: 0,
        dealerScore: 0,
        balance: 1000,
        currentBet: 50,
        gameActive: false
    },
    war: {
        playerDeck: [],
        dealerDeck: [],
        playerCard: null,
        dealerCard: null,
        wins: 0,
        rounds: 0
    },
    memory: {
        cards: [],
        flippedCards: [],
        matchedPairs: 0,
        moves: 0,
        gameActive: false
    }
};

// Inicialização
document.addEventListener('DOMContentLoaded', function() {
    initializeApp();
});

async function initializeApp() {
    await createNewDeck();
    setupEventListeners();
    updateUI();
}

function setupEventListeners() {
    // Navegação entre jogos
    document.querySelectorAll('.game-btn').forEach(btn => {
        btn.addEventListener('click', (e) => {
            const game = e.target.getAttribute('data-game');
            switchGame(game);
        });
    });

    // Blackjack
    document.querySelectorAll('.bet-btn').forEach(btn => {
        btn.addEventListener('click', (e) => {
            const amount = parseInt(e.target.getAttribute('data-amount'));
            setBetAmount(amount);
        });
    });

    document.getElementById('deal-btn').addEventListener('click', startBlackjack);
    document.getElementById('hit-btn').addEventListener('click', playerHit);
    document.getElementById('stand-btn').addEventListener('click', playerStand);
    document.getElementById('double-btn').addEventListener('click', playerDouble);

    // Guerra
    document.getElementById('war-battle-btn').addEventListener('click', playWarRound);

    // Memória
    document.getElementById('start-memory').addEventListener('click', startMemoryGame);
    document.getElementById('reset-memory').addEventListener('click', resetMemoryGame);
}

// 🃏 Funções do Baralho
async function createNewDeck() {
    try {
        const response = await fetch(`${DECK_API}/new/shuffle/?deck_count=1`);
        const data = await response.json();
        gameState.deckId = data.deck_id;
        console.log('Novo baralho criado:', gameState.deckId);
    } catch (error) {
        console.error('Erro ao criar baralho:', error);
    }
}

async function drawCards(count) {
    if (!gameState.deckId) await createNewDeck();
    
    try {
        const response = await fetch(`${DECK_API}/${gameState.deckId}/draw/?count=${count}`);
        const data = await response.json();
        return data.cards;
    } catch (error) {
        console.error('Erro ao comprar cartas:', error);
        return [];
    }
}

function getCardValue(card) {
    const value = card.value;
    if (['JACK', 'QUEEN', 'KING'].includes(value)) return 10;
    if (value === 'ACE') return 11; // No blackjack, Ás vale 11 ou 1
    return parseInt(value);
}

function calculateScore(cards) {
    let score = 0;
    let aces = 0;

    cards.forEach(card => {
        const value = getCardValue(card);
        score += value;
        if (card.value === 'ACE') aces++;
    });

    // Ajustar Áses se necessário
    while (score > 21 && aces > 0) {
        score -= 10;
        aces--;
    }

    return score;
}

// 🎮 Blackjack
function setBetAmount(amount) {
    if (amount <= gameState.blackjack.balance) {
        gameState.blackjack.currentBet = amount;
        updateUI();
    }
}

async function startBlackjack() {
    if (gameState.blackjack.gameActive) return;
    if (gameState.blackjack.currentBet > gameState.blackjack.balance) {
        showMessage('Saldo insuficiente!', 'error');
        return;
    }

    gameState.blackjack.gameActive = true;
    gameState.blackjack.playerCards = [];
    gameState.blackjack.dealerCards = [];

    // Comprar cartas iniciais
    const cards = await drawCards(4);
    if (cards.length < 4) {
        showMessage('Erro ao comprar cartas', 'error');
        return;
    }

    gameState.blackjack.playerCards = [cards[0], cards[1]];
    gameState.blackjack.dealerCards = [cards[2], cards[3]];

    gameState.blackjack.playerScore = calculateScore(gameState.blackjack.playerCards);
    gameState.blackjack.dealerScore = calculateScore([gameState.blackjack.dealerCards[0]]); // Só mostra uma carta do dealer

    updateUI();
    enableGameControls(true);

    // Verificar blackjack natural
    if (gameState.blackjack.playerScore === 21) {
        endBlackjack('blackjack');
    }
}

async function playerHit() {
    const cards = await drawCards(1);
    if (cards.length === 0) return;

    gameState.blackjack.playerCards.push(cards[0]);
    gameState.blackjack.playerScore = calculateScore(gameState.blackjack.playerCards);

    if (gameState.blackjack.playerScore > 21) {
        endBlackjack('bust');
    }

    updateUI();
}

async function playerStand() {
    await dealerPlay();
}

async function playerDouble() {
    if (gameState.blackjack.currentBet * 2 > gameState.blackjack.balance) {
        showMessage('Saldo insuficiente para dobrar!', 'error');
        return;
    }

    gameState.blackjack.currentBet *= 2;
    await playerHit();
    
    if (gameState.blackjack.playerScore <= 21) {
        await dealerPlay();
    }
}

async function dealerPlay() {
    enableGameControls(false);

    // Revelar carta escondida do dealer
    gameState.blackjack.dealerScore = calculateScore(gameState.blackjack.dealerCards);

    // Dealer compra até ter 17 ou mais
    while (gameState.blackjack.dealerScore < 17) {
        const cards = await drawCards(1);
        if (cards.length === 0) break;

        gameState.blackjack.dealerCards.push(cards[0]);
        gameState.blackjack.dealerScore = calculateScore(gameState.blackjack.dealerCards);
        updateUI();
        await sleep(1000); // Delay para drama
    }

    determineBlackjackWinner();
}

function determineBlackjackWinner() {
    const playerScore = gameState.blackjack.playerScore;
    const dealerScore = gameState.blackjack.dealerScore;

    if (dealerScore > 21) {
        endBlackjack('dealer_bust');
    } else if (playerScore > dealerScore) {
        endBlackjack('win');
    } else if (playerScore < dealerScore) {
        endBlackjack('lose');
    } else {
        endBlackjack('push');
    }
}

function endBlackjack(result) {
    gameState.blackjack.gameActive = false;
    enableGameControls(false);

    let message = '';
    let messageType = 'info';

    switch (result) {
        case 'blackjack':
            gameState.blackjack.balance += Math.floor(gameState.blackjack.currentBet * 2.5);
            message = 'Blackjack! Você ganhou 2.5x sua aposta!';
            messageType = 'success';
            break;
        case 'win':
            gameState.blackjack.balance += gameState.blackjack.currentBet * 2;
            message = 'Você venceu!';
            messageType = 'success';
            break;
        case 'push':
            gameState.blackjack.balance += gameState.blackjack.currentBet;
            message = 'Empate! Sua aposta foi devolvida.';
            messageType = 'info';
            break;
        case 'lose':
            message = 'Dealer venceu!';
            messageType = 'error';
            break;
        case 'bust':
            message = 'Estourou! Mais de 21 pontos.';
            messageType = 'error';
            break;
        case 'dealer_bust':
            gameState.blackjack.balance += gameState.blackjack.currentBet * 2;
            message = 'Dealer estourou! Você venceu!';
            messageType = 'success';
            break;
    }

    showMessage(message, messageType);
    updateUI();
}

// ⚔️ Guerra
async function initializeWar() {
    if (!gameState.deckId) await createNewDeck();
    
    // Embaralhar e dividir o baralho
    const response = await fetch(`${DECK_API}/${gameState.deckId}/shuffle/`);
    const cards = await drawCards(52);
    
    gameState.war.playerDeck = cards.slice(0, 26);
    gameState.war.dealerDeck = cards.slice(26);
    gameState.war.wins = 0;
    gameState.war.rounds = 0;
}

async function playWarRound() {
    if (gameState.war.playerDeck.length === 0 || gameState.war.dealerDeck.length === 0) {
        await initializeWar();
    }

    const playerCard = gameState.war.playerDeck.shift();
    const dealerCard = gameState.war.dealerDeck.shift();

    gameState.war.playerCard = playerCard;
    gameState.war.dealerCard = dealerCard;
    gameState.war.rounds++;

    displayWarCards(playerCard, dealerCard);

    const playerValue = getWarValue(playerCard);
    const dealerValue = getWarValue(dealerCard);

    if (playerValue > dealerValue) {
        gameState.war.wins++;
        gameState.war.playerDeck.push(playerCard, dealerCard);
        showWarMessage('Você venceu esta rodada!', 'success');
    } else if (dealerValue > playerValue) {
        gameState.war.dealerDeck.push(playerCard, dealerCard);
        showWarMessage('CPU venceu esta rodada!', 'error');
    } else {
        // Guerra - empate
        gameState.war.playerDeck.push(playerCard);
        gameState.war.dealerDeck.push(dealerCard);
        showWarMessage('Guerra! Empate nesta rodada.', 'info');
    }

    updateUI();

    // Verificar fim de jogo
    if (gameState.war.playerDeck.length === 0) {
        showWarMessage('Fim de jogo! CPU venceu a guerra.', 'error');
    } else if (gameState.war.dealerDeck.length === 0) {
        showWarMessage('Parabéns! Você venceu a guerra!', 'success');
    }
}

function getWarValue(card) {
    const values = {
        '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10,
        'JACK': 11, 'QUEEN': 12, 'KING': 13, 'ACE': 14
    };
    return values[card.value];
}

// 🧠 Memória (implementação básica)
async function startMemoryGame() {
    // Implementação simplificada para demonstração
    showMessage('Jogo da Memória em desenvolvimento!', 'info');
}

function resetMemoryGame() {
    // Implementação simplificada para demonstração
    showMessage('Jogo da Memória em desenvolvimento!', 'info');
}

// 🎯 Funções de UI
function switchGame(game) {
    gameState.currentGame = game;
    
    // Atualizar botões
    document.querySelectorAll('.game-btn').forEach(btn => {
        btn.classList.toggle('active', btn.getAttribute('data-game') === game);
    });
    
    // Atualizar conteúdo
    document.querySelectorAll('.game-container').forEach(container => {
        container.classList.toggle('active', container.id === game);
    });

    // Inicializar jogos específicos
    if (game === 'war' && gameState.war.playerDeck.length === 0) {
        initializeWar();
    }
}

function updateUI() {
    // Blackjack
    document.getElementById('balance').textContent = gameState.blackjack.balance;
    document.getElementById('bet-amount').textContent = gameState.blackjack.currentBet;
    document.getElementById('player-score').textContent = gameState.blackjack.playerScore;
    document.getElementById('dealer-score').textContent = gameState.blackjack.gameActive ? 
        calculateScore([gameState.blackjack.dealerCards[0]]) : gameState.blackjack.dealerScore;

    // Atualizar cartas do blackjack
    updateBlackjackCards();

    // Guerra
    document.getElementById('war-wins').textContent = gameState.war.wins;
    document.getElementById('war-rounds').textContent = gameState.war.rounds;
    document.getElementById('player-deck-count').textContent = gameState.war.playerDeck.length;
    document.getElementById('dealer-deck-count').textContent = gameState.war.dealerDeck.length;
}

function updateBlackjackCards() {
    const playerContainer = document.getElementById('player-cards');
    const dealerContainer = document.getElementById('dealer-cards');

    playerContainer.innerHTML = '';
    dealerContainer.innerHTML = '';

    // Cartas do jogador
    gameState.blackjack.playerCards.forEach(card => {
        playerContainer.appendChild(createCardElement(card));
    });

    // Cartas do dealer
    gameState.blackjack.dealerCards.forEach((card, index) => {
        if (index === 0 && gameState.blackjack.gameActive) {
            // Primeira carta virada para baixo durante o jogo
            dealerContainer.appendChild(createCardElement(null, true));
        } else {
            dealerContainer.appendChild(createCardElement(card));
        }
    });
}

function displayWarCards(playerCard, dealerCard) {
    const playerSlot = document.getElementById('war-player-card');
    const dealerSlot = document.getElementById('war-dealer-card');
    const battleContainer = document.getElementById('war-battle-cards');

    playerSlot.innerHTML = '';
    dealerSlot.innerHTML = '';
    battleContainer.innerHTML = '';

    if (playerCard) {
        battleContainer.appendChild(createCardElement(playerCard));
    }
    if (dealerCard) {
        battleContainer.appendChild(createCardElement(dealerCard));
    }
}

function createCardElement(card, isBack = false) {
    const cardDiv = document.createElement('div');
    cardDiv.className = 'card';
    
    if (isBack) {
        cardDiv.classList.add('back');
        cardDiv.textContent = '🂠';
    } else if (card) {
        const isRed = ['HEARTS', 'DIAMONDS'].includes(card.suit);
        if (isRed) cardDiv.classList.add('red');
        
        const suitSymbol = getSuitSymbol(card.suit);
        cardDiv.textContent = `${getValueSymbol(card.value)}${suitSymbol}`;
    }
    
    return cardDiv;
}

function getSuitSymbol(suit) {
    const symbols = {
        'HEARTS': '♥',
        'DIAMONDS': '♦',
        'CLUBS': '♣',
        'SPADES': '♠'
    };
    return symbols[suit] || '?';
}

function getValueSymbol(value) {
    const symbols = {
        'ACE': 'A',
        'KING': 'K',
        'QUEEN': 'Q',
        'JACK': 'J'
    };
    return symbols[value] || value;
}

function enableGameControls(enabled) {
    document.getElementById('hit-btn').disabled = !enabled;
    document.getElementById('stand-btn').disabled = !enabled;
    document.getElementById('double-btn').disabled = !enabled || 
        (gameState.blackjack.currentBet * 2 > gameState.blackjack.balance);
}

function showMessage(text, type) {
    const messageEl = document.getElementById('game-message');
    messageEl.textContent = text;
    messageEl.className = `message ${type}`;
    
    setTimeout(() => {
        messageEl.textContent = '';
        messageEl.className = 'message';
    }, 3000);
}

function showWarMessage(text, type) {
    const messageEl = document.getElementById('war-message');
    messageEl.textContent = text;
    messageEl.className = `message ${type}`;
}

// Utilitários
function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}