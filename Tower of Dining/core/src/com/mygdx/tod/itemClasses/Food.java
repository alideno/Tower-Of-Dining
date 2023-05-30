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
        if (sellPrice >= basePrice) {
            if ((int) ((int) (-1/20)* Math.pow(sellPrice-basePrice, 2)+20) < 0) {
               return 0; 
            }else {
                return (int) ((int) (-1/20)* Math.pow(sellPrice-basePrice, 2)+20);
            }
        }else{
            if ((int) ((int) (1/20)* Math.pow(sellPrice-basePrice, 2)+20) > 40) {
                return 40;
            }else{
                return (int) ((int) (1/20)* Math.pow(sellPrice-basePrice, 2)+20);
            }

        }
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
