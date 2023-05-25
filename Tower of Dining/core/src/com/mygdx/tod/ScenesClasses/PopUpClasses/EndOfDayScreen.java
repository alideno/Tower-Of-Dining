package com.mygdx.tod.ScenesClasses.PopUpClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.tod.TowerOfDining;
import com.mygdx.tod.itemClasses.Restaurant;

/**
 * This class represents a popup that happens at the end of a day
 * 
 * @author Deniz Şahin
 */
public class EndOfDayScreen extends PopUp{
    
    //defining the variables
    private Restaurant[] restaurants = new Restaurant[8];
    private int[] earnings = new int[8];

    public EndOfDayScreen(TowerOfDining game)
    {
        super(game);
        

        popupTexture = new Texture(Gdx.files.internal("dayend.png"));
        
        //TODO 
        /*restaurants = game.getRestaurants();
        for(int i= 0; i < 8; i++)
        {
            earnings[i] = restaurants[i].endDay();
        }*/

        render(1);
    }

    /**
     * Renders the screen
     */
    public void render(float delta) {
        game.batch.begin();

        game.batch.draw(popupTexture, 0, 0);

        BitmapFont font = new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt"));
        font.getData().setScale(4);
        font.draw(game.batch, "12", 140, 150);

        game.batch.end();
    }

}
