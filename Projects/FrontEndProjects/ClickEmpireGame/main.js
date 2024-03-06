class UIManager {

  constructor(user) {
    this.user = user;
    this.burgerCount = 0;
  }

  increaseBurgerCount() {
    this.burgerCount++;
  }

  displayDefaultBurgerCount() {
    document.querySelector(
      ".burger-counter-display h2"
    ).textContent = `${this.burgerCount} Burgers`;
  }

  updateBurgerCount() {
    document.querySelector(
      ".burger-counter-display h2"
    ).textContent = `${this.burgerCount} Burgers`;

    this.user.updateAssets();
  }

  setClickEvent() {
    document.querySelector(".burger-image").addEventListener("click", () => {
      this.increaseBurgerCount();
      this.updateBurgerCount();
    });
  }
}

class User {
  INITIAL_ASSETS = 50000;
  MONEY_EVERY_CLICK = 25;

  constructor(name, age) {
    this.name = name;
    this.age = age;
    // ゲームを開始して経過した日にち
    this.days = 0;
    // 現在の所持資産
    this.assets = this.INITIAL_ASSETS;
  }

  // ユーザーの名前を表示する
  updateUserName() {
    document.querySelector("#user-name h1").textContent = this.name;
  }

  // ユーザーの年齢を表示する
  updateUserAge() {
    document.querySelector("#user-age h1").textContent = this.age + " yrs old";
  }

  // ユーザーのゲーム開始からの経過日数を表示する
displayDefaultDays() {
    document.querySelector("#user-elapsed-days h1").textContent =
        this.days + " days";
}

// ユーザーの所持金を表示する
displayDefaultAssets() {
    document.querySelector("#user-assets h1").textContent = this.assets + " $";
}

// ユーザーの所持金を更新する
updateAssets() {
    document.querySelector("#user-assets h1").textContent = this.assets + this.MONEY_EVERY_CLICK + " $";
    this.assets += this.MONEY_EVERY_CLICK;
}
}

class GameManager {
  constructor(user) {
    this.user = user;
}

  startGame() {
    this.uiManager = new UIManager(this.user);
    this.uiManager.setClickEvent();
    this.uiManager.displayDefaultBurgerCount();
    this.user.updateUserName();
    this.user.updateUserAge();
    this.user.displayDefaultDays();
    this.user.displayDefaultAssets();
  }
}

const game = new GameManager(new User("Taro", 20));
game.startGame();
