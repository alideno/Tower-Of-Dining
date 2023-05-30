package com.mygdx.tod.itemClasses;
/**
 * This class represents a restaurant and manages the functionality of a restaurant.
 * 
 * @author Deniz Şahin
 */
public class Restaurant {
    
    //defining the food related variables
    private Food[] foods; //the foods this restaurant sells
    private int addedFoods; //the number of foods added
    
    //defining the restaurant related variables
    private boolean isOpen; //is restaurant open
    private int purchasePrice; //the purchase price of the restaurant

    //constructor
    public Restaurant(int purchasePrice)
    {
        foods = new Food[3];
        addedFoods = 0;
        isOpen = false;
        this.purchasePrice = purchasePrice;
    }

    /**
     * This method adds the specified food to the foods array of this restaurant
     * @return the number of foods added, -1 if all food slots are full
     */
    public int addFoodItem(Food food)
    {
        if(addedFoods < 3)
        {
            this.foods[addedFoods] = food;
            this.addedFoods++;
            return addedFoods;
        }
        return -1;
    }

    /**
     * This method turns the restaurant status to open
     * Invoke when restaurant is bought
     */
    public void openRestaurant()
    {
        this.isOpen = true;
    }

    /**
     * This method calculates the end of day income and reduces the food used up
     * @return income this restaurant provided on this day
     */
    public int endDay()
    {
        int earning = 0;
        if(this.isOpen)
        {
            for(int i = 0; i < 3; i++)
            {
                int customers = foods[i].calculateCustomers();
                earning += (this.foods[i].getSellPrice() * customers);
            }
        }
        return earning;
    }

    //#region getter methods
    /**
     * This method returns the purchase price of the restaurant
     * @return the price of the restaurant
     */
    public int getPrice()
    {
        return this.purchasePrice;
    }

    /**
     * This method returns the opennes status of the restaurant
     * @return true if restaurant is open
     */
    public boolean isOpen()    
    {
        return this.isOpen;
    }

    public Food[] getFoods() {
        return foods;
    }

    //#endregion
}
