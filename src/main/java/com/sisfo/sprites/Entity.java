package com.sisfo.sprites;

import com.sisfo.GameEvent;

public class Entity extends GameEvent {


    public int PLAYER1_X, PLAYER1_Y, PLAYER2_X, PLAYER2_Y;
    public int PLAYER1_SCORE, PLAYER2_SCORE;
    
    public long start = System.currentTimeMillis();

    public int spriteCounterP1 = 0;
    public int spriteNumP1 = 1;

    public int spriteCounterP2 = spriteCounterP1;
    public int spriteNumP2 = spriteNumP1;

    public int playerID = 1;

    public int trap[];

    public void play1() {
        
    }

    public void play() {

    }

    public void generateTraps() {

        trap = new int[10];

        for (int i : trap) {
            trap[i] = (int) (Math.random() * 50 + 1);
        }
    }
    

}
