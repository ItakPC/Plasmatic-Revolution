package com.ItakPC.plasmatic_rev.reference;

import com.ItakPC.plasmatic_rev.Game;

import java.util.ArrayList;
import java.util.List;

public class Reference {

    // MAIN
    public static final String title = "Plasmatic Revolution";
    public static final float optimisedSceenWidth = 1920;
    public static final float optimisedSceenHeight = 1080;
    public static final boolean fullscreenWindow = false;
    public static int screenWidth = 800;
    public static int screenHeight = 600;

    // SCREEN
    public static float pixelSize = 3;
    public static int tileSize = 16;

    // GAME THREAD
    public static int updatesSec = 50;
    public static int framesSec = 100;
    public static long maxFSkip = 5;

    // GAME
    public static Game GAME;
    public static int SEED;

    // LISTENER
    public static List<Integer> pressedKeys = new ArrayList<Integer>();
    public static int mouseX;
    public static int mouseY;

    // CHUNK
    public static final int tileAmountX = 16;
    public static final int tileAmountY = 16;
    public static final int chunkAmountX = 5;
    public static final int chunkAmountY = 5;

}
