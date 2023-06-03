package com.mygdx.tod.ScenesClasses;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.tod.TowerOfDining;
import com.mygdx.tod.itemClasses.Customer;
import com.mygdx.tod.itemClasses.Food;
import com.mygdx.tod.ScenesClasses.PopUpClasses.EndOfDayScreen;
import com.mygdx.tod.ScenesClasses.PopUpClasses.NameEntry;
import com.mygdx.tod.ScenesClasses.PopUpClasses.PurchaseRestaurant;
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
    private TowerMenu towerMenu;
    Texture towerMenuImg;
    private int totalMoney = 5000;
    private int day = 0;
    public Restaurant[] restaurants;
    public PriceManagementMenu[] priceManagementMenus;
    public Button[] restaurantButtons;
    public Button[] forSaleButtons;
    private static boolean[] isOpen;
    Sound soundPurchaseRes = Gdx.audio.newSound(Gdx.files.internal("soundPurchaseRes.wav"));
    Sound coinDrop = Gdx.audio.newSound(Gdx.files.internal("coinDrop.mp3"));
    MainMenu main;
    private Stage stage;
    int n = 0;
    ArrayList<Customer> customers = new ArrayList<Customer>();
    ArrayList<Customer> removeCustomers = new ArrayList<Customer>();
    Customer customer1 = new Customer(1);

    public TowerMenu(TowerOfDining game, MainMenu main) {
        this.main = main;
        towerMenu = this;
        customers.add(customer1);
        this.game = game;
        towerMenuImg = new Texture("tower.png");
        restaurants = new Restaurant[8];
        priceManagementMenus = new PriceManagementMenu[8];
        restaurantButtons = new Button[8];
        forSaleButtons = new Button[8];
        priceManagementMenu = new PriceManagementMenu(game, this);
        isOpen = new boolean[8];
        isOpen[0] = true;
        isOpen[1] = false;
        isOpen[2] = false;
        isOpen[3] = false;
        isOpen[4] = false;
        isOpen[5] = false;
        isOpen[6] = false;
        isOpen[7] = false;

        stage = new Stage();
        defineRestaurants();
        definePriceManegementMenus();
        placeForSaleButtons();
        placeRestaurantButtons();
        shopButton();
        nextDayButton();
        settingsButton();
        forSaleButtons[0].remove();
        for (int index = 0; index < restaurantButtons.length - 1; index++) {
            restaurantButtons[index + 1].setVisible(false);
        }

    }

    public void defineRestaurants() {

        restaurants[0] = new Restaurant(2000, this);
        restaurants[0].addFoodItem(new Food(10, "Burger"));
        restaurants[0].addFoodItem(new Food(10, "Fries"));
        restaurants[0].addFoodItem(new Food(20, "Drink"));

        restaurants[1] = new Restaurant(5000, this);
        restaurants[1].addFoodItem(new Food(15, "Fried Chicken"));
        restaurants[1].addFoodItem(new Food(10, "Bucket"));
        restaurants[1].addFoodItem(new Food(20, "Sauce"));

        restaurants[2] = new Restaurant(6000, this);
        restaurants[2].addFoodItem(new Food(20, "Doner"));
        restaurants[2].addFoodItem(new Food(10, "Bread"));
        restaurants[2].addFoodItem(new Food(5, "Ayran"));

        restaurants[3] = new Restaurant(7000, this);
        restaurants[3].addFoodItem(new Food(40, "Coffee"));
        restaurants[3].addFoodItem(new Food(45, "Ice Coffee"));
        restaurants[3].addFoodItem(new Food(30, "Milked Coffee"));

        restaurants[4] = new Restaurant(8000, this);
        restaurants[4].addFoodItem(new Food(15, "Sandwich"));
        restaurants[4].addFoodItem(new Food(10, "Wrap"));
        restaurants[4].addFoodItem(new Food(30, "Toast"));

        restaurants[5] = new Restaurant(9000, this);
        restaurants[5].addFoodItem(new Food(70, "Pepperoni P"));
        restaurants[5].addFoodItem(new Food(50, "Veggie Pizza"));
        restaurants[5].addFoodItem(new Food(40, "Cheese Pizza"));

        restaurants[6] = new Restaurant(10000, this);
        restaurants[6].addFoodItem(new Food(60, "Cake"));
        restaurants[6].addFoodItem(new Food(30, "Cookie"));
        restaurants[6].addFoodItem(new Food(40, "Ekler"));

        restaurants[7] = new Restaurant(11000, this);
        restaurants[7].addFoodItem(new Food(200, "Sushi"));
        restaurants[7].addFoodItem(new Food(190, "Tempura Roll"));
        restaurants[7].addFoodItem(new Food(180, "Nigiri"));

    }

    public void definePriceManegementMenus() {
        priceManagementMenus[0] = new PriceManagementMenu(game, towerMenu);
        priceManagementMenus[0].addFoodToMenu(restaurants[0].getFoods()[0]);
        priceManagementMenus[0].addFoodToMenu(restaurants[0].getFoods()[1]);
        priceManagementMenus[0].addFoodToMenu(restaurants[0].getFoods()[2]);
        priceManagementMenus[0].setPriceMenuImg("price1.png");

        priceManagementMenus[1] = new PriceManagementMenu(game, towerMenu);
        priceManagementMenus[1].addFoodToMenu(restaurants[1].getFoods()[0]);
        priceManagementMenus[1].addFoodToMenu(restaurants[1].getFoods()[1]);
        priceManagementMenus[1].addFoodToMenu(restaurants[1].getFoods()[2]);
        priceManagementMenus[1].setPriceMenuImg("price2.png");

        priceManagementMenus[2] = new PriceManagementMenu(game, towerMenu);
        priceManagementMenus[2].addFoodToMenu(restaurants[2].getFoods()[0]);
        priceManagementMenus[2].addFoodToMenu(restaurants[2].getFoods()[1]);
        priceManagementMenus[2].addFoodToMenu(restaurants[2].getFoods()[2]);
        priceManagementMenus[2].setPriceMenuImg("price3.png");

        priceManagementMenus[3] = new PriceManagementMenu(game, towerMenu);
        priceManagementMenus[3].addFoodToMenu(restaurants[3].getFoods()[0]);
        priceManagementMenus[3].addFoodToMenu(restaurants[3].getFoods()[1]);
        priceManagementMenus[3].addFoodToMenu(restaurants[3].getFoods()[2]);
        priceManagementMenus[3].setPriceMenuImg("price4.png");

        priceManagementMenus[4] = new PriceManagementMenu(game, towerMenu);
        priceManagementMenus[4].addFoodToMenu(restaurants[4].getFoods()[0]);
        priceManagementMenus[4].addFoodToMenu(restaurants[4].getFoods()[1]);
        priceManagementMenus[4].addFoodToMenu(restaurants[4].getFoods()[2]);
        priceManagementMenus[4].setPriceMenuImg("price5.png");

        priceManagementMenus[5] = new PriceManagementMenu(game, towerMenu);
        priceManagementMenus[5].addFoodToMenu(restaurants[5].getFoods()[0]);
        priceManagementMenus[5].addFoodToMenu(restaurants[5].getFoods()[1]);
        priceManagementMenus[5].addFoodToMenu(restaurants[5].getFoods()[2]);
        priceManagementMenus[5].setPriceMenuImg("price6.png");

        priceManagementMenus[6] = new PriceManagementMenu(game, towerMenu);
        priceManagementMenus[6].addFoodToMenu(restaurants[6].getFoods()[0]);
        priceManagementMenus[6].addFoodToMenu(restaurants[6].getFoods()[1]);
        priceManagementMenus[6].addFoodToMenu(restaurants[6].getFoods()[2]);
        priceManagementMenus[6].setPriceMenuImg("price7.png");

        priceManagementMenus[7] = new PriceManagementMenu(game, towerMenu);
        priceManagementMenus[7].addFoodToMenu(restaurants[7].getFoods()[0]);
        priceManagementMenus[7].addFoodToMenu(restaurants[7].getFoods()[1]);
        priceManagementMenus[7].addFoodToMenu(restaurants[7].getFoods()[2]);
        priceManagementMenus[7].setPriceMenuImg("price8.png");

    }

    public void nextDayButton() {
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("salesign.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;
        Button nextDayButton = new Button(buttonStyle);
        nextDayButton.setBounds(1600, 110, 300, 70);
        nextDayButton.setColor(1, 1, 1, 0);
        nextDayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                coinDrop.play(1);
                customers = new ArrayList<Customer>();
                removeCustomers = new ArrayList<Customer>();
                day++;
                if (day == 10) {
                    game.newScreen(new NameEntry(game, towerMenu));
                } else {
                    game.newScreen(new EndOfDayScreen(game, towerMenu));
                    Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
                }
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
                game.newScreen(new Settings(game, towerMenu, 1, main));

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
        int[] foods = new int[3];
        for (int i = 0; i < foods.length; i++) {
            foods[i] = 0;
        }
        game.newScreen(new Shop(game, 0, this, 0));
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
        forSaleButtons[0].setBounds(325, 285, 310, 140);

        forSaleButtons[1] = new Button(buttonStyle);
        forSaleButtons[1].setBounds(645, 285, 310, 140);
        forSaleButtons[1].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.newScreen(new PurchaseRestaurant(game, restaurants[1], towerMenu, 1));
                soundPurchaseRes.play(1);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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
        forSaleButtons[2].setBounds(325, 445, 310, 150);
        forSaleButtons[2].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.newScreen(new PurchaseRestaurant(game, restaurants[2], towerMenu, 2));
                soundPurchaseRes.play(1);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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
        forSaleButtons[3].setBounds(645, 445, 310, 150);
        forSaleButtons[3].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.newScreen(new PurchaseRestaurant(game, restaurants[3], towerMenu, 3));
                soundPurchaseRes.play(1);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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
        forSaleButtons[4].setBounds(325, 605, 310, 150);
        forSaleButtons[4].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.newScreen(new PurchaseRestaurant(game, restaurants[4], towerMenu, 4));
                soundPurchaseRes.play(1);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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
        forSaleButtons[5].setBounds(645, 605, 310, 150);
        forSaleButtons[5].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.newScreen(new PurchaseRestaurant(game, restaurants[5], towerMenu, 5));
                soundPurchaseRes.play(1);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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
        forSaleButtons[6].setBounds(325, 765, 310, 150);
        forSaleButtons[6].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.newScreen(new PurchaseRestaurant(game, restaurants[6], towerMenu, 6));
                soundPurchaseRes.play(1);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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

        forSaleButtons[7] = new Button(buttonStyle);
        forSaleButtons[7].setBounds(645, 765, 310, 150);
        forSaleButtons[7].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.newScreen(new PurchaseRestaurant(game, restaurants[7], towerMenu, 7));
                soundPurchaseRes.play(1);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }

        });
        forSaleButtons[7].addListener(new InputListener() {
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
        stage.addActor(forSaleButtons[7]);
        render(5);
    }
    // #endregion @placeForSaleButtons

    // #region placeRestaurantButtons
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
                game.setScreen(priceManagementMenus[0]);
        

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
        restaurantButtons[1].setBounds(645, 285, 310, 150);
        restaurantButtons[1].setColor(1, 1, 1, 0);
        restaurantButtons[1].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(priceManagementMenus[1]);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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
        restaurantButtons[2].setBounds(325, 435, 310, 150);
        restaurantButtons[2].setColor(1, 1, 1, 0);
        restaurantButtons[2].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(priceManagementMenus[2]);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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
        restaurantButtons[3].setBounds(645, 435, 310, 150);
        restaurantButtons[3].setColor(1, 1, 1, 0);
        restaurantButtons[3].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(priceManagementMenus[3]);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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
        restaurantButtons[4].setBounds(325, 605, 310, 150);
        restaurantButtons[4].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[4].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(priceManagementMenus[4]);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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
        restaurantButtons[5].setBounds(645, 605, 310, 150);
        restaurantButtons[5].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[5].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(priceManagementMenus[5]);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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
        restaurantButtons[6].setBounds(325, 765, 310, 150);
        restaurantButtons[6].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[6].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(priceManagementMenus[6]);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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
        restaurantButtons[7].setBounds(645, 765, 310, 150);
        restaurantButtons[7].setColor(1f, 1f, 1f, 0f);
        restaurantButtons[7].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(priceManagementMenus[7]);
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
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

    // #endregion placeRestaurantButtons
    public void addMoney(int earning) {
        totalMoney += earning;
    }

    public void reduceMoney(int loss) {
        totalMoney -= loss;
    }

    public int getMoney() {
        return totalMoney;
    }

    public Restaurant[] getRestaurants() {
        return restaurants;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    // #region Animation
    public void Animation() {
        for (Customer customer : customers) // Customer class Animation Code. I have created a customers Arraylist for
                                            // this
        {
            TextureRegion currentFrame = (TextureRegion) customer.geAnimation().getKeyFrame(customer.getElapsedTime(),
                    true);
            game.batch.draw(currentFrame, customer.getX(), customer.getY());
            customer.update();
            removeCustomers.add(customer);
        }

        for (Customer customer : removeCustomers) {
            if (customer.getX() > 2000) {
                customers.remove(customer);
            }
        }

        if (n == 40) {
            addCustomer();
            n = 0;
        }
        n++;

    }
    // #endregion

    public void addCustomer() {
        java.util.Random random = new java.util.Random();
        int rand = random.nextInt(8);


        if (isOpen[rand] == true) {
            customers.add(new Customer(rand + 1));

        }

        else {
            addCustomer();
        }
    }

    public void setOpen(int restaurantNumber) {
        isOpen[restaurantNumber] = true;
    }

    public void setFoodsSellingPrices() {
        restaurants[0].setFoodsSellingPrices(priceManagementMenus[0].getSellPrice1(),
                priceManagementMenus[0].getSellPrice2(), priceManagementMenus[0].getSellPrice3());
        restaurants[1].setFoodsSellingPrices(priceManagementMenus[1].getSellPrice1(),
                priceManagementMenus[1].getSellPrice2(), priceManagementMenus[1].getSellPrice3());
        restaurants[2].setFoodsSellingPrices(priceManagementMenus[2].getSellPrice1(),
                priceManagementMenus[2].getSellPrice2(), priceManagementMenus[2].getSellPrice3());
        restaurants[3].setFoodsSellingPrices(priceManagementMenus[3].getSellPrice1(),
                priceManagementMenus[3].getSellPrice2(), priceManagementMenus[3].getSellPrice3());
        restaurants[4].setFoodsSellingPrices(priceManagementMenus[4].getSellPrice1(),
                priceManagementMenus[4].getSellPrice2(), priceManagementMenus[4].getSellPrice3());
        restaurants[5].setFoodsSellingPrices(priceManagementMenus[5].getSellPrice1(),
                priceManagementMenus[5].getSellPrice2(), priceManagementMenus[5].getSellPrice3());
        restaurants[6].setFoodsSellingPrices(priceManagementMenus[6].getSellPrice1(),
                priceManagementMenus[6].getSellPrice2(), priceManagementMenus[6].getSellPrice3());
        restaurants[7].setFoodsSellingPrices(priceManagementMenus[7].getSellPrice1(),
                priceManagementMenus[7].getSellPrice2(), priceManagementMenus[7].getSellPrice3());

    }

    @Override
    public void render(float delta) {

        setFoodsSellingPrices();
        ScreenUtils.clear(1, 0, 0, 1);

        game.batch.begin();

        game.batch.draw(towerMenuImg, 0, 0);

        BitmapFont font = new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt"));
        font.getData().setScale(1, 1);

        font.draw(game.batch, "Total Money: " + totalMoney, 80, 1000);

        font.draw(game.batch, "Day: " + day, 82, 970);

        font.getData().setScale(2, 2);
        font.draw(game.batch, "Next Day", 1600, 170);

        Animation();

        game.batch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}