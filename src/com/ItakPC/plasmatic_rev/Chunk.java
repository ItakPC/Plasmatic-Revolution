package com.ItakPC.plasmatic_rev;

import static com.ItakPC.plasmatic_rev.reference.Reference.*;

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

        /*double xStart = chunkX*tileAmountX;
        double xEnd = xStart+tileAmountX;
        double yStart = chunkY*tileAmountY;
        double yEnd = yStart+tileAmountY;*/

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
                    material = Material.GRASS;
                else
                    material = Material.SAND;
                tiles[i][j] = new Tile(material);

                data[i][j] = noise;
            }
        }
    }

    public boolean equals(Object object){
        if(!(object instanceof Chunk))
            return false;

        Chunk chunk = (Chunk) object;
        return chunkX == chunk.chunkX && chunkY == chunk.chunkY;
    }
}
