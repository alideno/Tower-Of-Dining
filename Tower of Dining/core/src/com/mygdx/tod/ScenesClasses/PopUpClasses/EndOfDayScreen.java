package com.mygdx.tod.ScenesClasses.PopUpClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import com.badlogic.gdx.graphics.Cursor;

/**
 * This class represents a popup that happens at the end of a day
 * 
 * @author Deniz Şahin
 */
public class EndOfDayScreen extends PopUp{
    
    //defining the variables
    private Restaurant[] restaurants = new Restaurant[8];
    private int[] earnings = new int[8];
    private Stage buttonStage;
    private Button nextButton;
    private TowerMenu towerMenu;
    private int totalEarnings;

    public EndOfDayScreen(TowerOfDining game, TowerMenu towerMenu)
    {
        super(game);
        this.towerMenu = towerMenu;
        
        //code fragment taken from MainMenu class
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;

        nextButton = new Button(buttonStyle);
        nextButton.setColor(1f, 1f, 1f, 0f);
        nextButton.setBounds(1325, 460, 435,385);
        
        nextButton.addListener(new nextButtonListener());

        nextButton.addListener(new InputListener() {
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
        buttonStage.addActor(nextButton);

        popupTexture = new Texture(Gdx.files.internal("dayend.png"));
        
        totalEarnings = 0;
        restaurants = towerMenu.getRestaurants();
        for(int i= 0; i < 8; i++)
        {
            earnings[i] = restaurants[i].endDay();
            totalEarnings += earnings[i];
        }

        Gdx.input.setInputProcessor(buttonStage);

        render(10);
    }

    /**
     * Renders the screen
     */
    public void render(float delta) {
        game.batch.begin();

        game.batch.draw(popupTexture, 0, 0);

        BitmapFont font = new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt"));
        font.getData().setScale(4.5f);

        for(int i = 0; i < 8; i++)
        {
            int column = i % 2;
            int row = i / 2;
            font.draw(game.batch, "" + earnings[i], 400 + column * 600, 210 + row * 200);
        }
        
        font.draw(game.batch, "" + totalEarnings, 400, 710);

        game.batch.end();

        buttonStage.act(delta);
        buttonStage.draw();
    }

    private class nextButtonListener extends ClickListener{
        public void clicked(InputEvent event, float x, float y) {
                game.closeScreen();
        }
    }

    @Override
    public void dispose() {
        buttonStage.dispose();        
        super.dispose();
    }

}
