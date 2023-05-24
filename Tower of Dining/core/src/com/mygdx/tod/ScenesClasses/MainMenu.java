package com.mygdx.tod.ScenesClasses;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tod.TowerOfDining;

public class MainMenu extends ScreenAdapter {
    Texture menuImg;
    TowerOfDining game;

    public MainMenu(TowerOfDining tod){
        game = tod;
        menuImg =  new Texture("title screen.png");
        
    }

   
    @Override
    public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		game.batch.begin();
		game.batch.draw(menuImg, 0, 0);
		game.batch.end();
    }
}