class VacationInvoice {
    private String startDate;
    private String endDate;
    private int totalPeople;
    private int hotelDays;
    private String hotelType;
    private int fairyWorldDays;
    private boolean adultFastPass;
    private boolean kidsFastPass;
    private boolean arcadePass;
    private boolean[] vipTours; // VIPツアー1, 2, 3
    private boolean[] mealsRegular; // 通常の食事: 朝食, 昼食, 夕食
    private boolean[] mealsFineDining; // 高級レストラン: 朝食, 昼食, 夕食
    private boolean photoshoot;
    private double totalAmount; // 合計金額

    public VacationInvoice() {
        vipTours = new boolean[3];
        mealsRegular = new boolean[3];
        mealsFineDining = new boolean[3];
    }

    // ゲッターとセッター
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(int totalPeople) {
        this.totalPeople = totalPeople;
    }

    public int getHotelDays() {
        return hotelDays;
    }

    public void setHotelDays(int hotelDays) {
        this.hotelDays = hotelDays;
    }

    // その他のゲッターとセッター...
    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public int getFairyWorldDays() {
        return fairyWorldDays;
    }

    public void setFairyWorldDays(int fairyWorldDays) {
        this.fairyWorldDays = fairyWorldDays;
    }


    public boolean isAdultFastPass() {
        return adultFastPass;
    }

    public void setAdultFastPass(boolean adultFastPass) {
        this.adultFastPass = adultFastPass;
    }

    public boolean isKidsFastPass() {
        return kidsFastPass;
    }

    public void setKidsFastPass(boolean kidsFastPass) {
        this.kidsFastPass = kidsFastPass;
    }

    public boolean isArcadePass() {
        return arcadePass;
    }

    public void setArcadePass(boolean arcadePass) {
        this.arcadePass = arcadePass;
    }

    public boolean[] getVipTours() {
        return vipTours;
    }

    public void setVipTour(int index, boolean value) {
        if (index >= 0 && index < vipTours.length) {
            vipTours[index] = value;
        }
    }

    public boolean[] getMealsRegular() {
        return mealsRegular;
    }

    public void setMealRegular(int index, boolean value) {
        if (index >= 0 && index < mealsRegular.length) {
            mealsRegular[index] = value;
        }
    }

    public boolean[] getMealsFineDining() {
        return mealsFineDining;
    }

    public void setMealFineDining(int index, boolean value) {
        if (index >= 0 && index < mealsFineDining.length) {
            mealsFineDining[index] = value;
        }
    }

    public boolean isPhotoshoot() {
        return photoshoot;
    }

    public void setPhotoshoot(boolean photoshoot) {
        this.photoshoot = photoshoot;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getStayCost() {
        double hotelRate = hotelType.equals("Deluxe") ? 200 : 100; // ホテルタイプによる料金
        return hotelDays * hotelRate * totalPeople;
    }
    
    public double getPassCost() {
        double cost = 0;
        if (adultFastPass) {
            cost += 50 * fairyWorldDays;
        }
        if (kidsFastPass) {
            cost += 30 * fairyWorldDays;
        }
        if (arcadePass) {
            cost += 20;
        }
        return cost;
    }
    
    public double getTourCost() {
        double cost = 0;
        for (int i = 0; i < vipTours.length; i++) {
            if (vipTours[i]) {
                cost += 100; // VIPツアー1回あたりの料金
            }
        }
        for (int i = 0; i < mealsRegular.length; i++) {
            if (mealsRegular[i]) {
                cost += 20; // 通常食事1回あたりの料金
            }
        }
        for (int i = 0; i < mealsFineDining.length; i++) {
            if (mealsFineDining[i]) {
                cost += 50; // 高級レストラン1回あたりの料金
            }
        }
        if (photoshoot) {
            cost += 150; // 写真撮影の料金
        }
        return cost;
    }
    
    public double calculateTotalAmount() {
        // 合計金額の計算
        return this.totalAmount = this.getStayCost() + this.getPassCost() + this.getTourCost();
    }
     

    // toStringメソッドで請求書の詳細を文字列で出力する
    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Vacation Invoice: \nStart Date: ").append(startDate)
      .append("\nEnd Date: ").append(endDate)
      .append("\nTotal People: ").append(totalPeople)
      .append("\nHotel Days: ").append(hotelDays)
      .append("\nHotel Type: ").append(hotelType)
      .append("\nFairy World Days: ").append(fairyWorldDays);

    if (adultFastPass) {
        sb.append("\nAdult Fast Pass: Yes");
    }
    if (kidsFastPass) {
        sb.append("\nKids Fast Pass: Yes");
    }
    if (arcadePass) {
        sb.append("\nArcade Pass: Yes");
    }
    for (int i = 0; i < vipTours.length; i++) {
        if (vipTours[i]) {
            sb.append("\nVIP Tour ").append(i + 1).append(": Yes");
        }
    }
    for (int i = 0; i < mealsRegular.length; i++) {
        if (mealsRegular[i]) {
            sb.append("\nRegular Meal ").append(i + 1).append(": Yes");
        }
    }
    for (int i = 0; i < mealsFineDining.length; i++) {
        if (mealsFineDining[i]) {
            sb.append("\nFine Dining ").append(i + 1).append(": Yes");
        }
    }
    if (photoshoot) {
        sb.append("\nPhotoshoot: Yes");
    }

    sb.append("\nTotal Amount: ").append(String.format("%.2f", totalAmount));
    return sb.toString();
}

}

class VacationInvoiceBuilder {
    private VacationInvoice invoice;

    public VacationInvoiceBuilder() {
        invoice = new VacationInvoice();
    }

    public VacationInvoiceBuilder setStartDate(String startDate) {
        invoice.setStartDate(startDate);
        return this;
    }

    public VacationInvoiceBuilder setEndDate(String endDate) {
        invoice.setEndDate(endDate);
        return this;
    }

    public VacationInvoiceBuilder setTotalPeople(int totalPeople) {
        invoice.setTotalPeople(totalPeople);
        return this;
    }

    public VacationInvoiceBuilder setHotelDays(int hotelDays) {
        invoice.setHotelDays(hotelDays);
        return this;
    }

    public VacationInvoiceBuilder setHotelType(String hotelType) {
        invoice.setHotelType(hotelType);
        return this;
    }

    public VacationInvoiceBuilder setFairyWorldDays(int fairyWorldDays) {
        invoice.setFairyWorldDays(fairyWorldDays);
        return this;
    }

    public VacationInvoiceBuilder setAdultFastPass(boolean adultFastPass) {
        invoice.setAdultFastPass(adultFastPass);
        return this;
    }

    public VacationInvoiceBuilder setKidsFastPass(boolean kidsFastPass) {
        invoice.setKidsFastPass(kidsFastPass);
        return this;
    }

    public VacationInvoiceBuilder setArcadePass(boolean arcadePass) {
        invoice.setArcadePass(arcadePass);
        return this;
    }

    public VacationInvoiceBuilder setVipTour(int index, boolean value) {
        invoice.setVipTour(index, value);
        return this;
    }

    public VacationInvoiceBuilder setMealRegular(int index, boolean value) {
        invoice.setMealRegular(index, value);
        return this;
    }

    public VacationInvoiceBuilder setMealFineDining(int index, boolean value) {
        invoice.setMealFineDining(index, value);
        return this;
    }

    public VacationInvoiceBuilder setPhotoshoot(boolean photoshoot) {
        invoice.setPhotoshoot(photoshoot);
        return this;
    }

    public VacationInvoice build() {
        invoice.calculateTotalAmount();
        return invoice;
    }
}

class VacationDirector {
    public static VacationInvoiceBuilder coupleWeekendRetreat() {
        VacationInvoiceBuilder builder = new VacationInvoiceBuilder();
        builder.setStartDate("2019/12/20")
               .setEndDate("2019/12/22")
               .setTotalPeople(2)
               .setHotelDays(2)
               .setHotelType("Deluxe")
               .setFairyWorldDays(0)
               .setAdultFastPass(true)
               .setKidsFastPass(false)
               .setArcadePass(false)
               .setVipTour(0, false)
               .setVipTour(1, false)
               .setVipTour(2, false)
               .setMealRegular(0, true)
               .setMealRegular(1, true)
               .setMealRegular(2, true)
               .setMealFineDining(0, false)
               .setMealFineDining(1, false)
               .setMealFineDining(2, false)
               .setPhotoshoot(false);
        return builder;
    }

    public static VacationInvoiceBuilder familyWeekendRetreat() {
        VacationInvoiceBuilder builder = new VacationInvoiceBuilder();
        builder.setStartDate("2019/12/20")
               .setEndDate("2019/12/22")
               .setTotalPeople(4)
               .setHotelDays(2)
               .setHotelType("Deluxe")
               .setFairyWorldDays(0)
               .setAdultFastPass(true)
               .setKidsFastPass(true)
               .setArcadePass(false)
               .setVipTour(0, false)
               .setVipTour(1, false)
               .setVipTour(2, false)
               .setMealRegular(0, true)
               .setMealRegular(1, true)
               .setMealRegular(2, true)
               .setMealFineDining(0, false)
               .setMealFineDining(1, false)
               .setMealFineDining(2, false)
               .setPhotoshoot(false);
        return builder;
    }

    public static VacationInvoiceBuilder familyWeekDeluxe() {
        VacationInvoiceBuilder builder = new VacationInvoiceBuilder();
        builder.setStartDate("2019/12/20")
               .setEndDate("2019/12/27")
               .setTotalPeople(4)
               .setHotelDays(7)
               .setHotelType("Deluxe")
               .setFairyWorldDays(5)
               .setAdultFastPass(true)
               .setKidsFastPass(true)
               .setArcadePass(true)
               .setVipTour(0, true)
               .setVipTour(1, true)
               .setVipTour(2, true)
               .setMealRegular(0, true)
               .setMealRegular(1, true)
               .setMealRegular(2, true)
               .setMealFineDining(0, true)
               .setMealFineDining(1, true)
               .setMealFineDining(2, true)
               .setPhotoshoot(true);
        return builder;
    }
}

class FairyWorld {
    // 請求書を要求するメソッド
    public static void requestInvoice(VacationInvoiceBuilder builder) {
        VacationInvoice invoice = builder.build();
        System.out.println(invoice);
    }
}

class Main {
    public static void main(String[] args) {
        FairyWorld.requestInvoice(VacationDirector.coupleWeekendRetreat());
        System.out.println();
        FairyWorld.requestInvoice(VacationDirector.familyWeekendRetreat());
        System.out.println();
        FairyWorld.requestInvoice(VacationDirector.familyWeekDeluxe());
    }
}

