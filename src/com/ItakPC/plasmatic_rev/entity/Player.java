package com.ItakPC.plasmatic_rev.entity;

import com.ItakPC.plasmatic_rev.TextureManager;

import java.awt.*;

public class Player {
    public Image texture;

    public int posX;
    public int posY;

    public Player(){
        texture = TextureManager.loadTexture("mob/player");
    }
}
