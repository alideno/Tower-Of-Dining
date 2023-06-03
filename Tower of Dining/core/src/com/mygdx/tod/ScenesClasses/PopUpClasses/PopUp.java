package com.mygdx.tod.ScenesClasses.PopUpClasses;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.tod.TowerOfDining;
/**
 * This class is an abstract class representing a popup and extended by the three popup classes,
 * 
 * @author Deniz Şahin
 */
public abstract class PopUp extends ScreenAdapter{
    
    //defining the common variables
    protected TowerOfDining game;
    protected Texture popupTexture;

    public PopUp(TowerOfDining game)
    {
        this.game = game;
    }

}
