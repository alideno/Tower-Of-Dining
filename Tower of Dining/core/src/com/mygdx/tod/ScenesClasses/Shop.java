package com.mygdx.tod.ScenesClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
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
    private int[] foodCount = new int[3];
    private Stage stage;
    private TextField textField1, textField2, textField3;
    private int totalCost;
    Sound pageFlip = Gdx.audio.newSound(Gdx.files.internal("pageFlip.mp3"));

    public Shop(TowerOfDining game, int currentShop, TowerMenu towerMenu, int totalCost) {// for towermenu class
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

        stage = new Stage();
        this.totalCost = totalCost;

        nextButton = new Button(buttonStyle);
        nextButton.setColor(1f, 1f, 1f, 0f);
        nextButton.setBounds(1500, 790, 390, 110);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pageFlip.play(1);
                updateTotal();
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
        previousButton.setColor(1f, 1f, 1f, 0f);
        previousButton.setBounds(1500, 670, 390, 110);
        previousButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pageFlip.play(1);
                updateTotal();
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
        backButton.setColor(1f, 1f, 1f, 0f);
        goBack();

        orderButton = new Button(buttonStyle);
        orderButton.setBounds(1100, 60, 520, 120);
        orderButton.setColor(1f, 1f, 1f, 0f);
        orderButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                updateTotal();
                purchase();
            }

        });
        orderButton.addListener(new InputListener() {
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
        TextFieldStyle textStyle = new TextFieldStyle(new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt")),
                Color.BLACK, null, null, null);
        textStyle.font.getData().setScale(3, 3);
        textField1 = new TextField("0", textStyle);
        textField1.setPosition(1250, 600);
        textField1.setSize(200, 100);
        textField2 = new TextField("0", textStyle);
        textField2.setPosition(1250, 410);
        textField2.setSize(200, 100);
        textField3 = new TextField("0", textStyle);
        textField3.setPosition(1250, 220);
        textField3.setSize(200, 100);

        stage.addActor(nextButton);
        stage.addActor(previousButton);
        stage.addActor(backButton);
        stage.addActor(orderButton);
        stage.addActor(textField1);
        stage.addActor(textField2);
        stage.addActor(textField3);
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

    public void updateTotal() throws NumberFormatException {

        int one = Integer.parseInt(textField1.getText());
        int two = Integer.parseInt(textField2.getText());
        int three = Integer.parseInt(textField3.getText());
        foodCount[0] = one;
        foodCount[1] = two;
        foodCount[2] = three;

        totalCost = +towerMenu.getRestaurants()[currentShop].getFoods()[0].getShopPrice() * one
                + towerMenu.getRestaurants()[currentShop].getFoods()[1].getShopPrice() * two
                + towerMenu.getRestaurants()[currentShop].getFoods()[2].getShopPrice() * three;

    }

    public void purchase() {
        for (int i = 0; i < foodCount.length; i++) {

            towerMenu.getRestaurants()[currentShop].getFoods()[i].addStock(foodCount[i]);

        }
        towerMenu.reduceMoney(totalCost);
    }

    public void nextClick() {
        if (currentShop != 7) {
            game.closeScreen();
            game.newScreen(new Shop(game, currentShop + 1, towerMenu, totalCost));
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        }

    }

    public void prevClick() {
        if (currentShop != 0) {
            game.closeScreen();
            game.newScreen(new Shop(game, currentShop - 1, towerMenu, totalCost));
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        }
    }

    public int getCurrentShop() {
        return currentShop;
    }

    public int[] getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int[] foodCount) {
        this.foodCount = foodCount;
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
        BitmapFont font = new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt"));
        font.getData().setScale(3, 3);
        font.setColor(Color.BLACK);
        font.draw(game.batch, towerMenu.getRestaurants()[currentShop].getFoods()[0].getStock() + "/40", 400, 700);
        font.draw(game.batch, towerMenu.getRestaurants()[currentShop].getFoods()[0].getShopPrice() + "", 890, 700);
        font.draw(game.batch, towerMenu.getRestaurants()[currentShop].getFoods()[1].getStock() + "/40", 400, 510);
        font.draw(game.batch, towerMenu.getRestaurants()[currentShop].getFoods()[1].getShopPrice() + "", 890, 510);
        font.draw(game.batch, towerMenu.getRestaurants()[currentShop].getFoods()[2].getStock() + "/40", 400, 320);
        font.draw(game.batch, towerMenu.getRestaurants()[currentShop].getFoods()[2].getShopPrice() + "", 890, 320);
        font.draw(game.batch, totalCost + "", 700, 150);

        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}