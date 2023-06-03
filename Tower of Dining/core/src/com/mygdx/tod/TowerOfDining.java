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
	public void create() {
		batch = new SpriteBatch();
		screens.push(new MainMenu(this));
		setScreen(screens.get(0));
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	/**
	 * This method is used to change the screen to a new screen.
	 * Use whenever a new screen is opened.
	 * 
	 * @param newScreen the new screen that will be used
	 */
	public void newScreen(Screen newScreen) {
		this.screens.push(newScreen);
		this.setScreen(this.screens.get(screens.size() - 1));
	}

	/**
	 * This method is used to remove the currently active screen.
	 * Use when the close button is pressed.
	 */
	public void closeScreen() {
		if (!screens.empty()) {
			this.screens.pop().dispose();
			if (!screens.empty()) {
				this.setScreen(this.screens.get(0));
			}
		}
	}
}
