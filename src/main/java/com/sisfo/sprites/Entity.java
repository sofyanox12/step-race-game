package com.sisfo.sprites;

import java.util.Arrays;

import com.sisfo.GPanel;

import javafx.scene.image.Image;

public class Entity extends Object {

    public static int PLAYER1_X;
    public static int PLAYER1_Y;
    public static int PLAYER2_X;
    public static int PLAYER2_Y;
    public static int PLAYER1_SCORE;
    public static int PLAYER2_SCORE;
    public static int playerID = 2;

    public final Image[] walkP1 = new Image[6];
    public final Image[] idleP1 = new Image[4];

    public final Image[] walkP2 = new Image[6];
    public final Image[] idleP2 = new Image[4];

    public int spriteCounterP1 = 0;
    public int spriteNumP1 = 1;

    public int spriteCounterP2 = spriteCounterP1;
    public int spriteNumP2 = spriteNumP1;

    public Boolean trapped, punched;

    public static int traps[] = new int[13];

    public void generateItem() {

        for (int i = 0; i < traps.length; i++) {
            traps[i] = (int) (Math.random() * 49) + 4; // Bikin trap dari tile ke - 49 sampai tile ke - 4

            if (i > 0 && Math.abs(traps[i-1] - traps[i]) == 1) {
                traps[i] += 8;
            }
        }

    }

    public void setPlayerStartingPos(GPanel window) {
        // Posisi awal Player 1
        PLAYER1_X = 12;
        PLAYER1_Y = window.panelHeigth - (5 * window.tileSize);

        // Posisi awal Player 2
        PLAYER2_X = 12;
        PLAYER2_Y = window.panelHeigth - (5 * window.tileSize);

    }

    public void detectUnlucky() {
        for (int i = 0; i < traps.length; i++) {
            if (traps[i] == PLAYER1_SCORE) {
                PLAYER1_X -= 3 * 48;
                PLAYER1_SCORE -= 3;
            }

            if (PLAYER2_SCORE == traps[i]) {
                PLAYER2_X -= 3 * 48;
                PLAYER2_SCORE -= 3;
            }
        }
    }

    public void getPlayer(int x, int playerID) {

        if (playerID == 1) { // Karakter untuk Player 1

            if (x == 1) { // Character : Old Man
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    this.walkP1[i] = new Image(getClass().getResourceAsStream("old_walk" + i + ".png"));
                    if (t < idleP1.length)
                        this.idleP1[t] = new Image(getClass().getResourceAsStream("old_idle" + t + ".png"));
                }

            } else if (x == 2) { // Character : Girl
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    this.walkP1[i] = new Image(getClass().getResourceAsStream("girl_walk" + i + ".png"));
                    if (t < idleP1.length)
                        this.idleP1[t] = new Image(getClass().getResourceAsStream("girl_idle" + t + ".png"));
                }

            } else if (x == 3) { // Character : Man
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    this.walkP1[i] = new Image(getClass().getResourceAsStream("man_walk" + i + ".png"));
                    if (t < idleP1.length)
                        this.idleP1[t] = new Image(getClass().getResourceAsStream("man_idle" + t + ".png"));
                }

            } else if (x == 4) {
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) { // Character : punk
                    this.walkP1[i] = new Image(getClass().getResourceAsStream("punk_walk" + i + ".png"));
                    if (t < idleP1.length)
                        this.idleP1[t] = new Image(getClass().getResourceAsStream("punk_idle" + t + ".png"));
                }

            }

        } else if (playerID == 2) { // Karakter untuk Player 2

            if (x == 1) { // Character : Old Man
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    this.walkP2[i] = new Image(getClass().getResourceAsStream("old_walk" + i + ".png"));
                    if (t < idleP2.length)
                        this.idleP2[t] = new Image(getClass().getResourceAsStream("old_idle" + t + ".png"));
                }

            } else if (x == 2) { // Character : Girl
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    this.walkP2[i] = new Image(getClass().getResourceAsStream("girl_walk" + i + ".png"));
                    if (t < idleP2.length)
                        this.idleP2[t] = new Image(getClass().getResourceAsStream("girl_idle" + t + ".png"));
                }

            } else if (x == 3) { // Character : Man
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    this.walkP2[i] = new Image(getClass().getResourceAsStream("man_walk" + i + ".png"));
                    if (t < idleP2.length)
                        this.idleP2[t] = new Image(getClass().getResourceAsStream("man_idle" + t + ".png"));
                }

            } else if (x == 4) {
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) { // Character : punk
                    this.walkP2[i] = new Image(getClass().getResourceAsStream("punk_walk" + i + ".png"));
                    if (t < idleP2.length)
                        this.idleP2[t] = new Image(getClass().getResourceAsStream("punk_idle" + t + ".png"));
                }

            } else {
                System.out.println("Invalid Player ID!");
            }
        }

    }
}
