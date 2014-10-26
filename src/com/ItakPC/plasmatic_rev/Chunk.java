package com.ItakPC.plasmatic_rev;

public class Chunk {

    int chunkX;
    int chunkY;

    public Chunk(int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
    }

    public boolean equals(Object object) {
        if(!(object instanceof Chunk)) return false;

        Chunk chunk = (Chunk) object;

        return chunkX == chunk.chunkX && chunkY == chunk.chunkY;
    }
}
