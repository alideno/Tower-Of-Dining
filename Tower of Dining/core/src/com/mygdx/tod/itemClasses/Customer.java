package com.mygdx.tod.itemClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Customer {
    static Texture BrownManMini = new Texture("BrownManMini.png");
    static Texture NavyManMini = new Texture("NavyManMini.png");
    static Texture PurpleManMini = new Texture("PurpleManMini.png");
    static Texture OrangeManMini = new Texture("OrangeManMini.png");
    static Texture GreenManMini = new Texture("GreenManMini.png");
    Texture img;
    int x = -100;
    int y = 255;
    protected int numOfRestaurant; // 1 = first restaurant, 2 = second restaurant and so on till 8
    TextureRegion[] frames = new TextureRegion[2];
    Animation<TextureRegion> animation;
    float elapsedTime;

    public Customer(int numOfRestaurant) {
        this.numOfRestaurant = numOfRestaurant;

        spriteRandomizer();

        frames[1] = new TextureRegion(img, 130, 0, 149, 120);
        frames[0] = new TextureRegion(img, 0, 0, 120, 120);

        animation = new Animation<TextureRegion>(1 / 5f, frames);

    }

    public void spriteRandomizer() {
        java.util.Random random = new java.util.Random();
        int randomNum = random.nextInt(5);

        if (randomNum==0) 
        {
            this.img = BrownManMini;
        }
        else if (randomNum==1)
        {
            this.img = GreenManMini;
        }
        else if (randomNum==2)
        {
            this.img = NavyManMini;
        }
        else if (randomNum==3)
        {
            this.img = PurpleManMini;
        }
        else if (randomNum==4)
        {
            this.img = OrangeManMini;
        }
    }

    public Animation geAnimation() {
        return animation;
    }

    public void showImg ()
    {
        frames[1] = new TextureRegion(img, 130, 0, 149, 120);
        frames[0] = new TextureRegion(img, 0, 0, 120, 120);
    }

    public void hideImg ()
    {
        frames[1] = new TextureRegion(img, 0, 0, 1, 120);
        frames[0] = new TextureRegion(img, 0, 0, 1, 120);
    }

    public void update() {
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (numOfRestaurant==1)
        {
            handleNum1();
        }
        else if (numOfRestaurant == 2)
        {
            handleNum2();
        }
        else if (numOfRestaurant == 3)
        {
            handleNum3();
        }
        else if (numOfRestaurant == 4)
        {
            handleNum4();
        }
        else if (numOfRestaurant == 5)
        {
            handleNum5();
        }
        else if (numOfRestaurant == 6)
        {
            handleNum6();
        }
        else if (numOfRestaurant == 7)
        {
            handleNum7();
        }
        else if (numOfRestaurant == 8)
        {
            handleNum8();
        }
    }

    public void handleNum1 ()
    {
        
        if ( x<440)
        {
            x += 2;
        }
        else if (x >= 440 && x < 910)
        {
            hideImg();
            x+=2;
        }
        
        else if (910<=x)
        {
            showImg();
            x += 2;
        }
    }

    public void handleNum2 ()
    {
        
        if ( x<440)
        {
            x += 2;
        }
        else if (x >= 440 && x < 610)
        {
            hideImg();
            x+=2;
        }
        
        else if (610<=x && x <=760)
        {
            showImg();
            x += 2;
        }

        else if (x >= 760 && x <= 910)
        {
            hideImg();
            x+=2;
        }
        else if (910<=x)
        {
            showImg();
            x += 2;
        }
    }

    public void handleNum3 ()
    {
        
        if ( x<220)
        {
            x += 2;
        }
        else if (x>=220 && x<=475)
        {
            x+=2;
            y=410;
        }
        
        else if (x >= 475 && x < 910)
        {
            hideImg();
            x+=2;
        }
        
        else if (910<=x)
        {
            y=255;
            showImg();
            x += 2;
        }
    }

    public void handleNum4 ()
    {
        
        if ( x<220)
        {
            x += 2;
        }
        else if (x>=220 && x<=475)
        {
            x+=2;
            y=410;
        }
        
        else if (475<=x && x <=610)
        {
            hideImg();
            x += 2;
        }

        else if (x >= 610 && x <= 760)
        {
            showImg();
            x+=2;
        }
        else if (x >= 760 && x <= 910)
        {
            hideImg();
            x+=2;
        }
        else if (910<=x)
        {
            y=255;
            showImg();
            x += 2;
        }
    }

    public void handleNum5 ()
    {
        
        if ( x<220)
        {
            x += 2;
        }
        else if (x>=220 && x<=475)
        {
            x+=2;
            y=575;
        }
        
        else if (x >= 475 && x < 910)
        {
            hideImg();
            x+=2;
        }
        
        else if (910<=x)
        {
            y=255;
            showImg();
            x += 2;
        }
    }

    public void handleNum6 ()
    {
        
        if ( x<220)
        {
            x += 2;
        }
        else if (x>=220 && x<=475)
        {
            x+=2;
            y=575;
        }
        
        else if (475<=x && x <=610)
        {
            hideImg();
            x += 2;
        }

        else if (x >= 610 && x <= 760)
        {
            showImg();
            x+=2;
        }
        else if (x >= 760 && x <= 910)
        {
            hideImg();
            x+=2;
        }
        else if (910<=x)
        {
            y=255;
            showImg();
            x += 2;
        }
    }

    public void handleNum7 ()
    {
        
        if ( x<220)
        {
            x += 2;
        }
        else if (x>=220 && x<=475)
        {
            x+=2;
            y=733;
        }
        
        else if (x >= 475 && x < 910)
        {
            hideImg();
            x+=2;
        }
        
        else if (910<=x)
        {
            y=255;
            showImg();
            x += 2;
        }
    }

    public void handleNum8 ()
    {
        
        if ( x<220)
        {
            x += 2;
        }
        else if (x>=220 && x<=475)
        {
            x+=2;
            y=733;
        }
        
        else if (475<=x && x <=610)
        {
            hideImg();
            x += 2;
        }

        else if (x >= 610 && x <= 760)
        {
            showImg();
            x+=2;
        }
        else if (x >= 760 && x <= 910)
        {
            hideImg();
            x+=2;
        }
        else if (910<=x)
        {
            y=255;
            showImg();
            x += 2;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }


}
