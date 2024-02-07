class UIManager {

    constructor() {
        this.burgerCount = 0;
    }

    increaseBurgerCount() {
        this.burgerCount++;
        this.updateBurgerCount();
    }

    updateBurgerCount() {
        document.querySelector('.burger-counter-display').textContent = `${this.burgerCount} Burgers`;
    }

    setClickEvent() {
        document.querySelector('.burger-image').addEventListener('click', () => {
            this.increaseBurgerCount();
            this.updateBurgerCount();
        })
    }
}

class GameManager {

    constructor() {
        // this.user = user;
    }

    startGame() {
        this.uiManager = new UIManager();
        this.uiManager.setClickEvent();
        this.uiManager.updateBurgerCount();
    }
}

const game = new GameManager();
game.startGame();
