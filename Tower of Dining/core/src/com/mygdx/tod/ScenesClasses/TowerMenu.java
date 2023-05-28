package com.mygdx.tod.ScenesClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.tod.TowerOfDining;
import com.mygdx.tod.itemClasses.Food;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Cursor;


/**
 * This class represents the screen when the game starts
 * 
 * @author Eren Berk Eraslan
 * 
 * @param game
 */

public class TowerMenu extends ScreenAdapter{
    TowerOfDining game;
    Texture towerMenuImg;
    //private Stage stage;

    public TowerMenu(TowerOfDining game){
        this.game = game;
        towerMenuImg =  new Texture("tower.png");

    }

    @Override
    public void render(float delta) {
      ScreenUtils.clear(1, 0, 0, 1);
      game.batch.begin();
      game.batch.draw(towerMenuImg, 0, 0);
      game.batch.end();
    }

    protected void handlePriceManagerExample(){

      // Example of how to create the restaurant instances
      PriceManagementMenu pmm = new PriceManagementMenu(game);
      pmm.addFoodToMenu(new Food(8));
      pmm.addFoodToMenu(new Food(5));      // Food class name in the futute plss   
      pmm.addFoodToMenu(new Food(17));
      pmm.generateSliders();
      game.setScreen(pmm);
      Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
      dispose();
  }
}
