// メソッドチェーンを使ってビルダーパターンを活用する。より流暢なインターフェースを提供する。

// 汎用サンドイッチ
class Sandwich {
    private String doughType;
    private String topping1;
    private String topping2;
    private String topping3;
    private String vegetable1;
    private String vegetable2;
    private String vegetable3;
    private String vegetable4;
    private boolean cheese;
    

    private boolean ketchup;
    private boolean mustard;
    private boolean mayonnaise;
    private String sauce;

    public Sandwich(String doughType, String topping1, String topping2, String topping3, String vegetable1,
            String vegetable2, String vegetable3, String vegetable4, boolean cheese, boolean ketchup, boolean mustard,
            boolean mayonnaise, String sauce) {
        this.doughType = doughType;
        this.topping1 = topping1;
        this.topping2 = topping2;
        this.topping3 = topping3;
        this.vegetable1 = vegetable1;
        this.vegetable2 = vegetable2;
        this.vegetable3 = vegetable3;
        this.vegetable4 = vegetable4;
        this.cheese = cheese;
        this.ketchup = ketchup;
        this.mustard = mustard;
        this.mayonnaise = mayonnaise;
        this.sauce = sauce;
    }

    public String toString() {
        String sandwich = this.doughType + " with " + this.topping1 + " ";
        if (this.topping2 != null)
            sandwich += this.topping2 + ", ";
        if (this.topping3 != null)
            sandwich += this.topping3 + ", ";
        if (this.vegetable1 != null)
            sandwich += this.vegetable1 + ", ";
        if (this.vegetable2 != null)
            sandwich += this.vegetable2 + ", ";
        if (this.vegetable3 != null)
            sandwich += this.vegetable3 + ", ";
        if (this.vegetable4 != null)
            sandwich += this.vegetable4 + ", ";
        if (this.cheese)
            sandwich += "with cheese, ";
        if (this.ketchup)
            sandwich += "with ketchup, ";
        if (this.mustard)
            sandwich += "with mustard, ";
        if (this.mayonnaise)
            sandwich += "with mayonnaise, ";
        if (this.sauce != null)
            sandwich += "with " + this.sauce;

        return sandwich;
    }
}

// ビルダーパターンを活用する
class SandwichBuilder {
    // デフォルトの材料を設定する
    private final static String DEFAULT_DOUGH = "wheat";
    private final static String DEFAULT_TOPPING1 = "Ham";

    private String doughType;
    private String topping1;
    private String topping2;
    private String topping3;
    private String vegetable1;
    private String vegetable2;
    private String vegetable3;
    private String vegetable4;
    private boolean cheese;
    private boolean ketchup;
    private boolean mustard;
    private boolean mayonnaise;
    private String sauce;

    // コンストラクタでビルダーの初期化を行う
    public SandwichBuilder() {
        this.reset();
    }

    // メソッドチェーンを使ってビルダーを構築する
    public SandwichBuilder addDoughType(String doughType) {
        this.doughType = doughType;
        return this;
    }

    public SandwichBuilder addTopping1(String topping1) {
        this.topping1 = topping1;
        return this;
    }

    public SandwichBuilder addTopping2(String topping2) {
        this.topping2 = topping2;
        return this;
    }

    public SandwichBuilder addTopping3(String topping3) {
        this.topping3 = topping3;
        return this;
    }

    public SandwichBuilder addVegetable1(String vegetable1) {
        this.vegetable1 = vegetable1;
        return this;
    }

    public SandwichBuilder addVegetable2(String vegetable2) {
        this.vegetable2 = vegetable2;
        return this;
    }

    public SandwichBuilder addVegetable3(String vegetable3) {
        this.vegetable3 = vegetable3;
        return this;
    }

    public SandwichBuilder addVegetable4(String vegetable4) {
        this.vegetable4 = vegetable4;
        return this;
    }

    public SandwichBuilder addCheese() {
        this.cheese = true;
        return this;
    }

    public SandwichBuilder noCheese() {
        this.cheese = false;
        return this;
    }

    public SandwichBuilder addKetchup() {
        this.ketchup = true;
        return this;
    }

    public SandwichBuilder noKetchup() {
        this.ketchup = false;
        return this;
    }

    public SandwichBuilder addMustard() {
        this.mustard = true;
        return this;
    }

    public SandwichBuilder noMustard() {
        this.mustard = false;
        return this;
    }

    public SandwichBuilder addMayo() {
        this.mayonnaise = true;
        return this;
    }

    public SandwichBuilder noMayo() {
        this.mayonnaise = false;
        return this;
    }

    public SandwichBuilder addSauce(String sauce) {
        this.sauce = sauce;
        return this;
    }

    // build関数を定義する
    public Sandwich build() {
        Sandwich s = new Sandwich(this.doughType, this.topping1, this.topping2, this.topping3, this.vegetable1,
                this.vegetable2, this.vegetable3, this.vegetable4, this.cheese, this.ketchup, this.mustard,
                this.mayonnaise, this.sauce);

        // ビルダーのリセットを行う
        this.reset();

        return s;
    }

    // builderのリセットを行う
    public SandwichBuilder reset() {
        this.doughType = DEFAULT_DOUGH;
        this.topping1 = DEFAULT_TOPPING1;
        this.topping2 = null;
        this.topping3 = null;
        this.vegetable1 = null;
        this.vegetable2 = null;
        this.vegetable3 = null;
        this.vegetable4 = null;
        this.cheese = false;
        this.ketchup = false;
        this.mustard = false;
        this.mayonnaise = false;
        this.sauce = null;

        return this;
    }
}

class Main {
    public static void main(String[] args) {
        SandwichBuilder sandwichBuilder = new SandwichBuilder();

        // メソッドチェーンを使ってサンドイッチを作る
        sandwichBuilder.addDoughType("Italian")
                       .addTopping1("Chicken")
                       .addTopping2("Bacon")
                       .addVegetable1("Lettuce")
                       .addVegetable2("Tomato")
                       .addVegetable3("Onion")
                       .addCheese()
                       .addKetchup()
                       .addMustard()
                       .addMayo()
                       .addSauce("BBQ");

        Sandwich sandwich = sandwichBuilder.build();

        System.out.println(sandwich);
}
}
