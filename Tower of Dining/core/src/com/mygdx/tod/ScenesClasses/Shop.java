package com.mygdx.tod.ScenesClasses;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.tod.TowerOfDining;

public class Shop extends ScreenAdapter{
    Texture menuImg;
    private TowerOfDining game;
    private Button nextButton, previousButton, orderButton, backButton;
    private int currentShop;
    private int[] foodCount;
    


    public Shop(TowerOfDining game, int currentShop){//for towermenu class
        this.game = game;
        this.currentShop = currentShop;
        String temp = "shop menu" + currentShop + ".png";
        menuImg =  new Texture(temp);
        foodCount = new int[24];
        for (int i = 0; i < foodCount.length; i++) {
            foodCount[i] = 0;
        }
        nextButton = new Button();
        nextButton.setColor(1f, 1f, 1f, 0f);
        nextButton.setBounds(1500, 196, 110,390);

        previousButton = new Button();
        previousButton.setColor(1f, 1f, 1f, 0f);
        previousButton.setBounds(1500, 305, 110,390);

        backButton = new Button();
        backButton.setColor(1f, 1f, 1f, 0f);
        backButton.setBounds(1152, 58, 100,750);




    }
    public Shop(TowerOfDining game, int currentShop, int[] foodCount){ //ONLY USE IN THIS CLASS
        Shop shop = new Shop(game, currentShop);
        this.foodCount = foodCount;
    }



}
