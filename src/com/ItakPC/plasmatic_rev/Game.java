package com.ItakPC.plasmatic_rev;

import com.ItakPC.plasmatic_rev.reference.ImageLib;
import com.ItakPC.plasmatic_rev.reference.Reference;

import static com.ItakPC.plasmatic_rev.reference.Reference.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {

    public Screen screen;

    public Map map;
    public GameThread thread;

    public int FPS;
    public int UPS;

    public float pixelScaleWidth;
    public float pixelScaleHeight;

    public Game(){
        map = new Map(new Player());

        pixelScaleWidth = screenWidth / optimisedSceenWidth;
        pixelScaleHeight = screenHeight / optimisedSceenHeight;
  
        // Starts the game thread
        thread = new GameThread();
    }

    public void update(){
        map.checkChunks();

        // Update Input
        updateInput();
    }

    private void updateInput() {
        // Keys
        for (int key : Reference.pressedKeys) {
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                map.player.posY--;
            }
        }

        for (int key1 : pressedKeys) {
            if (key1 == KeyEvent.VK_S || key1 == KeyEvent.VK_DOWN) {
                map.player.posY++;
            }
        }

        for (int key2 : pressedKeys) {
            if (key2 == KeyEvent.VK_D || key2 == KeyEvent.VK_RIGHT) {
                map.player.posX++;
            }
        }

        for (int key3 : pressedKeys) {
            if (key3 == KeyEvent.VK_A || key3 == KeyEvent.VK_LEFT) {
                map.player.posX--;
            }
        }
    }

    public static int ceil(double x){
        return (int)Math.ceil(x);
    }

    public static int fastFloor(double x){
        int xi = (int)x;
        return x<xi ? xi-1 : xi;
    }

    public class Screen extends JPanel {

        public void paintComponent(Graphics g){
            // Clears the screen
            g.clearRect(0, 0, screenWidth, screenHeight);

            // Renders background of screen
            renderBackground(g);

            // Renders main part of screen
            render(g);

            // Renders foreground of screen
            renderForeground(g);
        }

        private void renderBackground(Graphics g) {
            for (Chunk chunk : map.loadedChunks) {
                int posX = ceil((chunk.chunkX * tileAmountX * tileSize * pixelSize - map.player.posX * tileSize * pixelSize - tileSize * pixelSize / 2) * pixelScaleWidth + screenWidth / 2);
                int posY = ceil((chunk.chunkY * tileAmountY * tileSize * pixelSize - map.player.posY * tileSize * pixelSize - tileSize * pixelSize / 2) * pixelScaleHeight + screenHeight / 2);

                for (int x = 0; x < chunk.tiles.length; x++) {
                    for (int y = 0; y < chunk.tiles[0].length; y++) {
                        g.drawImage(chunk.tiles[x][y].texture, ceil(x * tileSize * pixelSize * pixelScaleWidth + posX), ceil(y * tileSize * pixelSize * pixelScaleHeight + posY), ceil(tileSize * pixelSize * pixelScaleWidth), ceil(tileSize * pixelSize * pixelScaleHeight), null);
                    }
                }
                g.drawLine(posX, 0, posX, screenHeight);
                g.drawLine(0, posY, screenWidth, posY);
            }
        }

        private void render(Graphics g){
            g.drawImage(map.player.texture, screenWidth / 2 - ceil(tileSize * pixelSize * pixelScaleWidth / 2), screenHeight / 2 - ceil(tileSize * pixelSize * pixelScaleHeight / 2), ceil(tileSize * pixelSize * pixelScaleWidth), ceil(tileSize * pixelSize * pixelScaleHeight), null);
        }

        private void renderForeground(Graphics g){
                        resetString();
            drawString(g, "FPS: " + FPS);
            drawString(g, "UPS: " + UPS);
            drawString(g, "");
            drawString(g, "AmountKeysPressed: " + pressedKeys.size());
            drawList(g, toStringList("KeyPressed: ", pressedKeys));
            drawString(g, "");
            drawString(g, "MouseX: " + mouseX);
            drawString(g, "MouseY: " + mouseY);
            drawString(g, "");
            drawString(g, "PosX: " + map.player.posX);
            drawString(g, "PosY: " + map.player.posY);
        }

        int spaceY = 15;
        private void resetString(){
            spaceY = 15;
        }
        private void drawString(Graphics g, String text){
            g.setColor(Color.BLACK);
            g.drawString(text, 5, spaceY);
            spaceY+=15;
        }
        private void drawList(Graphics g, java.util.List<String> list){
            for(String text : list){
                drawString(g, text);
            }
        }
        private java.util.List<String> toStringList(String pre, java.util.List list){
            java.util.List<String> stringList = new ArrayList<String>();

            for(Object object : list){
                stringList.add(pre+object.toString());
            }

            return stringList;
        }
    }

    /**
     * Starts the game
     */
    public void start(){
        thread.start();
    }

}
