package com.sisfo.sprites;

import java.util.Random;

import com.sisfo.GPanel;

import javafx.scene.image.Image;

/*
    Peran Kelas Entity adalah sebagai pemetaan
    objek entitas yang memiliki nilai interaksinya
    seperti Pemain, Power Up, dan Perangkap.
*/

public class Entity extends Object {

    public static int PLAYER1_X, PLAYER1_Y, PLAYER2_X, PLAYER2_Y, PLAYER1_SCORE, PLAYER2_SCORE;
    public static int playerID = 2;

    public static String powerSlot1[] = new String[3];
    public static String powerSlot2[] = new String[3];

    public final Image[] walkP1 = new Image[6];
    public final Image[] idleP1 = new Image[4];

    public final Image[] walkP2 = new Image[6];
    public final Image[] idleP2 = new Image[4];

    public static int traps[] = new int[15];
    public static int powerUps[] = new int[8];

    public static Boolean P1_IS_INVINCIBLE = false;
    public static Boolean P2_IS_INVINCIBLE = false;

    public int spriteCounterP1 = 0;
    public int spriteNumP1 = 1;

    public int spriteCounterP2 = spriteCounterP1;
    public int spriteNumP2 = spriteNumP1;

    
    public Boolean gotPower1 = false;
    public Boolean gotPower2 = false;

    private String powers[] = { "BLINK", "HOOK", "INVINCIBLE" };
    private String skill;

    public void generateItem() {
        Random random = new Random();
        
        int index = 0;
        while (index < traps.length) { // MENENTUKAN POSISI PERANGKAP
            int num = random.nextInt((49 - 4) + 1) + 4;
            if (isThere(num, traps)) {
                if (isNearby(num, traps)) {
                    traps[index] = num;
                    index++;
                }
            }
        }

        index = 0;
        while (index < powerUps.length) { // MENENTUKAN POSISI POWERUP
            int num = random.nextInt((49 - 4) + 1) + 4;
            if (isThere(num, powerUps)) {
                if (isThere(num, traps)) {
                    if (isNearby(num, powerUps)) {
                        powerUps[index] = num;
                        index++;
                    }
                }
            }
        }
    }

    // METHOD UNTUK MEMILAH KEBERADAAN TILE YANG SAMA
    public Boolean isThere(int num, int[] array) {
        for (int i = 0; i < array.length; i++)
            if (num == array[i])
                return false;

        return true;
    }

    // METHOD UNTUK MEMILAH SELISIH TILE YANG BERJARAK 1 & 3 UBIN
    public Boolean isNearby(int num, int[] array) {
        for (int i = 0; i < array.length; i++)
            if (num - array[i] == 1 || num - array[i] == -1 || num - array[i] == 3 || num - array[i] == -3)
                return false;

        return true;
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
                }

                else if (PLAYER2_SCORE == traps[i] && !P2_IS_INVINCIBLE) {
                    PLAYER2_X -= 3 * 48;
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

    public void getPlayer(int charID, int playerID) {
        /*
         * Informasi charID :
         * 1 : OLDIE
         * 2 : KIDDO
         * 3 : ARMIAN
         * 4 : PUNK
         */

        // SET UNTUK PLAYER 1
        if (playerID == 1) {

            if (charID == 1) {
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    walkP1[i] = new Image(getClass().getResourceAsStream("old_walk" + i + ".png"));
                    if (t < idleP1.length)
                        idleP1[t] = new Image(getClass().getResourceAsStream("old_idle" + t + ".png"));
                }

            } else if (charID == 2) {
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    walkP1[i] = new Image(getClass().getResourceAsStream("girl_walk" + i + ".png"));
                    if (t < idleP1.length)
                        idleP1[t] = new Image(getClass().getResourceAsStream("girl_idle" + t + ".png"));
                }

            } else if (charID == 3) {
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    walkP1[i] = new Image(getClass().getResourceAsStream("man_walk" + i + ".png"));
                    if (t < idleP1.length)
                        idleP1[t] = new Image(getClass().getResourceAsStream("man_idle" + t + ".png"));
                }

            } else if (charID == 4) {
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    walkP1[i] = new Image(getClass().getResourceAsStream("punk_walk" + i + ".png"));
                    if (t < idleP1.length)
                        idleP1[t] = new Image(getClass().getResourceAsStream("punk_idle" + t + ".png"));
                }

            }

            // SET UNTUK PLAYER 2
        } else if (playerID == 2) {

            if (charID == 1) {
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    walkP2[i] = new Image(getClass().getResourceAsStream("old_walk" + i + ".png"));
                    if (t < idleP2.length)
                        idleP2[t] = new Image(getClass().getResourceAsStream("old_idle" + t + ".png"));
                }

            } else if (charID == 2) {
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    walkP2[i] = new Image(getClass().getResourceAsStream("girl_walk" + i + ".png"));
                    if (t < idleP2.length)
                        idleP2[t] = new Image(getClass().getResourceAsStream("girl_idle" + t + ".png"));
                }

            } else if (charID == 3) {
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    walkP2[i] = new Image(getClass().getResourceAsStream("man_walk" + i + ".png"));
                    if (t < idleP2.length)
                        idleP2[t] = new Image(getClass().getResourceAsStream("man_idle" + t + ".png"));
                }

            } else if (charID == 4) {
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) { // Character : punk
                    walkP2[i] = new Image(getClass().getResourceAsStream("punk_walk" + i + ".png"));
                    if (t < idleP2.length)
                        idleP2[t] = new Image(getClass().getResourceAsStream("punk_idle" + t + ".png"));
                }

            }
        }
    }
}
