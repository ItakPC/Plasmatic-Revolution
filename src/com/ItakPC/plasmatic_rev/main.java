package com.ItakPC.plasmatic_rev;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void  main(String[] args) {

        new Main();
    }

    public Main() {

        /** Rendering */
        JFrame frame = new JFrame();
        //Title Of The Application.
        frame.setTitle("Plasmatic Revolution");
        //Start With Full Screen Window.
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
