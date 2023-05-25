package com.mygdx.tod.ScenesClasses;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tod.TowerOfDining;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * This class represents the screen when the game starts
 * 
 * @author Eren Berk Eraslan
 * 
 * @param game
 */

public class MainMenu extends ScreenAdapter {
    Texture menuImg;
    private TowerOfDining game;
    private Button playButton;
    private Button settingsButton;
    private Button lbButton;
    private Button exitButton;
    private Stage stage;

    public MainMenu(TowerOfDining tod){
        game = tod;
        menuImg =  new Texture("title screen.png");

        //Creating the "Play" button textures are not necessary since the button will be invisible
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;

        playButton = new Button(buttonStyle);
        playButton.setColor(1f, 1f, 1f, 0f);
        playButton.setBounds(300, 190, 600,250);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handlePlayButtonClick();
            }

        });
        playButton.addListener(new InputListener() {
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

        //Creating the "Settings" button textures are not necessary since the button will be invisible
        settingsButton = new Button(buttonStyle);
        settingsButton.setColor(1f, 1f, 1f, 0f);
        settingsButton.setBounds(1025, 190, 600,250);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleSettingsButtonClick();
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

        //Creating the "LeaderBoard" button textures are not necessary since the button will be invisible
        lbButton = new Button(buttonStyle);
        lbButton.setColor(1f, 1f, 1f, 0f);
        lbButton.setBounds(1545, 490, 350,150);

        lbButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleLbButtonClick();
            }

        });
        lbButton.addListener(new InputListener() {
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

        //Creating the exit button
        TextureRegionDrawable upDrawableExit = new TextureRegionDrawable(new TextureRegion(new Texture("exitButton.png")));//Change the image
        ButtonStyle exitButtonStyle = new ButtonStyle();
        exitButtonStyle.up = upDrawableExit;
        exitButton = new Button(exitButtonStyle);
        exitButton.setBounds(1750, 970, 100,100);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleExitButtonClick();
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

    
        stage = new Stage();
        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(lbButton);
        stage.addActor(exitButton);
        render(5);
    }
    
    protected void handleExitButtonClick() {
        System.exit(0);
    }

    protected void handleLbButtonClick() {//TODO
        System.out.println("lb");
    }

    protected void handlePlayButtonClick(){
        game.setScreen(new TowerMenu(game));
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        dispose();
    }
    
    protected void handleSettingsButtonClick() {//TODO
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); 
    }

   
    @Override
    public void render(float delta) {
      ScreenUtils.clear(1, 0, 0, 1);
      game.batch.begin();
      game.batch.draw(menuImg, 0, 0);
      game.batch.end();
      
      stage.act(delta); 
      stage.draw(); 
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}