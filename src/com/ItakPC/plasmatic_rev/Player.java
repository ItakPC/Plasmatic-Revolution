package com.ItakPC.plasmatic_rev;

import java.awt.*;

public class Player {
    public Image texture;

    public int posX;
    public int posY;

    public Player(){
        texture = TextureManager.loadTexture("mob/player");
    }
}
