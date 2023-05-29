package com.mygdx.tod.ScenesClasses;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import javax.print.attribute.standard.Sides;
import javax.swing.JSlider;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    TowerMenu towerMenu;
    Texture priceMenuImg;
    Texture background = new Texture("priceBackground.png");
    private TowerOfDining game;
    Window priceMenu;
    ArrayList<Food> menu;
    double changeStep = 0.1;
    double priceModiefier = 3;

    double sellingPrice1 = 0.00;
    double sellingPrice2 = 0.00;       // The price of the food that will be sold to the customer, controlled by the player
    double sellingPrice3 = 0.00;

    Texture marker1;                    // Each slider has a marker with int x that controls their movement along the x axis
    int x1 = 1161;
    Button sliderZone1;                 // Is an area where the Player will be able to use the slider to choose a price
    Texture marker2;
    int x2 = 1161;
    Button sliderZone2;
    Texture marker3;
    int x3 = 1161;
    Button sliderZone3;

    Button minusButton1;        // Decreases the sellingPrice1 of the respective food on the menu
    Button plusButton1;         // Increases the sellingPrice1 of the respective food on the menu
    Button minusButton2;
    Button plusButton2;
    Button minusButton3;
    Button plusButton3;
    private Button exitButton;  // Exits the game
    
    

    private Stage stage;

    BitmapFont font = new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt"));
    BitmapFont nameFont = new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt"));
    String text1;       // These are the Strings used for storing the values of Necessary texts for identifying foods and their names
    String text2;
    String text3;


    // Price management menus should are to be created beforehand and saved in a variable for a later use, since
    // the current amount of restaurants is FINAL. This class is  intended to be called as x1, x2, x3 and etc...
    // The .setScreen(new PriceManagerMenu (TowerOfDining tod) ) is NOT the correct use of this class

    public PriceManagementMenu (TowerOfDining tod, TowerMenu towerMenu)
    {
        this.towerMenu = towerMenu;
        this.game = tod;
        menu = new ArrayList<Food>();
        priceMenuImg = new Texture("price1.png");
        marker1 = new Texture("marker.png");
        marker2 = new Texture("marker.png");
        marker3 = new Texture("marker.png");
        allButtonsClick();  // Makes all buttons pressable and gives them function
        font.getData().setScale(1.8f);      // Answers for the size of the Texts
        nameFont.getData().setScale(2.3f);
        nameFont.setColor(Color.BLACK);


        
    }

    public void activateTheStage ()
    {
        stage = new Stage();
        stage.addActor(plusButton1);
        stage.addActor(minusButton1);
        stage.addActor(plusButton2);
        stage.addActor(minusButton2);
        stage.addActor(plusButton3);
        stage.addActor(minusButton3);
        stage.addActor(exitButton);
        stage.addActor(sliderZone1);
        stage.addActor(sliderZone2);
        stage.addActor(sliderZone3);
        render(5);
    }

    public void addFoodToMenu (Food food)
    {
        if (menu.size()<4)
        menu.add(food);
        
        if (menu.size()==3)
        {
            sellingPrice1 = menu.get(0).getBasePrice() + menu.get(0).getBasePrice()*priceModiefier/2.0;
            sellingPrice2 = menu.get(1).getBasePrice() + menu.get(1).getBasePrice()*priceModiefier/2.0;
            sellingPrice3 = menu.get(2).getBasePrice() + menu.get(2).getBasePrice()*priceModiefier/2.0;
            activateTheStage();
        }

        
    }

    public void setPriceMenuImg (String filename)
    {
        // For setting different layouts for restaurants
        this.priceMenuImg = new Texture(filename);
    }

    public void render(float delta) 
    {
        
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.draw(priceMenuImg, 0, 0);


        // Answers for the display of the sliders' marks and where they are
        game.batch.draw(marker1,x1, 634);
        game.batch.draw(marker2,x2, 480);
        game.batch.draw(marker3,x3, 324);
        

        // Answers for display of prices and their change is updated here
        nameFont.draw(game.batch, menu.get(0).getName(), 440, 684);
        nameFont.draw(game.batch, menu.get(1).getName(), 440, 525);
        nameFont.draw(game.batch, menu.get(2).getName(), 440, 370);

        font.draw(game.batch, Double.toString( Math.round(sellingPrice1*100.0)/100.0) , 1520,677);
        font.draw(game.batch, Double.toString(Math.round(sellingPrice2*100.0)/100.0) , 1520,520);
        font.draw(game.batch, Double.toString(Math.round(sellingPrice3*100.0)/100.0) , 1520,364);
        
        
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
        int plusXplacement = 1459;
        int minusXplacement = 1386;

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

        sliderZone1 = new Button(buttonStyle);
        sliderZone1.setColor(Color.RED);
        sliderZone1.setBounds(980, 630,363, 40);

        sliderZone2 = new Button(buttonStyle);
        sliderZone2.setColor(Color.RED);
        sliderZone2.setBounds(980, 477,363, 40);

        sliderZone3 = new Button(buttonStyle);
        sliderZone3.setColor(Color.RED);
        sliderZone3.setBounds(980, 320,363, 40);

        exitButton = new Button(buttonStyle);
        exitButton.setColor(Color.RED);
        exitButton.setBounds(1707, 802, 57,58); 





        minusButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                decreaseSellingPrice1(); 
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
                increaseSellingPrice1();
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
                decreaseSellingPrice2();
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
                increaseSellingPrice2();
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
                decreaseSellingPrice3();
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
                increaseSellingPrice3();
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

        sliderZone1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                x1 = Gdx.input.getX();
                sellingPrice1 = menu.get(0).getBasePrice() + ( (double)(x1-990.0) / (double)350.0 ) * menu.get(0).getBasePrice() * priceModiefier ; 
            if (x1<990)
            {
                x1=990;
            }
            else if (x1>1340)
            {
                x1=1340;
            }
            int basePrice = menu.get(0).getBasePrice();
            if (sellingPrice1 <= basePrice)
            {
                    x1 = 990;
                    sellingPrice1 = basePrice;
            }
            if (sellingPrice1 >= basePrice*(1+priceModiefier))
            {
                    x1 = 1340;
                    sellingPrice1 = basePrice*(1+priceModiefier);
            }
            
            
            }
        });
        sliderZone1.addListener(new InputListener() {
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

        sliderZone2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                x2 = Gdx.input.getX();
                sellingPrice2 = menu.get(1).getBasePrice() + ( (double)(x2-990.0) / (double)350.0 ) * menu.get(1).getBasePrice() * priceModiefier;
                if (x2<990)
                {
                    x2=990;
                }
                else if (x2>1340)
                {
                    x2=1340;
                }
                int basePrice = menu.get(1).getBasePrice();
                if (sellingPrice2 <= basePrice)
            {
                    x2 = 990;
                    sellingPrice2 = basePrice;
            }
            if (sellingPrice2 >= basePrice*(1+priceModiefier))
            {
                    x2 = 1340;
                    sellingPrice2 = basePrice*(1+priceModiefier);
            }         
            }

        });
        sliderZone2.addListener(new InputListener() {
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

        sliderZone3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                x3 = Gdx.input.getX();
                sellingPrice3 = menu.get(2).getBasePrice() + ( (double)(x3-990.0) / (double)350.0 ) * menu.get(2).getBasePrice() * priceModiefier;
                if (x3<990)
                {
                    x3=990;
                }
                else if (x3>1340)
                {
                    x3=1340;
                }
                int basePrice = menu.get(2).getBasePrice();
                if (sellingPrice3 <= basePrice)
                {
                        x3 = 990;
                        sellingPrice3 = basePrice;
                }
                if (sellingPrice3 >= basePrice*(1+priceModiefier))
                {
                        x3 = 1340;
                        sellingPrice3 = basePrice*(1+priceModiefier);
                } 
            }

        });
        sliderZone3.addListener(new InputListener() {
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
                game.setScreen(towerMenu);
                dispose();
                
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

    public void decreaseSellingPrice1 ()
    {

        // This method is used not only for decreasing the sellingPrice, but also for the the new coordinates of the marker of the slider
        int basePrice = menu.get(0).getBasePrice();
        sellingPrice1-=changeStep;

            
        x1 =  990 + (int) (348 * (sellingPrice1-basePrice)/(basePrice*(priceModiefier)) ); 
        System.out.println( (sellingPrice1-basePrice)/(basePrice*(priceModiefier)));
        System.out.println((30 * (sellingPrice1-basePrice)/(basePrice*(priceModiefier)) ));

        if (x1<990)
        {
            x1=990;
        }
        else if (x1>1340)
        {
            x1=1340;
        }
            
        if (sellingPrice1 <= basePrice)
        {
            x1 = 990;
            sellingPrice1 = basePrice;
        }
        

    }

    public void increaseSellingPrice1 ()
    {

        // Same applies here too except its function is to increase the sellingPrice
        
        
        int basePrice = menu.get(0).getBasePrice();
        

        sellingPrice1+=changeStep;

            
        x1 =  990 + (int) (350 * (sellingPrice1-basePrice)/(basePrice*priceModiefier) );
            
        if (x1<990)
        {
            x1=990;
        }
        else if (x1>1340)
        {
            x1=1340;
        }
        
        if (sellingPrice1 >= basePrice*(1+priceModiefier))
        {
            x1 = 1340;
            sellingPrice1 = basePrice*(1+priceModiefier);
        }

    }

    public void decreaseSellingPrice2 ()
    {
        
        int basePrice = menu.get(1).getBasePrice();
        

        sellingPrice2-=changeStep;

            
        x2 =  990 + (int) (350 * (sellingPrice2-basePrice)/(basePrice*(priceModiefier)) ); 

        if (x1<990)
        {
            x1=990;
        }
        else if (x1>1340)
        {
            x1=1340;
        }
            
        if (sellingPrice2 <= basePrice)
        {
            x2 = 990;
            sellingPrice2 = basePrice;
        }

    }

    public void increaseSellingPrice2 ()
    {
        
        
        int basePrice = menu.get(1).getBasePrice();
        

        sellingPrice2+=changeStep;

            
        x2 =  990 + (int) (350 * (sellingPrice2-basePrice)/(basePrice*priceModiefier) );
            
        if (x1<990)
        {
            x1=990;
        }
        else if (x1>1340)
        {
            x1=1340;
        }
            
        if (sellingPrice2 >= basePrice*(1+priceModiefier))
        {
            x2 = 1340;
            sellingPrice2 = basePrice*(1+priceModiefier);
        }

    }

    public void decreaseSellingPrice3 ()
    {
        
        int basePrice = menu.get(2).getBasePrice();
        

        sellingPrice3-=changeStep;

            
        x3 =  990 + (int) (350 * (sellingPrice3-basePrice)/(basePrice*(priceModiefier)) ); 

        if (x3<990)
        {
            x3=990;
        }
        else if (x3>1340)
        {
            x3=1340;
        }
            
        if (sellingPrice3 <= basePrice)
        {
            x3 = 990;
            sellingPrice3 = basePrice;
        } 

    }

    public void increaseSellingPrice3 ()
    {
        
        int basePrice = menu.get(2).getBasePrice();
        

        sellingPrice3+=changeStep;

            
        x3 =  990 + (int) (350 * (sellingPrice3-basePrice)/(basePrice*priceModiefier) );
            
        if (x3<990)
        {
            x3=990;
        }
        else if (x3>1340)
        {
            x3=1340;
        }
            
        if (sellingPrice3 >= basePrice*(1+priceModiefier))
        {
            x3 = 1340;
            sellingPrice3 = basePrice*(1+priceModiefier);
        }

    }


    public double getSellPrice1 ()
    {
        return sellingPrice1;
    }

    public double getSellPrice2 ()
    {
        return sellingPrice2;
    }

    public double getSellPrice3 ()
    {
        return sellingPrice3;
    }

    public void show() {
        Gdx.input.setInputProcessor(stage); 
    }


    

}
