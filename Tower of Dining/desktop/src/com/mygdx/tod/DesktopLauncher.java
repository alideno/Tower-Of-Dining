package com.mygdx.tod;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();

		if(displayMode.width == 1920)
		{
			
			config.setFullscreenMode(displayMode);
		}
		else
		{
			config.setWindowedMode(1920, 1080);
		}
		
		config.setTitle("Tower Of Dining");
		new Lwjgl3Application(new TowerOfDining(), config);
	}
}
