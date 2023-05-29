package com.mygdx.tod.ScenesClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tod.TowerOfDining;

public class Shop extends ScreenAdapter {
    Texture menuImg;
    private TowerOfDining game;
    private TowerMenu towerMenu;
    private Button nextButton, previousButton, orderButton, backButton;
    private int currentShop;
    int[] foodCount;
    private Stage stage;

    public Shop(TowerOfDining game, int currentShopx, TowerMenu towerMenu) {// for towermenu class
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;
        this.game = game;
        this.towerMenu = towerMenu;
        this.currentShop = currentShop;
        String temp = "shop menu" + (currentShop + 1) + ".png";
        menuImg = new Texture(temp);
        foodCount = new int[24];
        for (int i = 0; i < foodCount.length; i++) {
            foodCount[i] = 0;
        }
        stage = new Stage();

        nextButton = new Button(buttonStyle);
        nextButton.setColor(1f,1f,1f,0f);
        nextButton.setBounds(1500, 790, 390, 110);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                nextClick();

            }

        });
        nextButton.addListener(new InputListener() {
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

        previousButton = new Button(buttonStyle);
        previousButton.setColor(1f,1f,1f,0f);
        previousButton.setBounds(1500, 670, 390, 110);
        previousButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prevClick();

            }

        });
        previousButton.addListener(new InputListener() {
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
        backButton = new Button(buttonStyle);
        backButton.setBounds(1100, 920, 750, 120);
        backButton.setColor(1f,1f,1f,0f);
        stage.addActor(nextButton);
        stage.addActor(previousButton);
        stage.addActor(backButton);
        goBack();
    }

    public void goBack() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                towerMenu.render(1);

                game.closeScreen();

            }

        });

        backButton.addListener(new InputListener() {
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
        render(1);
    }

    public void nextClick(){
        if (currentShop != 7) {
            render(currentShop);
            game.closeScreen();
            game.newScreen(new Shop(game, currentShop+1, foodCount));
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        }
    }
    public void prevClick(){
        if (currentShop != 0) {
            game.closeScreen();
            game.newScreen(new Shop(game, currentShop-1, foodCount));
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        }
    }

    public Shop(TowerOfDining game, int currentShop, int[] foodCount) { // ONLY USE IN THIS CLASS
        Shop shop = new Shop(game, currentShop, towerMenu);
        shop.foodCount = foodCount;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(menuImg, 0, 0);
        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
