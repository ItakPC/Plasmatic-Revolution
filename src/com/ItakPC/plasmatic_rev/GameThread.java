package com.ItakPC.plasmatic_rev;

import static com.ItakPC.plasmatic_rev.reference.Reference.*;

public class GameThread extends Thread implements Runnable {

    @Override
    public void run() {
        // The wait time in milliseconds before next update/render
        long GAME_SKIP_TICKS = 1000 / updatesSec;
        long FRAME_SKIP_TICKS = 1000 / framesSec;

        // Schedules the updates and repaints
        long next_game_tick = System.currentTimeMillis();
        long next_frame_tick = System.currentTimeMillis();
        long time = System.currentTimeMillis();

        // Counts the amount of time the game updates without rendering
        int loops;

        // Variables to update the UPS & FPS variables
        int frames = 0;
        int updates = 0;

        while(true){
            loops = 0;
            while(System.currentTimeMillis() > next_game_tick && loops < maxFSkip){
                GAME.update();

                next_game_tick += GAME_SKIP_TICKS;
                updates++;
                loops++;
            }

            if(System.currentTimeMillis() > next_frame_tick){
                next_frame_tick += FRAME_SKIP_TICKS;
                GAME.screen.repaint();
                frames++;
            }

            if(time+1000 <= System.currentTimeMillis()){
                time+=1000;

                GAME.FPS = frames;
                GAME.UPS = updates;
                updates = 0;
                frames = 0;
            }
        }
    }

}
