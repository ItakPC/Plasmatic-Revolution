package com.ItakPC.plasmatic_rev;

import static com.ItakPC.plasmatic_rev.reference.Reference.*;

public class Chunk {

    Tile[][] tiles;
    int chunkX;
    int chunkY;

    public Chunk(int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
    }

    public void populate() {
        tiles = new Tile[tileAmountX][tileAmountY];

        for(int x = 0; x < tiles.length; x++) {
            for(int y = 0; y < tiles[0].length; y++) {
                tiles[x][y] = new Tile(Material.GRASS);
            }
        }
    }

    public boolean equals(Object object) {
        if(!(object instanceof Chunk)) return false;

        Chunk chunk = (Chunk) object;

        return chunkX == chunk.chunkX && chunkY == chunk.chunkY;
    }
}
