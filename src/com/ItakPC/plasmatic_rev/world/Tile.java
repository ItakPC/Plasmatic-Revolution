package com.ItakPC.plasmatic_rev.world;

import com.ItakPC.plasmatic_rev.Material;
import com.ItakPC.plasmatic_rev.TextureManager;

import static com.ItakPC.plasmatic_rev.reference.Reference.*;
import static com.ItakPC.plasmatic_rev.Game.*;
import java.awt.*;

public class Tile {

    public Material material;
    public Image texture;

    public Tile(Material material) {
        this.material = material;
        this.texture = TextureManager.loadTexture(this.material.getRandomResourceId());
    }

    public void render(Graphics g) {
        this.render(g, 0, 0);
    }

    public void render(Graphics g, int x,int y) {
        this.render(g, x, y, ceil(tileSize * pixelSize * pixelScaleWidth), ceil(tileSize * pixelSize * pixelScaleHeight));
    }

    public void render(Graphics g, int x, int y, int width, int height){
        g.drawImage(texture, x, y, width, height, null);
    }

}
