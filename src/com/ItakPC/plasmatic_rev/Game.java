package com.ItakPC.plasmatic_rev;

import com.ItakPC.plasmatic_rev.reference.ImageLib;
import com.ItakPC.plasmatic_rev.reference.Reference;

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

    public Game(){
        map = new Map(new Player());

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
            if (key == KeyEvent.VK_W) {
                map.player.posY--;
            }
        }

        for (int key1 : Reference.pressedKeys) {
            if (key1 == KeyEvent.VK_S) {
                map.player.posY++;
            }
        }

        for (int key2 : Reference.pressedKeys) {
            if (key2 == KeyEvent.VK_D) {
                map.player.posX++;
            }
        }

        for (int key3 : Reference.pressedKeys) {
            if (key3 == KeyEvent.VK_A) {
                map.player.posX--;
            }
        }
        /** 2 if statements to not allow you to get into negative position :D */
        if(map.player.posX <= 0) {
            map.player.posX = 0;
        }

        if(map.player.posY <= 0) {
            map.player.posY = 0;
        }
    }

    public class Screen extends JPanel {

        public void paintComponent(Graphics g){
            // Clears the screen
            g.clearRect(0, 0, Reference.screenWidth, Reference.screenHeight);

            // Renders background of screen
            renderBackground(g);

            // Renders main part of screen
            render(g);

            // Renders foreground of screen
            renderForeground(g);
        }

        private void renderBackground(Graphics g){
            for(Chunk chunk : map.loadedChunks) {
                g.drawLine(100+chunk.chunkX*5, 100+chunk.chunkY*5, 100+chunk.chunkX*5, 100+chunk.chunkY*5);
            }

          /*  for(int x = 0; x < 20; x++){
                for(int y = 0; y < 20; y++){
                    g.drawImage(ImageLib.GRASS, x*Reference.tileSize*Reference.pixelSize, y*Reference.tileSize*Reference.pixelSize, Reference.tileSize*Reference.pixelSize, Reference.tileSize*Reference.pixelSize, null);
                }
            }*/
        }

        private void render(Graphics g){

        }

        private void renderForeground(Graphics g){
                        resetString();
            drawString(g, "FPS: " + FPS);
            drawString(g, "UPS: " + UPS);
            drawString(g, "");
            drawString(g, "AmountKeysPressed: " + Reference.pressedKeys.size());
            drawList(g, toStringList("KeyPressed: ", Reference.pressedKeys));
            drawString(g, "");
            drawString(g, "MouseX: " + Reference.mouseX);
            drawString(g, "MouseY: " + Reference.mouseY);
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
