package com.ItakPC.plasmatic_rev.world;

import com.ItakPC.plasmatic_rev.Game;
import com.ItakPC.plasmatic_rev.Material;
import com.ItakPC.plasmatic_rev.SimplexNoise;

import java.awt.*;

import static com.ItakPC.plasmatic_rev.reference.Reference.*;
import static com.ItakPC.plasmatic_rev.Game.*;

public class Chunk {

    Tile[][] tiles;

    int chunkX;
    int chunkY;

    public Chunk(int chunkX, int chunkY){
        this.chunkX = chunkX;
        this.chunkY = chunkY;
    }

    public void populate(){
        tiles = new Tile[tileAmountX][tileAmountY];

        SimplexNoise simplexNoise = new SimplexNoise(7, 0.1);

        double xStart = chunkX*tileAmountX;
        double xEnd = xStart+tileAmountX;
        double yStart = chunkY*tileAmountY;
        double yEnd = yStart+tileAmountY;

        int xResolution = tileAmountX;
        int yResolution = tileAmountY;

        double[][] data = new double[xResolution][yResolution];

        for(int i = 0; i < xResolution; i++){
            for(int j = 0; j < yResolution; j++){
                int x = (int)(xStart+(i*(xEnd-xStart)/xResolution));
                int y = (int)(yStart+(j*(yEnd-yStart)/yResolution));

                double noise = (1+simplexNoise.getNoise(x, y))/2;

                Material material;
                if(noise < 0.495F)
                    material = Material.DEEPWATER;
                else if(noise < 0.5F)
                    material = Material.WATER;
                else if(noise < 0.525F)
                    material = Material.SAND;
                else if(noise < 0.545F)
                    material = Material.GRASS;
                else
                    material = Material.ROCK;

                tiles[i][j] = new Tile(material);

                data[i][j] = noise;
            }
        }
    }

    public void render(Graphics g) {
        int posX = ceil((chunkX * tileAmountX * tileSize * pixelSize - Game.instance.player.posX * tileSize * pixelSize - tileSize * pixelSize / 2) * pixelScaleWidth + screenWidth / 2);
        int posY = ceil((chunkY * tileAmountY * tileSize * pixelSize - Game.instance.player.posY * tileSize * pixelSize - tileSize * pixelSize / 2) * pixelScaleHeight + screenHeight / 2);

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                tiles[x][y].render(g, ceil(x * tileSize * pixelSize * pixelScaleWidth + posX), ceil(y * tileSize * pixelSize * pixelScaleHeight + posY));
                //g.drawImage(tiles[x][y].texture, ceil(x * tileSize * pixelSize * pixelScaleWidth + posX), ceil(y * tileSize * pixelSize * pixelScaleHeight + posY), ceil(tileSize * pixelSize * pixelScaleWidth), ceil(tileSize * pixelSize * pixelScaleHeight), null);
            }
        }
        g.drawLine(posX, 0, posX, screenHeight);
        g.drawLine(0, posY, screenWidth, posY);

    }

    public boolean equals(Object object){
        if(!(object instanceof Chunk))
            return false;

        Chunk chunk = (Chunk) object;
        return chunkX == chunk.chunkX && chunkY == chunk.chunkY;
    }
}
