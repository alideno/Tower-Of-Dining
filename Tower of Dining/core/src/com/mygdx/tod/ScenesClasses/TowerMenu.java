package com.mygdx.tod.ScenesClasses;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
    private int totalMoney = 1000000;
    private int day = 0;
    public Restaurant[] restaurants;
    public Button[] restaurantButtons;
    public Button[] forSaleButtons;
    private static boolean[] isOpen;
    Sound soundPurchaseRes = Gdx.audio.newSound(Gdx.files.internal("soundPurchaseRes.wav"));
    private Stage stage;
    int n = 0;
    ArrayList<Customer> customers = new ArrayList<Customer>();
    Customer customer1 = new Customer(1);

    public TowerMenu(TowerOfDining game) {
        towerMenu = this;
        customers.add(customer1);
        this.game = game;
        towerMenuImg = new Texture("tower.png");
        restaurants = new Restaurant[8];
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
        restaurants[0] = new Restaurant(2000);
        restaurants[0].addFoodItem(new Food(15, "Burger"));
        restaurants[0].addFoodItem(new Food(10, "Fries"));
        restaurants[0].addFoodItem(new Food(20, "Drink"));

        restaurants[1] = new Restaurant(5000);
        restaurants[1].addFoodItem(new Food(15, "Fried Chicken"));
        restaurants[1].addFoodItem(new Food(10, "Bucket"));
        restaurants[1].addFoodItem(new Food(20, "Sauce"));

        restaurants[2] = new Restaurant(6000);
        restaurants[2].addFoodItem(new Food(15, "Doner"));
        restaurants[2].addFoodItem(new Food(10, "Bread"));
        restaurants[2].addFoodItem(new Food(20, "Ayran"));

        restaurants[3] = new Restaurant(7000);
        restaurants[3].addFoodItem(new Food(15, "Coffee"));
        restaurants[3].addFoodItem(new Food(10, "Ice Coffee"));
        restaurants[3].addFoodItem(new Food(20, "Milked Coffee"));

        restaurants[4] = new Restaurant(8000);
        restaurants[4].addFoodItem(new Food(15, "Sandwich"));
        restaurants[4].addFoodItem(new Food(10, "Wrap"));
        restaurants[4].addFoodItem(new Food(20, "Toast"));

        restaurants[5] = new Restaurant(8000);
        restaurants[5].addFoodItem(new Food(15, "Pepperoni P"));
        restaurants[5].addFoodItem(new Food(10, "Vegi Pizza"));
        restaurants[5].addFoodItem(new Food(20, "Cheese Pizza"));

        restaurants[6] = new Restaurant(8000);
        restaurants[6].addFoodItem(new Food(15, "Cake"));
        restaurants[6].addFoodItem(new Food(10, "Cookie"));
        restaurants[6].addFoodItem(new Food(20, "Ekler"));

        restaurants[7] = new Restaurant(8000);
        restaurants[7].addFoodItem(new Food(15, "Sushi"));
        restaurants[7].addFoodItem(new Food(10, "Tempura Roll"));
        restaurants[7].addFoodItem(new Food(20, "Nigiri"));

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
                priceManagementMenu.addFoodToMenu(new Food(5, "Whatever")); // These are temporary examples. Grab foods
                                                                            // from restaurant instances if it has such
                                                                            // method
                priceManagementMenu.addFoodToMenu(new Food(5, "Burger"));
                priceManagementMenu.addFoodToMenu(new Food(5, "Sauce"));
                priceManagementMenu.setPriceMenuImg("price1.png"); // Must add respective texture for each Price
                                                                   // Mangement Menu
                game.setScreen(priceManagementMenu);
                System.out.println(priceManagementMenu.getSellPrice1());
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
                priceManagementMenu.addFoodToMenu(new Food(5, "Whatever")); // These are temporary examples. Grab foods
                                                                            // from restaurant instances if it has such
                                                                            // method
                priceManagementMenu.addFoodToMenu(new Food(5, "Burger"));
                priceManagementMenu.addFoodToMenu(new Food(5, "Sauce"));
                game.setScreen(priceManagementMenu);
                System.out.println("aaa");
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
                priceManagementMenu.addFoodToMenu(new Food(5, "Whatever")); // These are temporary examples. Grab foods
                                                                            // from restaurant instances if it has such
                                                                            // method
                priceManagementMenu.addFoodToMenu(new Food(5, "Burger"));
                priceManagementMenu.addFoodToMenu(new Food(5, "Sauce"));
                game.setScreen(priceManagementMenu);
                System.out.println("aaa");
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
                priceManagementMenu.addFoodToMenu(new Food(5, "Whatever")); // These are temporary examples. Grab foods
                                                                            // from restaurant instances if it has such
                                                                            // method
                priceManagementMenu.addFoodToMenu(new Food(5, "Burger"));
                priceManagementMenu.addFoodToMenu(new Food(5, "Sauce"));
                game.setScreen(priceManagementMenu);
                System.out.println("aaa");
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
                priceManagementMenu.addFoodToMenu(new Food(5, "Whatever")); // These are temporary examples. Grab foods
                                                                            // from restaurant instances if it has such
                                                                            // method
                priceManagementMenu.addFoodToMenu(new Food(5, "Burger"));
                priceManagementMenu.addFoodToMenu(new Food(5, "Sauce"));
                game.setScreen(priceManagementMenu);
                System.out.println("aaa");
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
                priceManagementMenu.addFoodToMenu(new Food(5, "Whatever")); // These are temporary examples. Grab foods
                                                                            // from restaurant instances if it has such
                                                                            // method
                priceManagementMenu.addFoodToMenu(new Food(5, "Burger"));
                priceManagementMenu.addFoodToMenu(new Food(5, "Sauce"));
                game.setScreen(priceManagementMenu);
                System.out.println("aaa");
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
                priceManagementMenu.addFoodToMenu(new Food(5, "Whatever")); // These are temporary examples. Grab foods
                                                                            // from restaurant instances if it has such
                                                                            // method
                priceManagementMenu.addFoodToMenu(new Food(5, "Burger"));
                priceManagementMenu.addFoodToMenu(new Food(5, "Sauce"));
                game.setScreen(priceManagementMenu);
                System.out.println("aaa");
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
                priceManagementMenu.addFoodToMenu(new Food(5, "Whatever")); // These are temporary examples. Grab foods
                                                                            // from restaurant instances if it has such
                                                                            // method
                priceManagementMenu.addFoodToMenu(new Food(5, "Burger"));
                priceManagementMenu.addFoodToMenu(new Food(5, "Sauce"));
                game.setScreen(priceManagementMenu);
                System.out.println("aaa");
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
        }

        if (n == 40) {
            customers.add(new Customer(0));
            n = 0;
        }
        n++;

        System.out.println(customer1.getX()); 
    }
    // #endregion

    public void setOpen(int restaurantNumber) {
        isOpen[restaurantNumber] = true;
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