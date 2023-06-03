package com.mygdx.tod.itemClasses;

public class Food {
    private int sellPrice, basePrice, currentStock, shopPrice;
    private final int maxStock = 40;
    private String name;

    public Food(int basePrice, String name) {
        this.name = name;
        this.basePrice = basePrice;
        currentStock = maxStock/2;
        sellPrice = basePrice;
        shopPrice = basePrice/2;
    }

    public String getName() {
        return name;
    }

    public void setPrice(int newPrice) {
        sellPrice = newPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int calculateCustomers() {
        if (currentStock > 0) {
            if (sellPrice >= basePrice) {
                int customers = (int) (Math.log(sellPrice + 1) * 10);
                return Math.max(customers, 0);
            } else {
                int customers = (int) (Math.log(basePrice + 1) * 10);
                return Math.min(customers, 40);

            }
        }
        return 0;
    }
    

    public int  getStock(){
        return currentStock;
    }

    public void addStock(int change){
        currentStock = currentStock +  change;
        if (currentStock > maxStock) {
            currentStock = maxStock;
        }
    }
    public void removeStock(int change){
        currentStock =- change;
        if (currentStock < 0) {
            currentStock = 0;
        }
    }

    public int getShopPrice(){
        return shopPrice;
    }
}
