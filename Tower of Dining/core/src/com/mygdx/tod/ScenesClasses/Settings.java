package com.mygdx.tod.ScenesClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.tod.TowerOfDining;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Settings extends ScreenAdapter {
    protected static final String Cursor = null;
    private Stage stage;
    Texture settingsImg;
    TowerOfDining game;
    TowerMenu towerMenu;
    private float volume = 1f;
    MainMenu main;

    public Settings(TowerOfDining game, TowerMenu towerMenu, int one, MainMenu main) {
        this.towerMenu = towerMenu;
        this.game = game;
        this.main = main;
        stage = new Stage();

        if (one == 0) {
            settingsImg = new Texture("Settings.png");
            TextureRegionDrawable upDrawable = new TextureRegionDrawable(
                    new TextureRegion(new Texture("salesign.png")));
            TextureRegionDrawable downDrawable = new TextureRegionDrawable(
                    new TextureRegion(new Texture("salesign.png")));
            ButtonStyle buttonStyle = new ButtonStyle();
            buttonStyle.up = upDrawable;
            buttonStyle.down = downDrawable;
            Button plus = new Button(buttonStyle);

            plus.setBounds(990, 550, 180, 180);
            plus.setColor(1, 1, 1, 0);
            plus.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    handlePlusClick();
                }
            });

            Button minus = new Button(buttonStyle);
            minus.setBounds(1430, 550, 200, 180);
            minus.setColor(1, 1, 1, 0);
            minus.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    handleMinusClick();
                }
            });

            Button exit = new Button(buttonStyle);
            exit.setBounds(1650, 850, 220, 220);
            exit.setColor(1, 1, 1, 0);
            exit.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    handleExitClick();
                }
            });
            stage.addActor(exit);
            stage.addActor(plus);
            stage.addActor(minus);
        }

        if (one == 1) {
            settingsImg = new Texture("in game settings.png");
            TextureRegionDrawable upDrawable = new TextureRegionDrawable(
                    new TextureRegion(new Texture("salesign.png")));
            TextureRegionDrawable downDrawable = new TextureRegionDrawable(
                    new TextureRegion(new Texture("salesign.png")));
            ButtonStyle buttonStyle = new ButtonStyle();
            buttonStyle.up = upDrawable;
            buttonStyle.down = downDrawable;
            Button plus = new Button(buttonStyle);

            plus.setBounds(990, 550, 180, 180);
            plus.setColor(1, 1, 1, 0);
            plus.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    handlePlusClick();
                }
            });

            Button minus = new Button(buttonStyle);
            minus.setBounds(1430, 550, 200, 180);
            minus.setColor(1, 1, 1, 0);
            minus.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    handleMinusClick();
                }
            });

            Button exit = new Button(buttonStyle);
            exit.setBounds(1650, 850, 220, 220);
            exit.setColor(1, 1, 1, 0);
            exit.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    handleExitClick();
                }
            });

            Button returnToTitle = new Button(buttonStyle);
            returnToTitle.setBounds(500, 150, 1000, 180);
            returnToTitle.setColor(1, 1, 1, 0);
            returnToTitle.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    handleReturnToTitleClick();
                }
            });
            stage.addActor(returnToTitle);
            stage.addActor(exit);
            stage.addActor(plus);
            stage.addActor(minus);
        }
    }

    protected void handleReturnToTitleClick() {
        game.newScreen(new MainMenu(game));
        main.closeMusic();
    }

    protected void handleExitClick() {
        game.closeScreen();
    }

    protected void handleMinusClick() {
        if (volume > 0f) {
            volume = volume - 0.1f;
        }
        main.setVol(volume);
    }

    protected void handlePlusClick() {
        if (volume <= 0.9f) {
            volume = volume + 0.1f;
        }
        main.setVol(volume);
    }

    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(settingsImg, 0, 0);

        game.batch.end();
        stage.act(delta);
        stage.draw();
    }
}
