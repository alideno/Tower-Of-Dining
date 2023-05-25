package com.mygdx.tod;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.util.Stack;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.tod.ScenesClasses.MainMenu;

public class TowerOfDining extends Game {
	public SpriteBatch batch;
	public Stack<Screen> screens = new Stack<>();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		screens.push(new MainMenu(this));
		setScreen(screens.get(0));
		
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
