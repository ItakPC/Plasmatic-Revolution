package com.ItakPC.plasmatic_rev;

import com.ItakPC.plasmatic_rev.reference.Reference;

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
        // Game object
        Reference.GAME = new Game();
        Reference.GAME.screen = Reference.GAME.new Screen();
        Reference.GAME.start();

        // You can customize the screen size under References
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        if(Reference.screenWidth == 0)
            Reference.screenWidth = toolkit.getScreenSize().width;
        if(Reference.screenHeight == 0)
            Reference.screenHeight = toolkit.getScreenSize().height;

        // Creates a Window
        JFrame frame = new JFrame(Reference.title);
        frame.setSize(Reference.screenWidth,Reference. screenHeight);
        frame.setUndecorated(true); // TODO: If use of custom screen size, set this to false and pack the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // If you use custom screen size, this will make sure its centered

        // Frame listeners
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!Reference.pressedKeys.contains(e.getKeyCode())) Reference.pressedKeys.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Reference.pressedKeys.remove((Integer)e.getKeyCode());
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
                Reference.mouseX = e.getX();
                Reference.mouseY = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Reference.mouseX = e.getX();
                Reference.mouseY = e.getY();
            }
        });

        // Custom Icon
        // Custom Cursor

        // Adds the screen
        frame.add(Reference.GAME.screen);

        // Makes the window visible
        frame.setVisible(true);
    }

}