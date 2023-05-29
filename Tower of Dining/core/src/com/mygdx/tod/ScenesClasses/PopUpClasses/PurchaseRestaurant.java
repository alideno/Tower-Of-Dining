package com.mygdx.tod.ScenesClasses.PopUpClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.tod.TowerOfDining;
import com.mygdx.tod.ScenesClasses.TowerMenu;
import com.mygdx.tod.itemClasses.Restaurant;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * This is a class representing the popup that prompts the user about purchasing a restaurant
 * 
 * @author Deniz Şahin
 */
public class PurchaseRestaurant extends PopUp{

    //defining the variables
    private boolean purchasable;
    private Restaurant restaurant;
    private TowerMenu towerMenu;
    private Button noButton;
    private Button yesButton;
    private Stage buttonStage;
    private int restaurantNumber;

    /**
     * The constructor of the restaurant purchase popup
     * 
     * Selects the version based on the amount user has
     * @param game the game
     * @param restaurant the restaurant ready for purchase
     */
    public PurchaseRestaurant(TowerOfDining game, Restaurant restaurant, TowerMenu towerMenu, int restaurantNumber)
    {
        super(game);
        
        this.restaurant = restaurant;
        this.towerMenu = towerMenu;
        this.restaurantNumber = restaurantNumber;

        purchasable = (towerMenu.getMoney() >= restaurant.getPrice());

        if(purchasable)
        {
            popupTexture = new Texture(Gdx.files.internal("restaurant buy.png"));
        }
        else
        {
            popupTexture = new Texture(Gdx.files.internal("restaurant buy2.png"));
        }

        //code fragment taken from MainMenu class
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;

        noButton = new Button(buttonStyle);
        noButton.setColor(1f, 1f, 1f, 0f);
        noButton.setBounds(1080, 465, 210,85);
        
        noButton.addListener(new noButtonListener());

        noButton.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Set the cursor to Hand when the mouse enters the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Restore the default cursor when the mouse exits the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        buttonStage = new Stage();
        buttonStage.addActor(noButton);

        if(purchasable)
        {
            yesButton = new Button(buttonStyle);
            yesButton.setColor(1f, 1f, 1f, 0f);
            yesButton.setBounds(630, 465, 210,85);
            
            yesButton.addListener(new yesButtonListener());

            yesButton.addListener(new InputListener() {
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    // Set the cursor to Hand when the mouse enters the button
                    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                }
            
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    // Restore the default cursor when the mouse exits the button
                    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
                }
            });
            buttonStage.addActor(yesButton);
        }

        Gdx.input.setInputProcessor(buttonStage);

        this.render(5);
    }

    /**
     * This method renders the images
     */
    public void render(float delta) {
        game.batch.begin();
                
		game.batch.draw(popupTexture, 0, 0);

        BitmapFont font = new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt"));
        font.getData().setScale(3f);
        font.draw(game.batch, "" + restaurant.getPrice(), 800, 710);
        
		game.batch.end();

        buttonStage.act(delta);
        buttonStage.draw();
    }

    private class noButtonListener extends ClickListener{
        public void clicked(InputEvent event, float x, float y) {
                game.closeScreen();
        }
    }

    private class yesButtonListener extends ClickListener{
        public void clicked(InputEvent event, float x, float y) {
            restaurant.openRestaurant();
            towerMenu.reduceMoney(restaurant.getPrice());
            towerMenu.setOpen(restaurantNumber);
        }
    }

    @Override
    public void dispose() {
        buttonStage.dispose();
        super.dispose();
    }
}

