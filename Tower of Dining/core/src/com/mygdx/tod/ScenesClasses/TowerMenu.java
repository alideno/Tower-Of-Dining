package com.mygdx.tod.ScenesClasses;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.tod.TowerOfDining;
import com.badlogic.gdx.utils.ScreenUtils;


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
}
