package com.mygdx.tod.ScenesClasses;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.tod.TowerOfDining;
import com.mygdx.tod.itemClasses.Restaurant;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

/**
 * This class represents the screen when the game starts
 * 
 * @author Eren Berk Eraslan
 * 
 * @param game
 */

public class TowerMenu extends ScreenAdapter {
    TowerOfDining game;
    PriceManagementMenu priceManagementMenu;
    Texture towerMenuImg;
    private int totalMoney = 0;
    private int day = 0;
    public Restaurant[] restaurants;
    private Button[] restaurantButtons;
    private Button[] forSaleButtons;
    private static boolean[] isOpen;
    private Stage stage;

    public TowerMenu(TowerOfDining game) {
        this.game = game;
        towerMenuImg = new Texture("tower.png");
        restaurants = new Restaurant[8];
        restaurantButtons = new Button[8];
        forSaleButtons = new Button[7];
        priceManagementMenu = new PriceManagementMenu(game);
        isOpen = new boolean[7];
        isOpen[0] = false;
        isOpen[1] = false;
        isOpen[2] = false;
        isOpen[3] = false;
        isOpen[4] = false;
        isOpen[5] = false;
        isOpen[6] = false;

        stage = new Stage();
        defineRestaurants();
        placeForSaleButtons();
        placeRestaurantButtons();
        shopButton();
        settingsButton();
    }

    public void defineRestaurants() {
        restaurants[0] = new Restaurant(2000);
        restaurants[1] = new Restaurant(5000);
        restaurants[2] = new Restaurant(6000);
        restaurants[3] = new Restaurant(7000);
        restaurants[4] = new Restaurant(8000);
        restaurants[5] = new Restaurant(9000);
        restaurants[6] = new Restaurant(10000);
        restaurants[7] = new Restaurant(11000);
    }

    public void nextDayButton() {
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;
        Button nextDayButton = new Button(buttonStyle);
        nextDayButton.setBounds(1760, 930, 130, 130);
        nextDayButton.setColor(1, 1, 1, 0);
        nextDayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO
                // TODO

            }

        });
        nextDayButton.addListener(new InputListener() {
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
        stage.addActor(nextDayButton);

    }

    public void settingsButton() {
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;
        Button settingsButton = new Button(buttonStyle);
        settingsButton.setBounds(1760, 930, 130, 130);
        settingsButton.setColor(1, 1, 1, 0);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO
                // TODO

            }

        });
        settingsButton.addListener(new InputListener() {
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
        stage.addActor(settingsButton);

    }

    public void shopButton() {
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;
        Button shopButton = new Button(buttonStyle);
        shopButton.setBounds(1350, 285, 500, 500);
        shopButton.setColor(1, 1, 1, 0);
        shopButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleShopButtonClick();
            }

        });
        shopButton.addListener(new InputListener() {
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
        stage.addActor(shopButton);

    }

    protected void handleShopButtonClick() {
        game.newScreen(new Shop(game, 0, this));
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
    }

    // This method creates 7 "For Sale" buttons and determines what they do
    // #region @placeForSaleButtons
    public void placeForSaleButtons() {
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;
        forSaleButtons[0] = new Button(buttonStyle);
        forSaleButtons[0].setBounds(645, 285, 310, 140);
        forSaleButtons[0].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }

        });
        forSaleButtons[0].addListener(new InputListener() {
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

        forSaleButtons[1] = new Button(buttonStyle);
        forSaleButtons[1].setBounds(325, 445, 310, 150);
        forSaleButtons[1].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }

        });
        forSaleButtons[1].addListener(new InputListener() {
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

        forSaleButtons[2] = new Button(buttonStyle);
        forSaleButtons[2].setBounds(645, 445, 310, 150);
        forSaleButtons[2].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }

        });
        forSaleButtons[2].addListener(new InputListener() {
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

        forSaleButtons[3] = new Button(buttonStyle);
        forSaleButtons[3].setBounds(325, 605, 310, 150);
        forSaleButtons[3].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleFirstButtonClick();
            }

        });
        forSaleButtons[3].addListener(new InputListener() {
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

        forSaleButtons[4] = new Button(buttonStyle);
        forSaleButtons[4].setBounds(645, 605, 310, 150);
        forSaleButtons[4].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleFirstButtonClick();
            }

        });
        forSaleButtons[4].addListener(new InputListener() {
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

        forSaleButtons[5] = new Button(buttonStyle);
        forSaleButtons[5].setBounds(325, 765, 310, 150);
        forSaleButtons[5].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleFirstButtonClick();
            }

        });
        forSaleButtons[5].addListener(new InputListener() {
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

        forSaleButtons[6] = new Button(buttonStyle);
        forSaleButtons[6].setBounds(645, 765, 310, 150);
        forSaleButtons[6].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }

        });
        forSaleButtons[6].addListener(new InputListener() {
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

        stage.addActor(forSaleButtons[0]);
        stage.addActor(forSaleButtons[1]);
        stage.addActor(forSaleButtons[2]);
        stage.addActor(forSaleButtons[3]);
        stage.addActor(forSaleButtons[4]);
        stage.addActor(forSaleButtons[5]);
        stage.addActor(forSaleButtons[6]);
        render(5);
    }
    // #endregion @placeForSaleButtons

    public void placeRestaurantButtons() {
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;
        restaurantButtons[0] = new Button(buttonStyle);
        restaurantButtons[0].setBounds(325, 285, 310, 140);
        restaurantButtons[0].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[0].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.closeScreen();
                game.newScreen(priceManagementMenu);
                System.out.println("aaa");
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }

        });
        restaurantButtons[0].addListener(new InputListener() {
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

        restaurantButtons[1] = new Button(buttonStyle);
        restaurantButtons[1].setBounds(325, 445, 310, 150);
        restaurantButtons[1].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[1].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleFirstButtonClick();
            }

        });
        restaurantButtons[1].addListener(new InputListener() {
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

        restaurantButtons[2] = new Button(buttonStyle);
        restaurantButtons[2].setBounds(645, 445, 310, 150);
        restaurantButtons[2].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[2].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleFirstButtonClick();
            }

        });
        restaurantButtons[2].addListener(new InputListener() {
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

        restaurantButtons[3] = new Button(buttonStyle);
        restaurantButtons[3].setBounds(325, 605, 310, 150);
        restaurantButtons[3].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[3].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleFirstButtonClick();
            }

        });
        restaurantButtons[3].addListener(new InputListener() {
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

        restaurantButtons[4] = new Button(buttonStyle);
        restaurantButtons[4].setBounds(645, 605, 310, 150);
        restaurantButtons[4].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[4].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleFirstButtonClick();
            }

        });
        restaurantButtons[4].addListener(new InputListener() {
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

        restaurantButtons[5] = new Button(buttonStyle);
        restaurantButtons[5].setBounds(325, 765, 310, 150);
        restaurantButtons[5].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[5].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleFirstButtonClick();
            }

        });
        restaurantButtons[5].addListener(new InputListener() {
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

        restaurantButtons[6] = new Button(buttonStyle);
        restaurantButtons[6].setBounds(645, 765, 310, 150);
        restaurantButtons[6].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[6].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleFirstButtonClick();
            }

        });
        restaurantButtons[6].addListener(new InputListener() {
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
        restaurantButtons[7] = new Button(buttonStyle);
        restaurantButtons[7].setBounds(325, 445, 310, 150);
        restaurantButtons[7].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[7].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleFirstButtonClick();
            }

        });
        restaurantButtons[7].addListener(new InputListener() {
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

        stage.addActor(restaurantButtons[0]);
        stage.addActor(restaurantButtons[1]);
        stage.addActor(restaurantButtons[2]);
        stage.addActor(restaurantButtons[3]);
        stage.addActor(restaurantButtons[4]);
        stage.addActor(restaurantButtons[5]);
        stage.addActor(restaurantButtons[6]);
        stage.addActor(restaurantButtons[7]);
        render(5);
    }

    // This method opens price management screen for the first restaurant
    protected void handleFirstButtonClick() {
    }

    public void addMoney(int earning) {
        totalMoney += earning;
    }

    public void reduceMoney(int loss) {
        totalMoney -= loss;
    }

    public int getMoney() {
        return totalMoney;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(towerMenuImg, 0, 0);
        BitmapFont font = new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt"));
        font.getData().setScale(1, 1);
        font.draw(game.batch, "Total Money: " + totalMoney, 80, 1000);
        font.draw(game.batch, "Day: " + day, 82, 970);

        game.batch.end();

        for (int i = 0; i < isOpen.length; i++) {
            if (isOpen[i] == true) {
                forSaleButtons[i].setVisible(false);
                restaurantButtons[i + 1].setVisible(true);
            } else if (restaurantButtons[i] != null) {
                forSaleButtons[i].setVisible(true);
                restaurantButtons[i + 1].setVisible(false);
            }
        }

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
