package com.sisfo.sprites;

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
    public Boolean gotPower1 = false;
    public Boolean gotPower2 = false;

    public static String powerSlot1[];
    public static String powerSlot2[];

    public final Image[] walkP1 = new Image[6];
    public final Image[] idleP1 = new Image[4];

    public final Image[] walkP2 = new Image[6];
    public final Image[] idleP2 = new Image[4];

    public static int traps[] = new int[13];
    public static int powerUps[] = new int[7];

    public int spriteCounterP1 = 0;
    public int spriteNumP1 = 1;

    public int spriteCounterP2 = spriteCounterP1;
    public int spriteNumP2 = spriteNumP1;

    public Boolean P1_IS_INVINCIBLE = false;

    private String powers[] = { "BLINK", "HOOK", "INVINCIBLE" };
    private String skill;

    public void generateItem() {

        powerSlot1 = new String[3];
        powerSlot2 = new String[3];

        for (int i = 0, t = 0; i < traps.length; i++, t++) {

            if (i < traps.length) {
                traps[i] = (int) (Math.random() * 49) + 7; // Bikin trap dari tile ke - 49 sampai tile ke - 4
            }

            if (t < powerUps.length) {
                powerUps[t] = (int) (Math.random() * 48) + 4;

                if (traps[i] == powerUps[t]) {
                    powerUps[i]++;
                    traps[i]--;
                }
            }

            if (i > 0 && Math.abs(traps[i - 1] - traps[i]) == 1) {
                traps[i] += 8;
            }

            if (traps[i] < 7) {
                traps[i] += 10;
            }

        }

    }

    public void setPlayerStartingPos(GPanel window) {
        // SET POSISI AWAL PLAYER 1
        PLAYER1_X = 12;
        PLAYER1_Y = window.panelHeigth - (5 * window.tileSize);

        // SET POSISI AWAL PLAYER 2
        PLAYER2_X = 12;
        PLAYER2_Y = window.panelHeigth - (5 * window.tileSize);

    }

    public void detectEvent() {

        // MENDETEKSI JEBAKAN
        if (Player.forward != 6) {
            for (int i = 0; i < traps.length; i++) {
                if (traps[i] == PLAYER1_SCORE && !P1_IS_INVINCIBLE) {
                    PLAYER1_X -= 3 * 48;
                    PLAYER1_SCORE -= 3;
                }

                if (PLAYER2_SCORE == traps[i]) {
                    PLAYER2_X -= 3 * 48;
                    PLAYER2_SCORE -= 3;
                }

            }
        }

        // MENDETEKSI POWER UP
        skill = powers[(int) (Math.random() * 3)];

        if (!gotPower1) {
            for (int i = 0; i < powerUps.length; i++) {

                // POWER UP UNTUK PLAYER 1
                if (powerUps[i] == PLAYER1_SCORE) {
                    if (powerSlot1[0] == null) {
                        powerSlot1[0] = skill;
                        gotPower1 = true;
                        break;
                    } else if (powerSlot1[1] == null) {
                        powerSlot1[1] = skill;
                        gotPower1 = true;
                        break;
                    } else if (powerSlot1[2] == null) {
                        powerSlot1[2] = skill;
                        gotPower1 = true;
                        break;
                    }
                }

            }
        }

        if (!gotPower2) {
            for (int i = 0; i < powerUps.length; i++) {

                // POWER UP UNTUK PLAYER 2
                if (powerUps[i] == PLAYER2_SCORE) {
                    if (powerSlot2[0] == null) {
                        powerSlot2[0] = skill;
                        gotPower2 = true;
                        break;
                    } else if (powerSlot2[1] == null) {
                        powerSlot2[1] = skill;
                        gotPower2 = true;
                        break;
                    } else if (powerSlot2[2] == null) {
                        powerSlot2[2] = skill;
                        gotPower2 = true;
                        break;
                    }
                }
            }
        }

    }

    public void getPlayer(int x, int playerID) {

        if (playerID == 1) { // Karakter untuk Player 1

            if (x == 1) { // Character : Old Man
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    walkP1[i] = new Image(getClass().getResourceAsStream("old_walk" + i + ".png"));
                    if (t < idleP1.length)
                        idleP1[t] = new Image(getClass().getResourceAsStream("old_idle" + t + ".png"));
                }

            } else if (x == 2) { // Character : Girl
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    walkP1[i] = new Image(getClass().getResourceAsStream("girl_walk" + i + ".png"));
                    if (t < idleP1.length)
                        idleP1[t] = new Image(getClass().getResourceAsStream("girl_idle" + t + ".png"));
                }

            } else if (x == 3) { // Character : Man
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    walkP1[i] = new Image(getClass().getResourceAsStream("man_walk" + i + ".png"));
                    if (t < idleP1.length)
                        idleP1[t] = new Image(getClass().getResourceAsStream("man_idle" + t + ".png"));
                }

            } else if (x == 4) {
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) { // Character : punk
                    walkP1[i] = new Image(getClass().getResourceAsStream("punk_walk" + i + ".png"));
                    if (t < idleP1.length)
                        idleP1[t] = new Image(getClass().getResourceAsStream("punk_idle" + t + ".png"));
                }

            }

        } else if (playerID == 2) { // Karakter untuk Player 2

            if (x == 1) { // Character : Old Man
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    walkP2[i] = new Image(getClass().getResourceAsStream("old_walk" + i + ".png"));
                    if (t < idleP2.length)
                        idleP2[t] = new Image(getClass().getResourceAsStream("old_idle" + t + ".png"));
                }

            } else if (x == 2) { // Character : Girl
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    walkP2[i] = new Image(getClass().getResourceAsStream("girl_walk" + i + ".png"));
                    if (t < idleP2.length)
                        idleP2[t] = new Image(getClass().getResourceAsStream("girl_idle" + t + ".png"));
                }

            } else if (x == 3) { // Character : Man
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    walkP2[i] = new Image(getClass().getResourceAsStream("man_walk" + i + ".png"));
                    if (t < idleP2.length)
                        idleP2[t] = new Image(getClass().getResourceAsStream("man_idle" + t + ".png"));
                }

            } else if (x == 4) {
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) { // Character : punk
                    walkP2[i] = new Image(getClass().getResourceAsStream("punk_walk" + i + ".png"));
                    if (t < idleP2.length)
                        idleP2[t] = new Image(getClass().getResourceAsStream("punk_idle" + t + ".png"));
                }

            } else {
                System.out.println("Invalid Player ID!");
            }
        }

    }
}
