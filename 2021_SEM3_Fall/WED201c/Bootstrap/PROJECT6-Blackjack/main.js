let firstCard, secondCard;
let cards = [];
let sum = 0;
let isAlive = true;
let hasBlackJack = false;
let cardEl = document.querySelector("#card-el");
let sumEl = document.querySelector("#sum-el");
let msgEl = document.querySelector("#msg-el");

function genCardNumber() {
    let n = Math.floor(Math.random() * 13) + 1;
    if (n === 1) return 11;
    else return Math.min(n, 10);
}

function startGame() {
    isAlive = true;
    hasBlackJack = false;   
    firstCard = genCardNumber();
    secondCard = genCardNumber();
    cards = [firstCard, secondCard];   
    
    renderGame();
}

function renderGame() {
    cardEl.innerHTML = "Cards: ";
    sum = 0;
    for (let i = 0; i < cards.length; ++i) {
        cardEl.innerHTML += cards[i] + " ";
        sum += cards[i];
    }
    sumEl.innerHTML = "Sum: " + sum;
    if (sum <= 20) {
        msgEl.innerHTML = "Do you want to draw a new card? 🙂";
    } else if (sum === 21) {
        msgEl.innerHTML = "Wohoo! You've got Blackjack! 🥳";
        hasBlackJack = true;
    } else {
        msgEl.innerHTML = "You're out of the game! 😭";
        isAlive = false;
    }
}

function addCard() {
    if (isAlive === true && hasBlackJack === false) {
        let newCard = genCardNumber();
        cards.push(newCard);
        renderGame();
    }
}