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

  displayCanPurchaseItems(items) {
    const displayItems = items.filter((item) => item.price <= this.user.assets);
    console.log(displayItems);
    const itemArea = document.getElementById("item-list");
    let itemInfo = document.createElement("div");
    itemInfo.classList.add("my-3", "item-info-display", "bg-dark");
    for (let i = 0; i < displayItems.length; i++) {
      itemInfo.innerHTML += `
          <div
          class="container row d-flex justify-content-between align-items-center p-3 item-info-display my-3"
          >
          <img
            class="card-img-top img-small"
            src="${displayItems[i].image}"
            alt=""
          />
          <div
            class="d-flex flex-column mx-5 justify-content-around align-items-start"
          >
            <div class="item-name">
            <h1>${displayItems[i].name}</h1>
            </div>
            <div class="item-price">
            <h2>$${displayItems[i].price}</h2>
            </div>
          </div>
          <div class="item-money">
            <h2>+$${displayItems[i].revenuePerSecond} / sec</h2>
          </div>
          <div class="count">
            <h1>0</h1>
          </div>
          </div>
        </div>
      `;
    }

    itemArea.appendChild(itemInfo);
  }
}

class User {
  INITIAL_ASSETS = 100000000;
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
    document.querySelector("#user-assets h1").textContent =
      this.assets + this.MONEY_EVERY_CLICK + " $";
    this.assets += this.MONEY_EVERY_CLICK;
  }
}

class Item {
  constructor(name, type, maxPurchase, price, revenuePerSecond, image) {
    this.name = name;
    this.type = type;
    this.maxPurchase = maxPurchase;
    this.price = price;
    this.revenuePerSecond = revenuePerSecond;
    this.image = image;
  }
}

class GameManager {
  constructor(user) {
    this.user = user;
    this.items = [];
    this.initializeItems();
  }

  initializeItems() {
    this.items.push(new Item("Flip machine", "ability", 500, 15000, 25, "image/house.webp"));
    this.items.push(new Item("Lemonade Stand", "realEstate", 1000, 30000, 30, "image/house.webp"));
    this.items.push(new Item("Ice Cream Truck", "realEstate", 500, 100000, 120, "image/house.webp"));
    this.items.push(new Item("House", "realEstate", 100, 20000000, 32000, "image/house.webp"));
    this.items.push(new Item("TownHouse", "realEstate", 100, 40000000, 64000, "image/house.webp"));
    this.items.push(new Item("Mansion", "realEstate", 20, 250000000, 500000, "image/house.webp"));
    this.items.push(new Item("Industrial Space", "realEstate", 10, 1000000000, 2200000, "image/house.webp"));
    this.items.push(new Item("Hotel Skyscraper", "realEstate", 5, 10000000000, 25000000, "image/house.webp"));
    this.items.push(new Item("Bullet-Speed Sky Railway", "realEstate", 1, 10000000000000, 30000000, "image/house.webp"));
  }

  startGame() {
    this.uiManager = new UIManager(this.user);
    this.uiManager.setClickEvent();
    this.uiManager.displayDefaultBurgerCount();
    this.user.updateUserName();
    this.user.updateUserAge();
    this.user.displayDefaultDays();
    this.user.displayDefaultAssets();
    this.uiManager.displayCanPurchaseItems(this.items);
  }
}

const game = new GameManager(new User("Taro", 20));
game.startGame();
