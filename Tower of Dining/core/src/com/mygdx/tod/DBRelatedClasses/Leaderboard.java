package com.mygdx.tod.DBRelatedClasses;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.tod.TowerOfDining;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Cursor;

/**
 * This is a class managing the leaderboard screen of the game.
 * 
 * @author Deniz Şahin
 */
public class Leaderboard extends ScreenAdapter {
    
    //database variables
    private final String DB_LOCATION = "jdbc:mysql://localhost:3306/towerofdining";
    private final String DB_USER = "root";
    private final String DB_PASS = "admin";

    //other variables
    private TowerOfDining game;
    private Texture leaderboardBackground;
    private String[] names;
    private int[] scores;
    private Stage buttonStage;
    private Button exitButton;

    public Leaderboard(TowerOfDining game)
    {
        this.game = game;
        this.leaderboardBackground = new Texture(Gdx.files.internal("Leaderboard.png"));

        //creating database connection
        try{
            System.out.println("Database connecting...");
            Connection conn = DriverManager.getConnection(DB_LOCATION, DB_USER, DB_PASS);
            System.out.println("Database connection complete.");
            Statement statement = conn.createStatement();
            
            ResultSet result = statement.executeQuery(
                "SELECT name, score FROM leaderboard ORDER BY score DESC LIMIT 9"
            );

            names = new String[10];
            scores = new int[10];

            int i = 0;
            while(result.next())
            {
                names[i] = result.getString(1);
                scores[i] = result.getInt(2);
                i++;
            }

        }catch (SQLException e){
            System.out.println("SQL Exception");
            game.closeScreen();
            e.printStackTrace();
        }

        //code fragment taken from MainMenu class
        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("dayend.png")));
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = upDrawable;
        buttonStyle.down = downDrawable;

        exitButton = new Button(buttonStyle);
        exitButton.setColor(1f, 1f, 1f, 0f);
        exitButton.setBounds(1345, 910, 60,60);
        
        exitButton.addListener(new exitButtonListener());

        exitButton.addListener(new InputListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Set the cursor to Hand when the mouse enters the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }
        
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Restore the default cursor when the mouse exits the button
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        buttonStage = new Stage();
        buttonStage.addActor(exitButton);

        Gdx.input.setInputProcessor(buttonStage);

        render(20);
    }

    public void render(float delta)
    {
        game.batch.begin();
        game.batch.draw(leaderboardBackground, 0, 0);

        BitmapFont font = new BitmapFont(Gdx.files.internal("minecraftFontWhite.fnt"));
        font.getData().setScale(1.7f);

        for(int i = 0; i < 9; i++)
        {
            font.draw(game.batch, i + 1 + ". " + names[i], 550, 750 - 70 * i);
            font.draw(game.batch, "" + scores[i], 1220, 750 - 70 * i);
        }
        
        game.batch.end();

        buttonStage.act(delta);
        buttonStage.draw();

    }


    private class exitButtonListener extends ClickListener{
        public void clicked(InputEvent event, float x, float y) {
            game.closeScreen();
        }
    }
}
