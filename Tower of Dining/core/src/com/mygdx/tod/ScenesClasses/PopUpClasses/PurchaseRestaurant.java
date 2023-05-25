package com.mygdx.tod.ScenesClasses.PopUpClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.tod.TowerOfDining;
import com.mygdx.tod.itemClasses.Restaurant;

/**
 * This is a class representing the popup that prompts the user about purchasing a restaurant
 * 
 * @author Deniz Şahin
 */
public class PurchaseRestaurant extends PopUp{

    //defining the variables
    private boolean purchasable;

    /**
     * The constructor of the restaurant purchase popup
     * 
     * Selects the version based on the amount user has
     * @param game the game
     * @param restaurant the restaurant ready for purchase
     */
    public PurchaseRestaurant(TowerOfDining game, Restaurant restaurant)
    {
        super(game);
        
        //TODO
        purchasable = true;//(game.getMoney() > restaurant.getPrice());

        if(purchasable)
        {
            popupTexture = new Texture(Gdx.files.internal("restaurant buy.png"));
        }
        else
        {
            popupTexture = new Texture(Gdx.files.internal("restaurant buy2.png"));
        }

        this.render(5);
    }

    /**
     * This method renders the images
     */
    public void render(float delta) {
        game.batch.begin();
        
        if(Gdx.input.isTouched())
        {
            int xPosition = Gdx.input.getX();
            int yPosition = Gdx.input.getY();

            if(xPosition < 200)//TODO find button positions when screen is set properly
                System.exit(0);
        }
        
		game.batch.draw(popupTexture, 0, 0);
		game.batch.end();
    }


}
