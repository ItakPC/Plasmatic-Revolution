package com.ItakPC.plasmatic_rev;

import static com.ItakPC.plasmatic_rev.reference.Reference.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Map {

    List<Chunk> loadedChunks = new CopyOnWriteArrayList<Chunk>();
    Player player;

    public Map(Player player) {
        this.player = player;
        checkChunks();
    }

    public void checkChunks() {
        int playerChunkX = player.posX / tileAmountX;
        int playerChunkY = player.posY / tileAmountY;

        /** Unload chunks */
        unloadChunks(playerChunkX, playerChunkY);

        /** Load chunks */
        loadChunks(playerChunkX, playerChunkY);

    }

    private void unloadChunks(int playerChunkX, int playerChunkY) {
        for(Chunk chunk : loadedChunks) {
            if(chunk.chunkX > playerChunkX+(chunkAmountX-1)/2 || chunk.chunkX < playerChunkX-(chunkAmountX-1)/2 || chunk.chunkY > playerChunkY+(chunkAmountY-1)/2 || chunk.chunkY < playerChunkY-(chunkAmountY-1)/2)
                loadedChunks.remove(chunk);
        }
    }

    private void loadChunks(int playerChunkX, int playerChunkY) {
        for(int x = playerChunkX-(chunkAmountX-1)/2; x <= playerChunkX+(chunkAmountX-1)/2; x++) {
            for(int y = playerChunkY-(chunkAmountY-1)/2; y <= playerChunkY+(chunkAmountY-1)/2; y++) {
                if(!loadedChunks.contains(new Chunk(x, y))) {

                    Chunk chunk = new Chunk(x, y);
                    chunk.populate();
                    loadedChunks.add(chunk);
                }
            }
        }
    }
}
