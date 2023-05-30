package com.mygdx.tod.itemClasses;
import java.util.ArrayList;

import org.w3c.dom.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;

public class Customer 
{
    Texture img;
    int x = 0;
    int y = 0;
    protected int numOfRestaurant;          // 1 = first restaurant, 2 = second restaurant and so on till 8
    TextureRegion[] frames = new TextureRegion[2];
    Animation<TextureRegion> animation;
    float elapsedTime;

    public Customer (int numOfRestaurant)
    {
        this.numOfRestaurant = numOfRestaurant;
        

        spriteRandomizer();

        frames[1] = new TextureRegion(img, 130, 0, 149, 120);
        frames[0] = new TextureRegion(img, 0, 0, 120, 120);

        animation = new Animation<TextureRegion>(1/5f, frames);
        
    }

    public void spriteRandomizer ()
    {
        java.util.Random random = new java.util.Random();
        int randomNum = random.nextInt();

        if (true)
        {
            this.img = new Texture ("NavyManMini.png"); 
        }
    }

    public Animation geAnimation ()
    {
        return animation;
    }

    public void update ()
    {
        elapsedTime+=Gdx.graphics.getDeltaTime();
        x+=2;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public float getElapsedTime ()
    {
        return elapsedTime;
    }
    
}
