class UIManager {
  constructor(user) {
    this.user = user;
    this.burgerCount = 0;
    this.currentView = "itemList"
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

    this.user.updateAssetsByBurgerClick();
  }

  setBurgerClickEvent() {
    document.querySelector(".burger-image").addEventListener("click", () => {
      this.increaseBurgerCount();
      this.updateBurgerCount();
    });
  }

  setItemClickEvent(items) {
    // 各アイテムにクリックイベントを設定
    const displayedItems = document.querySelectorAll(".item-info-display");
    displayedItems.forEach((item) => {
      item.addEventListener("click", () => {
        const itemIndex = item.getAttribute("data-index");
        // item-listの要素を取得
        const itemList = document.getElementById("item-list");
        // 取得した要素のHTMLを初期化
        itemList.innerHTML = "";
        itemList.innerHTML = `
          <div class="row d-flex justify-content-around">
            <div class="col-8 d-flex flex-column justify-content-around">
                <h1><strong>${itemIndex}</strong></h1>
                <h3>Max purchase: ${items[itemIndex].maxPurchase == Infinity ? "♾️" : items[itemIndex].maxPurchase}</h3>
                <h3>Price: $${items[itemIndex].price}</h3>
                <h3>Get ${items[itemIndex].revenuePerSecond} extra yen per second </h3>
            </div>
            <div class="col-4 d-flex justify-content-center align-items-center">
              <img class="img-large" src=${items[itemIndex].image}>
            </div>
          </div>
            <div class="row my-5">
              <div class="col-12 d-flex justify-content-start">
                <h3>How many would you like to purchase?</h3>
              </div>
              <form class="col-12">
                <div class="form-row">
                  <div class="form-group col-12">
                    <input type="text" class="form-control form-control-lg" id="purchase-number" style="text-align:right;">
                  </div>
                </div>
              <div class="total-purchase-price col-12 d-flex justify-content-end">
                  <h3>Total: $0</h3>
              </div>
            </div>
            <div class="row d-flex justify-content-around">
              <div class="col-6">
                <button type="button" class="btn btn-outline-primary btn-lg bg-white w-100" id="go-back-button"><strong>Go Back</strong></button>
              </div>
              <div class="col-6">
                <button type="submit" class="btn btn-primary btn-lg w-100" id="purchase-button"><strong>Purchase</strong></button>
              </div>
            </div>
              </form>
        `;

        this.currentView = "itemDetail";

        // 購入数の入力欄に入力があった場合のイベントを設定
        document
          .getElementById("purchase-number")
          .addEventListener("input", (event) => {
            const purchaseNumber = parseInt(event.target.value);
            const itemPrice = items[itemIndex].price;

            if (!isNaN(purchaseNumber)) {
              document.querySelector(
                ".total-purchase-price h3"
              ).textContent = `Total: $${purchaseNumber * itemPrice}`;
            } else {
              document.querySelector(
                ".total-purchase-price h3"
              ).textContent = `Total: $0`;
            }
          });
        // Backボタンが押された場合のイベントを設定
        document
          .getElementById("go-back-button")
          .addEventListener("click", (event) => {
            event.preventDefault();
            this.displayCanPurchaseItems(items);
            this.currentView = "itemList";
          });

        // 購入ボタンが押された場合のイベントを設定
        document.getElementById('purchase-button').addEventListener("click", (event) => {
          event.preventDefault();
          const purchaseNumber = parseInt(
            document.getElementById("purchase-number").value
          );
          const itemPrice = items[itemIndex].price;
          const totalPurchasePrice = purchaseNumber * itemPrice;
          
          if (this.user.assetsHeld[itemIndex] >= items[itemIndex].maxPurchase) {
            alert("You have reached the maximum purchase limit!");
            return;
          }
          if (this.user.assets >= totalPurchasePrice) {
              this.user.assets -= totalPurchasePrice;
              this.user.updateAssets();
              this.user.addAssetsHeld(itemIndex, purchaseNumber);
              // 購入したアイテムが"ETF Stock"の場合、priceを10%上昇させる（これを購入数分繰り返す）
              if (itemIndex == "ETF Stock") {
                for (let i = 0; i < purchaseNumber; i++) {
                  items[itemIndex].setPrice(parseInt(items[itemIndex].price * 1.1));
                }
              }
              this.displayCanPurchaseItems(items);
            } else {
              alert("You don't have enough money!");
            }

            this.currentView = "itemList";
        });
      });
    });
  }

  setButtonClickEvent() {
    document.getElementById("save-button").addEventListener("click", () => {
      // ユーザーに本当に保存してもよいか確認する
      if (!confirm("ゲームデータを保存します。よろしいですか？")) {
        return;
      }

      const saveManager = new SaveManager(this.user);
      saveManager.saveData();
    });

    document.getElementById("load-button").addEventListener("click", () => {
      // ユーザーに本当にロードしてもよいか確認する
      if (!confirm("ゲームデータをロードします。よろしいですか？")) {
        return;
      }
      
      const saveManager = new SaveManager(this.user);
      saveManager.loadSaveData();
      this.user.displayDefaultUserName();
      this.user.displayDefaultUserAge();
      this.user.displayDefaultDays();
      this.user.displayDefaultAssets();
      this.displayCanPurchaseItems(game.ITEMS);
    }
    );
  }

  displayCanPurchaseItems(items) {
    const displayItems = Object.values(items).filter(
      (item) => item.price <= this.user.assets
    );
    const itemArea = document.getElementById("item-list");
    itemArea.innerHTML = "";
    let itemInfo = document.createElement("div");
    itemInfo.classList.add("my-3", "bg-dark", "item-area");
    itemInfo.innerHTML = `
    <div class="d-flex justify-content-center">
      <h1>Items you can purchase</h1>
    </div>
    `
    ;
    for (let i = 0; i < displayItems.length; i++) {
      const currentItem = displayItems[i];
      itemInfo.innerHTML += `
          <div
          class="container row d-flex justify-content-between align-items-center p-3 item-info-display my-3"
          data-index="${currentItem.name}"
          >
          <img
            class="card-img-top img-small"
            src="${currentItem.image}"
            alt=""
          />
          <div
            class="d-flex flex-column mx-5 justify-content-around align-items-start"
          >
            <div class="item-name">
            <h1>${currentItem.name}</h1>
            </div>
            <div class="item-price">
            <h2>$${currentItem.price}</h2>
            </div>
          </div>
          <div class="item-money">
            <h2>+$${currentItem.revenuePerSecond} / sec</h2>
          </div>
          <div class="count">
            <h1>${this.user.assetsHeld[currentItem.name] || 0}</h1>
          </div>
          </div>
        </div>
      `;
    }
    itemArea.appendChild(itemInfo);

    this.setItemClickEvent(items);
  }
}

class User {
  INITIAL_ASSETS = 50000;
  MONEY_EVERY_CLICK = 25;
  // 保有資産のリスト(最初は何も持っていない)
  assetsHeld = {};

  constructor(name, age) {
    this.name = name;
    this.age = age;
    // ゲームを開始して経過した日にち
    this.days = 0;
    // 現在の所持資産
    this.assets = this.INITIAL_ASSETS;
  }

  // ユーザーの名前を表示する
  displayDefaultUserName() {
    document.querySelector("#user-name h1").textContent = this.name;
  }

  // ユーザーの年齢を表示する
  displayDefaultUserAge() {
    document.querySelector("#user-age h1").textContent = this.age + " yrs old";
  }

  // 1年が経過したら、ユーザーの年齢を更新する
  updateUserAge() {
    this.age++;
    document.querySelector("#user-age h1").textContent = this.age + " yrs old";
  }

  // ユーザーのゲーム開始からの経過日数を表示する
  displayDefaultDays() {
    document.querySelector("#user-elapsed-days h1").textContent =
      this.days + " days";
  }

  // ユーザーのゲーム開始からの経過日数を更新する
  updateDays() {
    document.querySelector("#user-elapsed-days h1").textContent =
      this.days + " days";
  }

  // ユーザーの所持金を表示する
  displayDefaultAssets() {
    document.querySelector("#user-assets h1").textContent = this.assets + " $";
  }

  // ユーザーのクリックによる所持金の増加分で更新する
  updateAssetsByBurgerClick() {
    document.querySelector("#user-assets h1").textContent =
      this.assets + this.MONEY_EVERY_CLICK + " $";
    this.assets += this.MONEY_EVERY_CLICK;
  }
  
  // ユーザーの所持金を更新する
  updateAssets() {
    document.querySelector("#user-assets h1").textContent = this.assets + " $";
  }

  // 取得可能な資産のリストを返す
  canGetAssetsList() {
    return Object.keys(this.assetsHeld);
  }

  // 新たに資産を保有する
  addAssetsHeld(assetName, purchaseCount) {
    if (this.assetsHeld.hasOwnProperty(assetName)) {
      this.assetsHeld[assetName] += purchaseCount;
    } else {
      this.assetsHeld[assetName] = purchaseCount;
    }
  }

  // 資産による1秒あたりの収入を計算する
  calculateAssetsRevenuePerSecond() {
    let totalRevenue = 0;
    // assetsHeldからkey(文字列として)とvalueを取り出す
    for (const [key, value] of Object.entries(this.assetsHeld)) {
      // keyが"ETF Stock"または"ETF Bonds"の場合、除外して計算する
      if (key == "ETF Stock" || key == "ETF Bonds") {
        continue;
      }

      totalRevenue += game.ITEMS[key].revenuePerSecond * value;
    }
    return totalRevenue;
  }

  calculateETFassetsRevenuePerSecond() {
    const ETF_STOCK_INTEREST_RATE = 0.001;
    const ETF_BONDS_INTEREST_RATE = 0.0007;

    let totalRevenue = 0;
    let totalETFStock = 0;
    let totalETFBonds = 0;

    if (this.assetsHeld.hasOwnProperty("ETF Stock")) {
      totalETFStock = game.ITEMS["ETF Stock"].price * this.assetsHeld["ETF Stock"];
    }
    if (this.assetsHeld.hasOwnProperty("ETF Bonds")) {
      totalETFBonds = game.ITEMS["ETF Bonds"].price * this.assetsHeld["ETF Bonds"];
    }
    totalRevenue = totalETFStock * ETF_STOCK_INTEREST_RATE + totalETFBonds * ETF_BONDS_INTEREST_RATE;

    return parseInt(totalRevenue);
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
  
  // priceを変更するメソッド
  setPrice(price) {
    this.price = price;
  }
}

class TimeManager {

  constructor(user, uiManager, items) {
    this.user = user;
    this.uiManager = uiManager;
    this.items = items;
  }

  // 1秒ごとに呼び出される関数
  startDayCycle() {
    setInterval(() => {
      this.user.assets += this.user.calculateAssetsRevenuePerSecond();
      console.log(this.user.calculateETFassetsRevenuePerSecond());
      this.user.assets += this.user.calculateETFassetsRevenuePerSecond();
      this.user.days++;
      // user.daysが365の倍数になったら、年齢を更新する
      if (this.user.days % 365 == 0) {
        this.user.updateUserAge();
      }
      this.user.updateAssets();
      this.user.updateDays();
      if (this.uiManager.currentView == "itemDetail") {
        return;
      } else {
        this.checkCanPurchaseItems();
      }
    }, 1500);
  }

  // itemsの中の各アイテムいづれかの価格を上回ったら、displayCanPurchaseItems()を呼び出す
  checkCanPurchaseItems() {
    const canPurchaseItems = Object.values(this.items).filter(
      (item) => item.price <= this.user.assets
    );
    if (canPurchaseItems.length > 0) {
      this.uiManager.displayCanPurchaseItems(this.items);
    }
  }
}


class GameManager {
  ITEMS = {};

  constructor(user) {
    this.user = user;
    this.initializeItems();
  }

  initializeItems() {
    this.ITEMS = {
      "Flip machine": new Item(
        "Flip machine",
        "ability",
        500,
        15000,
        2500,
        "image/FlipMachine.webp"
      ),
      "Lemonade Stand": new Item(
        "Lemonade Stand",
        "realEstate",
        1000,
        30000,
        30,
        "image/LemonadeStand.webp"
      ),
      "Ice Cream Truck": new Item(
        "Ice Cream Truck",
        "realEstate",
        500,
        100000,
        120,
        "image/iceCream.webp"
      ),
      "House": new Item(
        "House",
        "realEstate",
        100,
        20000000,
        32000,
        "image/house.webp"
      ),
      "TownHouse": new Item(
        "TownHouse",
        "realEstate",
        100,
        40000000,
        64000,
        "image/townHouse.webp"
      ),
      "Mansion": new Item(
        "Mansion",
        "realEstate",
        20,
        250000000,
        500000,
        "image/mansion.webp"
      ),
      "Industrial Space": new Item(
        "Industrial Space",
        "realEstate",
        10,
        1000000000,
        2200000,
        "image/industrialSpace.webp"
      ),
      "Hotel Skyscraper": new Item(
        "Hotel Skyscraper",
        "realEstate",
        5,
        10000000000,
        25000000,
        "image/hotelSkyScraper.webp"
      ),
      "Bullet-Speed Sky Railway": new Item(
        "Bullet-Speed Sky Railway",
        "realEstate",
        1,
        10000000000000,
        30000000,
        "image/BulletSpeedSkyRailway.webp"
      ),
      "ETF Stock": new Item(
        "ETF Stock",
        "stock",
        Infinity, 
        300000,
        "0.1% of total purchase price",
        "image/ETFStock.webp"
      ),
      "ETF Bonds": new Item(
        "ETF Bonds",
        "stock",
        Infinity, 
        300000,
        "0.07% of total purchase price",
        "image/ETFBonds.webp"
      ),
    };
  }

  startGame() {
    this.uiManager = new UIManager(this.user);
    this.uiManager.setBurgerClickEvent();
    this.uiManager.setButtonClickEvent();
    this.uiManager.displayDefaultBurgerCount();
    this.user.displayDefaultUserName();
    this.user.displayDefaultUserAge();
    this.user.displayDefaultDays();
    this.user.displayDefaultAssets();
    this.uiManager.displayCanPurchaseItems(this.ITEMS);
    this.timeManager = new TimeManager(this.user, this.uiManager, this.ITEMS);
    this.timeManager.startDayCycle();
  }
}

class SaveManager{

  constructor(user) {
    this.user = user;
  }

  getSaveData() {
    return {
      name: this.user.name,
      age: this.user.age,
      days: this.user.days,
      assets: this.user.assets,
      assetsHeld: this.user.assetsHeld
    };
  }

  saveData() {
    localStorage.setItem("saveData", JSON.stringify(this.getSaveData()));
  }
  
  loadSaveData() {
    const saveData = JSON.parse(localStorage.getItem("saveData"));
    this.user.name = saveData.name;
    this.user.age = saveData.age;
    this.user.days = saveData.days;
    this.user.assets = saveData.assets;
    this.user.assetsHeld = saveData.assetsHeld;
  }
}

const game = new GameManager(new User("Taro", 20));
game.startGame();
