package com.ItakPC.plasmatic_rev;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static final String title = "Plasmatic Revolution";
    public static int screenWidth;
    public static int screenHeight;

    public static void  main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() { new Main(); }});

    }

    public Main() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        screenWidth = toolkit.getScreenSize().width;
        screenHeight = toolkit.getScreenSize().height;

        /** Rendering */
        JFrame frame = new JFrame(title);
        //Start With Full Screen Window.
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** Screen */
        frame.add(new Screen(frame));

        frame.setVisible(true);
    }
}
