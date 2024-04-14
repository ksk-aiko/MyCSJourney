class UIManager {
    constructor(user) {
      this.user = user;
      this.burgerCount = 0;
      this.currentView = "itemList";
      this.delegateItemEvents();
    }

    initializeUI(user, items) {
        this.displayBurgerCount();
        this.displayUserInfo(user);
        this.displayCanPurchaseItems(items);
    }
  
    increaseBurgerCount() {
      this.burgerCount++;
      this.displayBurgerCount();
    }

    displayUserInfo(user) {
        user.displayUserInfo();
    }
  
    displayBurgerCount() {
      document.querySelector(".burger-counter-display h2").textContent = `${this.burgerCount} Burgers`;
    }
  
    delegateItemEvents() {
      const itemList = document.getElementById("item-list");
      itemList.addEventListener("click", event => {
        const target = event.target;
        const itemDisplay = target.closest(".item-info-display");
        if (!itemDisplay) return;
  
        const itemIndex = itemDisplay.getAttribute("data-index");
        if (target.id === "purchase-button") {
          this.handlePurchase(itemIndex);
        } else if (target.id === "go-back-button") {
          this.displayCanPurchaseItems();
        } else {
          this.setItemDetail(itemIndex);
        }
      });
    }
  
    setItemDetail(itemIndex) {
      const item = game.ITEMS[itemIndex];
      const itemList = document.getElementById("item-list");
      itemList.innerHTML = `
        <div class="row d-flex justify-content-around">
          <div class="col-8 d-flex flex-column justify-content-around">
            <h1><strong>${item.name}</strong></h1>
            <h3>Max purchase: ${item.maxPurchase}</h3>
            <h3>Price: $${item.price}</h3>
            <h3>Get ${item.revenuePerSecond} extra yen per second</h3>
          </div>
          <div class="col-4 d-flex justify-content-center align-items-center">
            <img class="img-large" src=${item.image}>
          </div>
        </div>
      `;
      this.currentView = "itemDetail";
    }
  
    displayCanPurchaseItems() {
      const items = Object.values(game.items).filter(item => item.price <= this.user.assets);
      const itemList = document.getElementById("item-list");
      itemList.innerHTML = items.map(item => `
        <div class="item-info-display" data-index="${item.name}">
          <h1>${item.name}</h1>
          <h2>$${item.price}</h2>
          <h2>+$${item.revenuePerSecond} / sec</h2>
        </div>
      `).join('');
      this.currentView = "itemList";
    }
  
    handlePurchase(itemIndex) {
      const item = game.ITEMS[itemIndex];
      const purchaseNumberInput = document.getElementById("purchase-number");
      const purchaseNumber = parseInt(purchaseNumberInput.value);
      const totalPurchasePrice = purchaseNumber * item.price;
  
      if (this.user.assets >= totalPurchasePrice && (this.user.assetsHeld[itemIndex] || 0) + purchaseNumber <= item.maxPurchase) {
        this.user.assets -= totalPurchasePrice;
        this.user.assetsHeld[itemIndex] = (this.user.assetsHeld[itemIndex] || 0) + purchaseNumber;
        this.displayCanPurchaseItems();
        alert("Purchase successful!");
      } else {
        alert("Not enough funds or max purchase limit reached.");
      }
    }
  }
  
  class User {
    static INITIAL_ASSETS = 50000;
    static MONEY_EVERY_CLICK = 25;
  
    constructor(name, age) {
      this.name = name;
      this.age = age;
      this.days = 0;
      this.assets = User.INITIAL_ASSETS;
      this.assetsHeld = {};
    }
  
    displayUserInfo() {
      document.querySelector("#user-name h1").textContent = this.name;
      document.querySelector("#user-age h1").textContent = `${this.age} years old`;
      document.querySelector("#user-elapsed-days h1").textContent = `${this.days} days`;
      document.querySelector("#user-assets h1").textContent = `${this.assets} $`;
    }
  
    updateUserInfo({ age, days, assets }) {
      if (age !== undefined) {
        this.age = age;
        document.querySelector("#user-age h1").textContent = `${this.age} years old`;
      }
      if (days !== undefined) {
        this.days = days;
        document.querySelector("#user-elapsed-days h1").textContent = `${this.days} days`;
      }
      if (assets !== undefined) {
        this.assets += assets;
        document.querySelector("#user-assets h1").textContent = `${this.assets} $`;
      }
    }
  
    incrementDay() {
      this.updateUserInfo({ days: this.days + 1 });
      if (this.days % 365 === 0) {
        this.updateUserInfo({ age: this.age + 1 });
      }
    }
  
    addAssets(amount) {
      this.updateUserInfo({ assets: amount });
    }
  
    addAssetsHeld(assetName, purchaseCount) {
      this.assetsHeld[assetName] = (this.assetsHeld[assetName] || 0) + purchaseCount;
      this.displayUserInfo();
    }
  
    canGetAssetsList() {
      return Object.keys(this.assetsHeld);
    }
  
    calculateAssetsRevenuePerSecond() {
      return Object.entries(this.assetsHeld).reduce((totalRevenue, [assetName, count]) => {
        const revenuePerSecond = game.ITEMS[assetName]?.revenuePerSecond || 0;
        return totalRevenue + (revenuePerSecond * count);
      }, 0);
    }
  
    calculateETFassetsRevenuePerSecond() {
      let totalRevenue = 0;
      if (this.assetsHeld["ETF Stock"]) {
        totalRevenue += this.assetsHeld["ETF Stock"] * (game.ITEMS["ETF Stock"].price * 0.001);
      }
      if (this.assetsHeld["ETF Bonds"]) {
        totalRevenue += this.assetsHeld["ETF Bonds"] * (game.ITEMS["ETF Bonds"].price * 0.0007);
      }
      return totalRevenue;
    }
  }
  
  class Item {
    #name;
    #type;
    #maxPurchase;
    #price;
    #revenuePerSecond;
    #image;
  
    constructor(name, type, maxPurchase, price, revenuePerSecond, image) {
      this.#name = name;
      this.#type = type;
      this.#maxPurchase = maxPurchase;
      this.#price = price;
      this.#revenuePerSecond = revenuePerSecond;
      this.#image = image;
    }
  
    getName() {
      return this.#name;
    }
  
    getType() {
      return this.#type;
    }
  
    getMaxPurchase() {
      return this.#maxPurchase;
    }
  
    getPrice() {
      return this.#price;
    }
  
    getRevenuePerSecond() {
      return this.#revenuePerSecond;
    }
  
    getImage() {
      return this.#image;
    }
  
    setPrice(newPrice) {
      if (newPrice > 0) {
        this.#price = newPrice;
      }
    }
  
    canBePurchased(currentAssets) {
      return currentAssets >= this.#price && this.#maxPurchase > 0;
    }
  }
  
  class TimeManager {
    constructor(user, uiManager, items) {
      this.user = user;
      this.uiManager = uiManager;
      this.items = items;
      this.gameInterval = null;
      this.startDate = new Date();  // 現在の日時を保持する
    }
  
    // ゲームの1日ごとのサイクルを開始する
    startDayCycle() {
      this.gameInterval = setInterval(() => {
        this.updateAssets();
        this.incrementDay();
        this.checkCanPurchaseItems();
      }, 1500); // 1.5秒ごとに更新
    }
  
    // ユーザーの資産を更新する
    updateAssets() {
      const assetsFromInvestments = this.user.calculateAssetsRevenuePerSecond();
      const assetsFromETF = this.user.calculateETFassetsRevenuePerSecond();
      this.user.addAssets(assetsFromInvestments + assetsFromETF);
    }
  
    // 日数を更新し、365日ごとに年齢を更新する
    incrementDay() {
      const currentDate = new Date();
      const oneDay = 24 * 60 * 60 * 1000; // 1日のミリ秒
      if (currentDate - this.startDate >= oneDay) {
        this.user.incrementDay();
        this.startDate = currentDate;  // 最終更新日をリセット
      }
    }
  
    // 購入可能なアイテムをチェックし、UIを更新する
    checkCanPurchaseItems() {
      const canPurchaseItems = this.items.filter(
        item => item.canBePurchased(this.user.assets)
      );
      if (canPurchaseItems.length > 0) {
        this.uiManager.displayCanPurchaseItems(canPurchaseItems);
      }
    }
  
    // ゲームの時間進行を停止する
    stopDayCycle() {
      if (this.gameInterval) {
        clearInterval(this.gameInterval);  // setIntervalをクリア
        this.gameInterval = null;
      }
    }
  }
  
  class SaveManager {
    constructor(user) {
      this.user = user;
    }
  
    // セーブデータの構造を取得する
    getSaveData() {
      return {
        name: this.user.name,
        age: this.user.age,
        days: this.user.days,
        assets: this.user.assets,
        assetsHeld: this.user.assetsHeld
      };
    }
  
    // ゲームの状態をローカルストレージに保存
    saveData() {
      try {
        const saveData = JSON.stringify(this.getSaveData());
        localStorage.setItem("saveData", saveData);
        console.log("Game saved successfully.");
      } catch (error) {
        console.error("Failed to save game:", error);
      }
    }
    
    // ローカルストレージからゲームの状態を読み込む
    loadSaveData() {
      try {
        const saveData = localStorage.getItem("saveData");
        if (!saveData) throw new Error("No save data found.");
    
        const parsedData = JSON.parse(saveData);
        this.validateSaveData(parsedData);
        this.applySaveData(parsedData);
        console.log("Game loaded successfully.");
      } catch (error) {
        console.error("Failed to load game:", error);
      }
    }
  
    // セーブデータの検証
    validateSaveData(data) {
      if (typeof data.name !== 'string' ||
          typeof data.age !== 'number' ||
          typeof data.days !== 'number' ||
          typeof data.assets !== 'number' ||
          !data.assetsHeld || typeof data.assetsHeld !== 'object') {
        throw new Error("Invalid save data.");
      }
      // Further validation could include checking each key in assetsHeld corresponds to valid item names
    }
  
    // セーブデータをユーザー情報に適用
    applySaveData(data) {
      this.user.name = data.name;
      this.user.age = data.age;
      this.user.days = data.days;
      this.user.assets = data.assets;
      this.user.assetsHeld = data.assetsHeld;
    }
  }

  class GameManager {
    constructor(user, uiManager, timeManager, items) {
      this.user = user;
      this.uiManager = uiManager;
      this.timeManager = timeManager;
      this.items = items;
    }
  
    initializeGame() {
      this.loadItems();
      this.uiManager.initializeUI(this.user, this.items);
      this.timeManager.startDayCycle();
      this.user.displayUserInfo();
      this.uiManager.displayCanPurchaseItems(this.items);
    }
  
    loadItems() {
      // アイテムデータの読み込みと初期化
      this.items = {
        "Flip machine": new Item("Flip machine", "ability", 500, 15000, 2500, "image/FlipMachine.webp"),
        "Lemonade Stand": new Item("Lemonade Stand", "realEstate", 1000, 30000, 30, "image/LemonadeStand.webp"),
        "Ice Cream Truck": new Item("Ice Cream Truck", "realEstate", 500, 100000, 120, "image/iceCream.webp"),
        "House": new Item("House", "realEstate", 100, 20000000, 32000, "image/house.webp"),
        "TownHouse": new Item("TownHouse", "realEstate", 100, 40000000, 64000, "image/townHouse.webp"),
        "Mansion": new Item("Mansion", "realEstate", 20, 250000000, 500000, "image/mansion.webp"),
        "Industrial Space": new Item("Industrial Space", "realEstate", 10, 1000000000, 2200000, "image/industrialSpace.webp"),
        "Hotel Skyscraper": new Item("Hotel Skyscraper", "realEstate", 5, 10000000000, 25000000, "image/hotelSkyScraper.webp"),
        "Bullet-Speed Sky Railway": new Item("Bullet-Speed Sky Railway", "realEstate", 1, 10000000000000, 30000000, "image/BulletSpeedSkyRailway.webp"),
        "ETF Stock": new Item("ETF Stock", "stock", Infinity, 300000, "0.1% of total purchase price", "image/ETFStock.webp"),
        "ETF Bonds": new Item("ETF Bonds", "stock", Infinity, 300000, "0.07% of total purchase price", "image/ETFBonds.webp")
      };
    }
  
    startGame() {
      this.initializeGame();
      console.log("Game started successfully.");
    }
  
    stopGame() {
      this.timeManager.stopDayCycle();
      console.log("Game has been stopped.");
    }
  }
  
  // ゲームを開始する
    const user = new User("Taro", 18);
    const uiManager = new UIManager(user);
    const timeManager = new TimeManager(user, uiManager, []);
    const game = new GameManager(user, uiManager, timeManager, []);
    game.startGame();
  