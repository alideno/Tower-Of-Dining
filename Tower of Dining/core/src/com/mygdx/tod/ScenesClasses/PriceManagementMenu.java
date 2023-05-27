package com.mygdx.tod.ScenesClasses;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import javax.print.attribute.standard.Sides;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tod.TowerOfDining;
import com.mygdx.tod.itemClasses.Food;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;



/**
 * This class is a window for the Price management of "Foods" on the menu 
 * @author Rasul Ibrahimzade
 * 
 * @param game window
 */


public class PriceManagementMenu extends ScreenAdapter
{
    Texture priceMenuImg;
    private TowerOfDining game;
    Window priceMenu;
    ArrayList<Food> menu;

    double sellingPrice1 = 0;
    double sellingPrice2 = 0;       // The price of the food that will be sold to the customer, controlled by the player
    double sellingPrice3 = 0;

    Slider foodSlider1;
    Slider foodSlider2;         // Used to assign sellingPrice for the respective food on the menu
    Slider foodSlider3;

    Button minusButton1;        // Decreases the sellingPrice1 of the respective food on the menu
    Button plusButton1;         // Increases the sellingPrice1 of the respective food on the menu
    Button minusButton2;
    Button plusButton2;
    Button minusButton3;
    Button plusButton3;
    private Button exitButton;  // Exits the  

    private Stage stage;


    // Price management menus should are to be created beforehand and saved in a variable for a later use, since
    // the current amount of restaurants is FINAL. This class is  intended to be called as x1, x2, x3 and etc...
    // The .setScreen(new PriceManagerMenu (TowerOfDining tod) ) is NOT the correct use of this class

    public PriceManagementMenu (TowerOfDining tod)
    {
        this.game = tod;
        menu = new ArrayList<Food>();
        priceMenuImg = new Texture("price .png");
        allButtonsClick();  // Makes all buttons pressable


        stage = new Stage();
        stage.addActor(plusButton1);
        stage.addActor(minusButton1);
        stage.addActor(plusButton2);
        stage.addActor(minusButton2);
        stage.addActor(plusButton3);
        stage.addActor(minusButton3);
        stage.addActor(exitButton);
        render(5);
    }

    public void render(float delta) 
    {
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(priceMenuImg, 0, 0);
        game.batch.end();
        
        stage.act(delta); 
        stage.draw(); 
    }

    public void allButtonsClick ()
    {

        // Sorts out the buttons and assigns them functions 
        // TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        // TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        // buttonStyle.up = upDrawable;
        // buttonStyle.down = downDrawable;
        int buttonSize = 43;
        int plusXplacement = 1429;
        int minusXplacement = 1356;

        minusButton1 = new Button(buttonStyle);
        minusButton1.setColor(Color.RED);
        minusButton1.setBounds(minusXplacement, 630,buttonSize, buttonSize);

        plusButton1 = new Button(buttonStyle);
        plusButton1.setColor(Color.RED);
        plusButton1.setBounds(plusXplacement, 630,buttonSize, buttonSize);

        minusButton2 = new Button(buttonStyle);
        minusButton2.setColor(Color.RED);
        minusButton2.setBounds(minusXplacement, 477,buttonSize, buttonSize);

        plusButton2 = new Button(buttonStyle);
        plusButton2.setColor(Color.RED);
        plusButton2.setBounds(plusXplacement, 477,buttonSize, buttonSize);

        minusButton3 = new Button(buttonStyle);
        minusButton3.setColor(Color.RED);
        minusButton3.setBounds(minusXplacement, 320,buttonSize, buttonSize);

        plusButton3 = new Button(buttonStyle);
        plusButton3.setColor(Color.RED);
        plusButton3.setBounds(plusXplacement, 320,buttonSize, buttonSize);

        exitButton = new Button(buttonStyle);
        exitButton.setColor(Color.RED);
        exitButton.setBounds(1679, 802, 57,58);



        minusButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
            }

        });
        minusButton1.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Set the cursor to Hand when the mouse enters the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Restore the default cursor when the mouse exits the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        plusButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
            }

        });
        plusButton1.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Set the cursor to Hand when the mouse enters the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Restore the default cursor when the mouse exits the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        minusButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
            }

        });
        minusButton2.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Set the cursor to Hand when the mouse enters the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Restore the default cursor when the mouse exits the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        plusButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
            }

        });
        plusButton2.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Set the cursor to Hand when the mouse enters the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Restore the default cursor when the mouse exits the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        minusButton3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
            }

        });
        minusButton3.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Set the cursor to Hand when the mouse enters the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Restore the default cursor when the mouse exits the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        plusButton3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
            }

        });
        plusButton3.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Set the cursor to Hand when the mouse enters the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Restore the default cursor when the mouse exits the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
            }

        });
        exitButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Set the cursor to Hand when the mouse enters the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Restore the default cursor when the mouse exits the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });


    }

    public void show() {
        Gdx.input.setInputProcessor(stage); 
    }



}
