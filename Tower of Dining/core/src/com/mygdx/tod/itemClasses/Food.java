package com.mygdx.tod.itemClasses;

public class Food {
    private int sellPrice, basePrice, currentStock;
    private final int maxStock = 40;
    private String name;

    public Food(int basePrice, String name) {
        this.name = name;
        this.basePrice = basePrice;
        currentStock = maxStock;
        sellPrice = basePrice;
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
        return 0;
    }

}
