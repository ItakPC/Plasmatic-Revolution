package com.ItakPC.plasmatic_rev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Screen extends JPanel implements Runnable {

    List<Integer> keysPressed;

    Thread thread;
    Map map;

    int pixelSize = 3;

    int mouseX;
    int mouseY;

    int screenFps = 0;
    int screenUps = 0;

    public Screen(JFrame frame) {
        keysPressed = new ArrayList<Integer>();

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!keysPressed.contains(e.getKeyCode())) keysPressed.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keysPressed.remove((Integer)e.getKeyCode());
            }
        });

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        map = new Map();

        thread = new Thread(this);
        thread.start();
    }

    public void update() {

    }

    public void paintComponent(Graphics graphics) {
        graphics.clearRect(0, 0, Main.screenWidth, Main.screenHeight);

        int imgWidth = 16;
        int imgHeight = 16;

        for(int x = 0; x < 20; x++) {
            for(int y = 0; y < 20; y++) {
                graphics.drawImage(ImageLib.GRASS, x*imgWidth*pixelSize, y*imgHeight*pixelSize, imgWidth*pixelSize, imgHeight*pixelSize, null);
            }
        }
        resetString();
        drawString(graphics, "FPS: " + screenFps);
        drawString(graphics, "");
        drawString(graphics, "Key Pressed Number: " + keysPressed.size());
        for(Integer integer : keysPressed) {
            drawString(graphics, "Key Pressed: " + integer);
        }
        drawString(graphics, "");
        drawString(graphics, "Mouse X: " + mouseX);
        drawString(graphics, "Mouse Y: " + mouseY);
    }

    int spaceY = 15;

    private void resetString() {
        spaceY = 15;
    }
    private void drawString(Graphics graphics, String text) {
        graphics.setColor(Color.black);
        graphics.drawString(text, 5, spaceY);
        spaceY+=15;
    }

    @Override
    public void run() {
        /**Updates Per Second */
        int ups = 50;
        /**Frames per Second */
        int fps = 50;
        /**Game Skip Ticks*/
        long gst = 1000 / ups;
        long fst = 1000 / fps;
        /** Max Frame Skips*/
        long maxFSkip = 5;
        long time = System.currentTimeMillis();
        int loops;
        int frames = 0;
        int updates = 0;

        long nextGameTick = System.currentTimeMillis();
        long nextFrameTick = System.currentTimeMillis();

        while(true) {
            loops = 0;
            while(System.currentTimeMillis() > nextGameTick && loops < maxFSkip) {
                update();
                nextGameTick += gst;
                updates++;
                loops++;
            }

            if(System.currentTimeMillis() > nextFrameTick) {
                nextFrameTick += fst;

                repaint();
                frames++;
            }

            if(time+1000 <= System.currentTimeMillis()) {
                time += 1000;

                screenFps = frames;
                screenUps = updates;
                frames = 0;
                updates = 0;
            }
        }
    }
}
