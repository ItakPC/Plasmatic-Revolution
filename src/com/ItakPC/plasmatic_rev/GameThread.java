package com.ItakPC.plasmatic_rev;

import com.ItakPC.plasmatic_rev.reference.Reference;

public class GameThread extends Thread implements Runnable {

    @Override
    public void run() {
        // The wait time in milliseconds before next update/render
        long GAME_SKIP_TICKS = 1000 / Reference.updatesSec;
        long FRAME_SKIP_TICKS = 1000 / Reference.framesSec;

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
            while(System.currentTimeMillis() > next_game_tick && loops < Reference.maxFSkip){
                Reference.GAME.update();

                next_game_tick += GAME_SKIP_TICKS;
                updates++;
                loops++;
            }

            if(System.currentTimeMillis() > next_frame_tick){
                next_frame_tick += FRAME_SKIP_TICKS;
                Reference.GAME.screen.repaint();
                frames++;
            }

            if(time+1000 <= System.currentTimeMillis()){
                time+=1000;

                Reference.GAME.FPS = frames;
                Reference.GAME.UPS = updates;
                updates = 0;
                frames = 0;
            }
        }
    }

}
