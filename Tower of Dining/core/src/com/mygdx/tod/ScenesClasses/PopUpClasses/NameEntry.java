package com.mygdx.tod.ScenesClasses.PopUpClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.tod.TowerOfDining;
import com.mygdx.tod.DBRelatedClasses.Leaderboard;
import com.mygdx.tod.ScenesClasses.TowerMenu;

import java.sql.Statement;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;

/**
 * This is a class representing the name entry popup at the end of the day
 * 
 * @author Deniz Şahin
 */
public class NameEntry extends PopUp {

    // database variables
    private final String DB_LOCATION = "jdbc:mysql://localhost:3306/towerofdining";
    private final String DB_USER = "root";
    private final String DB_PASS = "admin";

    // other variables
    Button nextButton;
    Stage buttonStage;
    TowerMenu towerMenu;
    TextField textField;

    public NameEntry(TowerOfDining game, TowerMenu towerMenu) {
        super(game);
        this.towerMenu = towerMenu;
        popupTexture = new Texture("NameEntry.png");

        // code fragment taken from MainMenu class
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;

        nextButton = new Button(buttonStyle);
        nextButton.setColor(1f, 1f, 1f, 0f);
        nextButton.setBounds(805, 370, 200, 80);

        nextButton.addListener(new nextButtonListener());

        nextButton.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Set the cursor to Hand when the mouse enters the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }

            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Restore the default cursor when the mouse exits the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("marker.png")));
        TextFieldStyle textStyle = new TextFieldStyle(new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt")),
                Color.WHITE, upDrawable, null, null);
        textField = new TextField("name", textStyle);
        textField.setScale(5);

        textField.setPosition(850, 500);

        buttonStage = new Stage();

        buttonStage.addActor(nextButton);
        buttonStage.addActor(textField);

        Gdx.input.setInputProcessor(buttonStage);

        render(5);
    }

    private class nextButtonListener extends ClickListener {
        public void clicked(InputEvent event, float x, float y) {

            String name = textField.getText();
            if (name.length() > 20) {
                name = name.substring(0, 19);
            }

            // creating database connection
            try {
                System.out.println("Database connecting...");
                Connection conn = DriverManager.getConnection(DB_LOCATION, DB_USER, DB_PASS);
                System.out.println("Database connection complete.");
                Statement statement = conn.createStatement();

                String updateString = "INSERT INTO leaderboard (name, score) VALUES ('" + name + "', "
                        + towerMenu.getMoney() + ")";
                System.out.println(updateString);
                statement.executeUpdate(
                        updateString);

            } catch (SQLException e) {
                System.out.println("SQL Exception");
                game.closeScreen();
                e.printStackTrace();
            }
            game.closeScreen();
            game.newScreen(new Leaderboard(game));
        }
    }

    @Override
    public void render(float delta) {

        game.batch.begin();
        game.batch.draw(popupTexture, 0, 0);

        BitmapFont font = new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt"));
        font.getData().setScale(2f);
        font.draw(game.batch, "" + towerMenu.getMoney(), 900, 680);

        game.batch.end();

        buttonStage.act(delta);
        buttonStage.draw();
    }

    @Override
    public void dispose() {
        buttonStage.dispose();
    }
}
