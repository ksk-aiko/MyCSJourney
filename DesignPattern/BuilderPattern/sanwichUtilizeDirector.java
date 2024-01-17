// 汎用サンドイッチクラス
class Sandwich {
    private String doughType;
    private String topping1;
    private String topping2;
    private String topping3;
    private String vegetable1;
    private String vegetable2;
    private String vegetable3;
    private String vegetable4;
    private String vegetable5;
    // サイズを追加
    private double sizeCm;
    private boolean cheese;
    private boolean ketchup;
    private boolean mustard;
    private boolean mayonnaise;
    private String sauce;

    public Sandwich(String doughType, double sizeCm, String topping1, String topping2, String topping3, String vegetable1, String vegetable2, String vegetable3, String vegetable4, String vegetable5, boolean cheese, boolean ketchup, boolean mustard, boolean mayonnaise, String sauce){
        this.doughType = doughType;
        this.sizeCm = sizeCm;
        this.topping1 = topping1;
        this.topping2 = topping2;
        this.topping3 = topping3;
        this.vegetable1 = vegetable1;
        this.vegetable2 = vegetable2;
        this.vegetable3 = vegetable3;
        this.vegetable4 = vegetable4;
        this.vegetable5 = vegetable5;
        this.cheese = cheese;
        this.ketchup = ketchup;
        this.mustard = mustard;
        this.mayonnaise = mayonnaise;
        this.sauce = sauce;
    }

    public String toString(){
        String sandwich = this.doughType + " bread, size " + this.sizeCm + "cm with " + this.topping1 + ", ";
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

// ビルダークラスの作成
class SandwichBuilder {
    private final static String DEFAULT_DOUGH_TYPE = "wheat";
    private final static String DEFAULT_TOPPING1 = "Ham";
    private final static double DEFAULT_SIZE_CM = 15.24;

    private String doughType;
    private double sizeCm;
    private String topping1;
    private String topping2;
    private String topping3;
    private String vegetable1;
    private String vegetable2;
    private String vegetable3;
    private String vegetable4;
    private String vegetable5;

    private boolean cheese;
    private boolean ketchup;
    private boolean mustard;
    private boolean mayonnaise;
    private String sauce;

    public SandwichBuilder(){
        this.reset();
    }

    public SandwichBuilder addDoughType(String doughType){
        this.doughType = doughType;
        return this;
    }

    public SandwichBuilder addTopping1(String topping) {
        this.topping1 = topping;
        return this;
    }

    public SandwichBuilder addTopping2(String topping) {
        this.topping2 = topping;
        return this;
    }

    public SandwichBuilder addTopping3(String topping) {
        this.topping3 = topping;
        return this;
    }

    public SandwichBuilder addVegetable1(String vegetable) {
        this.vegetable1 = vegetable;
        return this;
    }

    public SandwichBuilder addVegetable2(String vegetable) {
        this.vegetable2 = vegetable;
        return this;
    }

    public SandwichBuilder addVegetable3(String vegetable) {
        this.vegetable3 = vegetable;
        return this;
    }

    public SandwichBuilder addVegetable4(String vegetable) {
        this.vegetable4 = vegetable;
        return this;
    }

    public SandwichBuilder addVegetable5(String vegetable) {
        this.vegetable5 = vegetable;
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

    public SandwichBuilder setSize(double size) {
        this.sizeCm = size;
        return this;
    }

    public Sandwich build() {
        Sandwich s = new Sandwich(this.doughType, this.sizeCm, this.topping1, this.topping2, this.topping3, this.vegetable1, this.vegetable2, this.vegetable3, this.vegetable4, this.vegetable5, this.cheese, this.ketchup, this.mustard, this.mayonnaise, this.sauce);
        this.reset();
        return s;
    }

    public SandwichBuilder reset() {
        this.doughType = DEFAULT_DOUGH_TYPE;
        this.sizeCm = DEFAULT_SIZE_CM;
        this.topping1 = DEFAULT_TOPPING1;
        this.topping2 = null;
        this.topping3 = null;
        this.vegetable1 = null;
        this.vegetable2 = null;
        this.vegetable3 = null;
        this.vegetable4 = null;
        this.vegetable5 = null;
        this.cheese = false;
        this.ketchup = false;
        this.mustard = false;
        this.mayonnaise = false;
        this.sauce = null;
        return this;
    }
}

// ビルダーのためのメニューを作成
enum SandwichMenu {
    CHIKEN_AND_BACON,
    STEAK_AND_CHEESE,
    SPIICY_ITALIAN,
    TUNA_AND_EGG,
}

enum SandwichSize {
    FOOTLONG,
    HALF_FOOTLONG,
}

// ディレクタークラスを作成
class FairlyWorldSandwichDirector {
    public static SandwichBuilder chickenAndBacon(SandwichBuilder builder) {
        builder
        .addDoughType("Italian")
        .addTopping1("Chicken")
        .addTopping2("Bacon")
        .addTopping3("steak")
        .addSauce("Ranch")
        .addVegetable1("Lettuce")
        .addVegetable2("Red Onion")
        .addVegetable3("Tomato")
        .addCheese();

        return builder;
    }

    public static SandwichBuilder steakAndCheese(SandwichBuilder builder) {
        builder
        .addDoughType("Italian")
        .addTopping1("Steak")
        .addTopping2("Cheese")
        .addSauce("BBQ")
        .addVegetable1("Lettuce")
        .addVegetable2("Red Onion")
        .addVegetable3("Tomato")
        .addVegetable4("Green Pepper")
        .addVegetable5("Jalapeno")
        .addCheese();

        return builder;
    }

    public static SandwichBuilder spicyItalian(SandwichBuilder builder) {
        builder
        .addDoughType("Wheat")
        .addTopping2("Salami")
        .addVegetable1("Lettuce")
        .addVegetable2("Red Onion")
        .addVegetable3("Tomato")
        .addVegetable4("Green Pepper")
        .addVegetable5("Jalapeno");

        return builder;
    }

    public static SandwichBuilder tunaAndEgg(SandwichBuilder builder) {
        builder
        .addDoughType("Wheat")
        .addTopping1("Tuna")
        .addTopping2("Egg")
        .addVegetable1("Lettuce")
        .addVegetable2("Red Onion")
        .addVegetable3("Tomato")
        .addVegetable4("Green Pepper")
        .addVegetable5("Jalapeno");

        return builder;
    }
}

class FairyWorld {
    public Sandwich orderSandwich(SandwichMenu item, SandwichSize itemSize) {
        SandwichBuilder builder = new SandwichBuilder();
        switch(item) {
            case CHIKEN_AND_BACON:
                FairlyWorldSandwichDirector.chickenAndBacon(builder);
                break;
            case STEAK_AND_CHEESE:
                FairlyWorldSandwichDirector.steakAndCheese(builder);
                break;
            case SPIICY_ITALIAN:
                FairlyWorldSandwichDirector.spicyItalian(builder);
                break;
            case TUNA_AND_EGG:
                FairlyWorldSandwichDirector.tunaAndEgg(builder);
                break;
        }

        switch(itemSize) {
            case FOOTLONG:
                builder.setSize(30.48);
                break;
            case HALF_FOOTLONG:
                builder.setSize(15.24);
                break;
        }

        return builder.build();
    }
}