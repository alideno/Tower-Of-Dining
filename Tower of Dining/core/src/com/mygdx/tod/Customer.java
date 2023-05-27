package com.mygdx.tod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;

class Customer extends SpriteBatch
{
    
    Texture img;
    protected int numOfRestaurant;          // 1 = first restaurant, 2 = second restaurant and so on till 8
    public Customer (int numOfRestaurant)
    {
        super();
        this.numOfRestaurant = numOfRestaurant;
        System.out.println();
        
    }

    public void spriteRandomizer ()
    {
        java.util.Random random = new java.util.Random();
        int randomNum = random.nextInt();

        if (true)
        {
            this.img = new Texture ("Tower-Of-Dining\\Tower of Dining\\assets\\badlogic.jpg"); 
        }
    }

    public Texture getImage ()
    {
        return img;
    }
    
}