package com.ItakPC.plasmatic_rev;

import static com.ItakPC.plasmatic_rev.reference.Reference.*;
import java.awt.*;

public class Tile {

    public Material material;
    public Image texture;

    public Tile(Material material) {
        this.material = material;
        this.texture = TextureManager.loadTexture(this.material.getRandomResourceId());
    }

}
