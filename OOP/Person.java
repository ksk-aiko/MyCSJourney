
class Wallet{
    public int bill1;
    public int bill5;
    public int bill10;
    public int bill20;
    public int bill50;
    public int bill100;

    public Wallet(){}

    public Wallet(int bill1, int bill5, int bill10, int bill20, int bill50, int bill100){
        this.bill1 = bill1;
        this.bill5 = bill5;
        this.bill10 = bill10;
        this.bill20 = bill20;
        this.bill50 = bill50;
        this.bill100 = bill100;
    }

    public double getTotalMoney(){
        return (1*bill1) + (5*bill5) + (10*bill10) + (20*bill20) + (50*bill50) + (100*bill100);
    }

    public int insertBill(int bill, int amount){
        switch(bill){
            case(1):
                bill1 += amount;
                break;
            case(5):
                bill5 += amount;
                break;
            case(10):
                bill10 += amount;
                break;
            case(20):
                bill20 += amount;
                break;
            case(50):
                bill50 += amount;
                break;
            case(100):
                bill100 += amount;
                break;
            default:
                return 0;
        }

        return bill*amount;
    }

    public int removeBill(int bill, int amount) {
        switch(bill){
            case(1):
                if(bill1 < amount) return 0;
                bill1 -= amount;
                break;
            case(5):
                if(bill5 < amount) return 0;
                bill5 -= amount;
                break;
            case(10):
                if(bill10 < amount) return 0;
                bill10 -= amount;
                break;
            case(20):
                if(bill20 < amount) return 0;
                bill20 -= amount;
                break;
            case(50):
                if(bill50 < amount) return 0;
                bill50 -= amount;
                break;
            case(100):
                if(bill100 < amount) return 0;
                bill100 -= amount;
                break;
            default:
                return 0;
        }

        return bill*amount;
    }
}

class Person{
    private String firstName;
    private String lastName;
    private int age;
    private double heightM;
    private double weightKg;
    private int initialMoney;
    private String denomination = "highestFirst";
    private Wallet wallet;

    public Person(String firstName, String lastName, int age, double heightM, double weightKg){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.heightM = heightM;
        this.weightKg = weightKg;
        this.wallet = new Wallet();
    }
    
    public Person(String firstName, String lastName, int age, double heightM, double weightKg, int initialMoney){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.heightM = heightM;
        this.weightKg = weightKg;
        this.initialMoney = initialMoney;
        this.wallet = new Wallet(this.setNumberOfBills(initialMoney)[0], this.setNumberOfBills(initialMoney)[1], this.setNumberOfBills(initialMoney)[2], this.setNumberOfBills(initialMoney)[3], this.setNumberOfBills(initialMoney)[4], this.setNumberOfBills(initialMoney)[5]);
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    // 整数値を受け取り、denominationに応じた紙幣の枚数を設定し、walletに追加する
    public int[] getPayed(int money) {
        int[] result = setNumberOfBills(money);
        this.wallet.insertBill(1, result[0]);
        this.wallet.insertBill(5, result[1]);
        this.wallet.insertBill(10, result[2]);
        this.wallet.insertBill(20, result[3]);
        this.wallet.insertBill(50, result[4]);
        this.wallet.insertBill(100, result[5]);
        return result;
    }

    // 整数値を受け取り、denominationに応じた紙幣の枚数の配列を返す。それらをwalletから削除する
    public int[] spendMoney(int money) {
        int[] result = setNumberOfBills(money);
        this.wallet.removeBill(1, result[0]);
        this.wallet.removeBill(5, result[1]);
        this.wallet.removeBill(10, result[2]);
        this.wallet.removeBill(20, result[3]);
        this.wallet.removeBill(50, result[4]);
        this.wallet.removeBill(100, result[5]);
        return result;
    }
    
    public double getCash(){
        if(this.wallet == null) return 0;
        return this.wallet.getTotalMoney();
    }

    public void addWallet(Wallet wallet){
        this.wallet = wallet;
    }

    public Wallet dropWallet(){
        Wallet wallet = this.wallet;
        this.wallet = null;
        return wallet;
    }

    // 整数値を受け取り、denominationに応じた紙幣の枚数の配列を返す関数
    public int[] setNumberOfBills(int money) {
        int[] result = new int[6];
        // インデックスが小さい方から1ドル、5ドル、10ドル、20ドル、50ドル、100ドルの紙幣の枚数を表す
        // denominationが"highestFirst"の場合、額面が大きい紙幣を優先的に使って配列を作成する
        // 例：money = 389 の場合、{4, 1, 1, 1, 1, 3} を返す
        if (this.denomination == "highestFirst") {
            int[] bills = new int[]{100, 50, 20, 10, 5, 1};
            for (int i = bills.length - 1; i >= 0; i--) {
                result[i] = money / bills[bills.length - 1 - i];
                money %= bills[bills.length - 1 - i];
            }
        } else if (this.denomination == "dollars") {
            // 全て1ドル紙幣を使って配列を作成する
            // 例：money = 389 の場合、{389, 0, 0, 0, 0, 0} を返す
            return new int[]{money, 0, 0, 0, 0, 0};
        } else if (this.denomination == "twenties") {
            // まず20ドル紙幣を優先的に使い、その後はhighestFirstと同じようにする
            // 例：money = 389 の場合、{4, 1, 0, 19, 0, 0} を返す
            result[3] = money / 20;
            money %= 20;
            int[] bills = new int[]{100, 50, 10, 5, 1};
            for (int i = bills.length - 1; i >= 0; i--) {
                if (i == 3) continue;
                result[i] = money / bills[bills.length - 1 - i];
                money %= bills[bills.length - 1 - i];
            }
        }

        return result;

    }

    public void printState(){
        System.out.println("firstname - " + this.firstName);
        System.out.println("lastname - " + this.lastName);
        System.out.println("age - " + this.age);
        System.out.println("height - " + this.heightM);
        System.out.println("weight - " + this.weightKg);
        System.out.println("Current Money - " + this.getCash());
        System.out.println();
    }

    public void setDenomination(String denomination){
        this.denomination = denomination;
    }

    private void testGetPayed() {
        // getPayed関数のテスト
        System.out.println("-----getPayed関数のテスト-----");
        System.out.println("-----money = 389 の場合-----");
        int[] result = this.getPayed(389);
        System.out.println("1ドル紙幣の枚数: " + result[0]);
        System.out.println("5ドル紙幣の枚数: " + result[1]);
        System.out.println("10ドル紙幣の枚数: " + result[2]);
        System.out.println("20ドル紙幣の枚数: " + result[3]);
        System.out.println("50ドル紙幣の枚数: " + result[4]);
        System.out.println("100ドル紙幣の枚数: " + result[5]);
        System.out.println();

    }
}

class Main{
    public static void main(String[] args){
        Person p = new Person("Ryu","Poolhopper", 40, 1.8, 140);
        p.printState();
    }
}