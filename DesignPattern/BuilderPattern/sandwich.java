package DesignPattern.BuilderPattern;
// 汎用サンドイッチ
class Sandwich{
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

    public Sandwich(String doughType, String topping1, String topping2, String topping3, String vegetable1, String vegetable2, String vegetable3, String vegetable4, boolean cheese, boolean ketchup, boolean mustard, boolean mayonnaise, String sauce){
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

    public String toString(){
        String sandwich = this.doughType + " with " + this.topping1 + " ";
        if(this.topping2 != null) sandwich+=this.topping2 + ", ";
        if(this.topping3 != null) sandwich+=this.topping3 + ", ";
        if(this.vegetable1 != null) sandwich+=this.vegetable1 + ", ";
        if(this.vegetable2 != null) sandwich+=this.vegetable2 + ", ";
        if(this.vegetable3 != null) sandwich+=this.vegetable3 + ", ";
        if(this.vegetable4 != null) sandwich+=this.vegetable4 + ", ";
        if(this.cheese) sandwich+="with cheese, ";
        if(this.ketchup) sandwich+="with ketchup, ";
        if(this.mustard) sandwich+="with mustard, ";
        if(this.mayonnaise) sandwich+="with mayonnaise, ";
        if(this.sauce != null) sandwich+="with " + this.sauce;

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
    public  SandwichBuilder() {
        this.reset();
    }

    // 各要素を個別に追加するメソッドを定義する
    public void addDoughType(String doughType) { this.doughType = doughType;}
    public void addTopping1(String topping) { this.topping1 = topping;}
    public void addTopping2(String topping) { this.topping2 = topping;}
    public void addTopping3(String topping) { this.topping3 = topping;}
    public void addVegetable1(String vegetable) { this.vegetable1 = vegetable;}
    public void addVegetable2(String vegetable) { this.vegetable2 = vegetable;}
    public void addVegetable3(String vegetable) { this.vegetable3 = vegetable;}
    public void addVegetable4(String vegetable) { this.vegetable4 = vegetable;}
    public void addCheese() { this.cheese = true;}
    public void noCheese() { this.cheese = false;}
    public void addKetchup() { this.ketchup = true;}
    public void noKetchup() { this.ketchup = false;}
    public void addMustard() { this.mustard = true;}
    public void noMustard() { this.mustard = false;}
    public void addMayo() { this.mayonnaise = true;}
    public void noMayo() { this.mayonnaise = false;}
    public void addSauce(String sauce) { this.sauce = sauce;}

    // build関数を定義する
    public Sandwich build() {
        return new Sandwich(this.doughType, this.topping1, this.topping2, this.topping3, this.vegetable1, this.vegetable2, this.vegetable3, this.vegetable4, this.cheese, this.ketchup, this.mustard, this.mayonnaise, this.sauce);
    }

    //builderのリセットを行う
    public void reset() {
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
    }
}

class Main {
    public static void main(String[] args) {
        SandwichBuilder sandwichBuilder = new SandwichBuilder();

        // ビルダーを使ってサンドイッチを段階的に作る
        sandwichBuilder.addDoughType("Italian");
        sandwichBuilder.addTopping1("Salami");
        sandwichBuilder.addTopping2("Pepperoni");
        sandwichBuilder.addVegetable1("Lettuce");
        sandwichBuilder.addVegetable2("Tomato");
        sandwichBuilder.addVegetable3("Onion");
        sandwichBuilder.addCheese();

        // サンドウィッチオブジェクトをビルドする
        Sandwich italianSandwich = sandwichBuilder.build();

        System.out.println(italianSandwich);
    }
}
