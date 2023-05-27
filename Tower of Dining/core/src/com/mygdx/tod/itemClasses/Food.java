package com.mygdx.tod.itemClasses;

public class Food {
    private int sellPrice, basePrice, currentStock;
    private final int maxStock = 40;


    public Food(int basePrice){
        this.basePrice = basePrice;
        currentStock = maxStock;
        sellPrice = basePrice;
    }
    public void setPrice(int newPrice){
        sellPrice = newPrice;
    }
    public int getSellPrice() {
        return sellPrice;
    }
    public int getBasePrice(){
        return basePrice;
    }
    
}
