package com.ItakPC.plasmatic_rev;

import static com.ItakPC.plasmatic_rev.reference.Reference.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    public static void main(String[] args){
        // Makes sure the GUI updates correctly
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

    public Main(){
        Dimension maxScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(screenWidth == 0) screenWidth = maxScreenSize.width;
        if(screenHeight == 0) screenHeight = maxScreenSize.height;

        /** Screen object*/
        GAME = new Game();
        GAME.screen = GAME.new Screen();
        GAME.start();

        // You can customize the screen size under References


        /** Creates a Window */
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        boolean fullscreen = fullscreenWindow;
        if(screenWidth == maxScreenSize.width && screenHeight == maxScreenSize.height) fullscreen = true;
        frame.setSize(screenWidth, screenHeight);

        if(fullscreen) {
            frame.setUndecorated(true);
        }else{
            frame.getContentPane().setPreferredSize(new Dimension(screenWidth, screenHeight));
            frame.pack();
        }


        // Frame listeners
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!pressedKeys.contains(e.getKeyCode())) pressedKeys.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressedKeys.remove((Integer)e.getKeyCode());
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

        // Custom Icon
        // Custom Cursor

        // Adds the screen
        frame.add(GAME.screen);

        // Makes the window visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}