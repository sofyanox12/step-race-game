package com.sisfo.sprites;

import com.sisfo.GPanel;

public class Entity extends Object {

    public static int PLAYER1_X;
    public static int PLAYER1_Y;
    public static int PLAYER2_X;
    public static int PLAYER2_Y;
    public static int PLAYER1_SCORE;
    public static int PLAYER2_SCORE;
    public static int playerID = 2;

    public int worldX, worldY;

    public int spriteCounterP1 = 0;
    public int spriteNumP1 = 1;

    public int spriteCounterP2 = spriteCounterP1;
    public int spriteNumP2 = spriteNumP1;

    

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

    public void setPlayerStartingPos(GPanel window) {
        // Posisi awal Player 1
        PLAYER1_X = 12;
        PLAYER1_Y = window.panelHeigth - (5 * window.tileSize);
        worldX = PLAYER1_X;
        worldY = PLAYER1_Y;

        // Posisi awal Player 2
        PLAYER2_X = 12;
        PLAYER2_Y = window.panelHeigth - (5 * window.tileSize);
        worldX = PLAYER2_X;
        worldY = PLAYER2_Y;

    }

}
