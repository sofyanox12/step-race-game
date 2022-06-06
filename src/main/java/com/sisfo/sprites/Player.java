package com.sisfo.sprites;

import com.sisfo.GPanel;
import com.sisfo.GameEvent;
import com.sisfo.tiles.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/*
    Peran Kelas Player disini adalah untuk menyatakan
    berbagai informasi apa saja yang akan terjadi dan sedang
    terjadi pada player.
*/

public class Player extends Entity {

    public static int moves = 0;
    public static int mapShift = 0;

    public static int player1, player2;

    public final int WIN_SCORE = 50;

    public static Boolean idlingP1 = true;
    public static Boolean idlingP2 = true;
    public static Boolean winner = false;

    public static Boolean usingBlinkP1 = false;
    public static Boolean usingBlinkP2 = false;

    public Boolean computerPlay = true;
    public Boolean diceRolling = false;

    public int move, maxMove;

    private final GameEvent event;
    private final GPanel window;

    public Player(GPanel window, GameEvent event) {

        this.window = window;
        this.event = event;
        this.setPlayerStartingPos(window);
        this.generateItem();
        this.getPlayer(player1, 1);
        this.getPlayer(player2, 2);
        this.diceRoll();
        this.stopPlayer();
        powerSlot1[0] = "BLINK";
        powerSlot1[1] = "BLINK";

    }

    public void updateP1() {

        if (!idlingP1) {
            if (PLAYER1_X < maxMove) {
                playerID = 1;
                PLAYER1_X++;

                if (spriteCounterP1 > 5 && !idlingP1) { // DELAY ANIMASI
                    spriteNumP1 += (spriteNumP1 < walkP1.length) ? 1 : 0;
                    spriteNumP1 = (spriteNumP1 == walkP1.length) ? 1 : spriteNumP1;
                    spriteCounterP1 = 0;
                }
                spriteCounterP1++;

            } else {
                spriteNumP1--;
                stopPlayer();
            }
        }

        else {
            if (spriteCounterP1 > 10) { // DELAY ANIMASI
                spriteNumP1 += (spriteNumP1 < idleP1.length) ? 1 : 0;
                spriteNumP1 = (spriteNumP1 == idleP1.length) ? 1 : spriteNumP1;
                spriteCounterP1 = 0;
            }
            spriteCounterP1++;
        }

        if (mapShift < 0)
            mapShift = 0;

    }

    public void updateP2() {

        if (!idlingP2) {
            if (PLAYER2_X < maxMove) {
                playerID = 2;
                PLAYER2_X++;

                if (spriteCounterP2 > 5 && !idlingP2) { // DELAY ANIMASI
                    spriteNumP2 += (spriteNumP2 < walkP2.length) ? 1 : 0;
                    spriteNumP2 = (spriteNumP2 == walkP2.length) ? 1 : spriteNumP2;
                    spriteCounterP2 = 0;
                }
                spriteCounterP2++;

            } else {
                spriteNumP2--;
                stopPlayer();
            }
        }

        else {
            if (spriteCounterP2 > 10) { // DELAY ANIMASI
                spriteNumP2 += (spriteNumP2 < idleP2.length) ? 1 : 0;
                spriteNumP2 = (spriteNumP2 == idleP2.length) ? 1 : spriteNumP2;
                spriteCounterP2 = 0;
            }
            spriteCounterP2++;
        }
    }

    // MEKANISME RENDER PLAYER 1
    public void drawP1(GraphicsContext render) {

        Image player1 = null;

        if (idlingP1 && spriteNumP1 < idleP1.length) {
            spriteNumP1 = (spriteNumP1 < 1) ? 1 : spriteNumP1;
            player1 = idleP1[spriteNumP1 - 1];

        } else if (!idlingP1 && spriteNumP1 < walkP1.length) {
            spriteNumP1 = (spriteNumP1 < 1) ? 1 : spriteNumP1;
            player1 = walkP1[spriteNumP1 - 1];

        }
        render.drawImage(player1, PLAYER1_X, PLAYER1_Y, window.spriteSize, window.spriteSize);
    }

    // MEKANISME RENDER PLAYER 2
    public void drawP2(GraphicsContext render) {

        Image player2 = null;

        if (idlingP2 && spriteNumP2 < idleP2.length) {
            spriteNumP2 = (spriteNumP2 < 1) ? 1 : spriteNumP2;
            player2 = idleP2[spriteNumP2 - 1];

        } else if (!idlingP2 && spriteNumP2 < walkP2.length) {
            spriteNumP2 = (spriteNumP2 < 1) ? 1 : spriteNumP2;
            player2 = walkP2[spriteNumP2 - 1];

        }
        render.drawImage(player2, PLAYER2_X, PLAYER2_Y, window.spriteSize, window.spriteSize);
    }

    // MENSTOP PLAYER DAN MENGKONFIGURASI HAL YANG DIPERLUKAN
    public void stopPlayer() {
        diceRolling = false;
        Objects.showSpaceBar = true;

        if (moves > 1) {
            PLAYER1_SCORE += (playerID == 1) ? event.moves : 0;
            PLAYER2_SCORE += (playerID == 2) ? event.moves : 0;

            if (event.moves == 6) {
                playerID = (playerID == 1) ? 1 : 2;
                gotPower1 = true;
                gotPower2 = true;
                isTrapped = true;

            } else if (event.moves < 6) {
                playerID = (playerID == 1) ? 2 : 1;
                computerPlay = (computerPlay) ? false : true;
                gotPower1 = (playerID == 1) ? true : false;
                gotPower2 = (playerID == 2) ? true : false;
                isTrapped = false;
                detectEvent();   
            }

        } else {
            computerPlay = false;
            maxMove = 0;
        }

        idlingP1 = true;
        idlingP2 = true;
        playerID = 0;

        if (computerPlay && !winner) {
            playerID = 2;
            idlingP2 = false;
            if (powerSlot2 != null && gotPower2) {
                computerUseSkill((int) (Math.random() * 3));
                gotPower2 = false;
            }
            diceRoll();
        }
    }

    private int countP1 = 0;
    private int countP2 = 0;

    // MENGGOYANG DADU DAN MENGKONFIGURASI HAL YANG DIPERLUKAN
    public void diceRoll() {
        
        if (P1_IS_INVINCIBLE && countP1 > 2) {
            P1_IS_INVINCIBLE = false;
            countP1 = 0;
        } else countP1++;

        if (P2_IS_INVINCIBLE && countP2 > 2) {
            P2_IS_INVINCIBLE = false;
            countP2 = 0;
        } else countP2++;

        diceRolling = true;
        event.diceRoll();
        move = event.moves;

        if (playerID == 1) {
            gotPower1 = true;
            idlingP1 = true;
            maxMove = PLAYER1_X + (this.event.moves * window.spriteSize);

        } else if (playerID == 2) {
            gotPower2 = true;
            idlingP2 = true;
            maxMove = PLAYER2_X + (this.event.moves * window.spriteSize);
        }

    }

    // MENGECEK PEMAIN MANA YANG MENANG
    public void checkWinner() {

        if (PLAYER1_SCORE >= WIN_SCORE || PLAYER2_SCORE >= WIN_SCORE) {
            winner = true;
            playerID = 0;
            stopPlayer();
            for (int i = 0; i< powerSlot1.length; i ++) {
                powerSlot1[i] = null;
                powerSlot2[i] = null;
            }
        }

    }

    // KONFIGURASI KEYBOARD
    public void useQ() {
        if (idlingP1 && idlingP2 && powerSlot1[0] != null)
            useSkill(0);
    }

    public void useW() {
        if (idlingP1 && idlingP2 && powerSlot1[1] != null)
            useSkill(1);
    }

    public void useE() {
        if (idlingP1 && idlingP2 && powerSlot1[2] != null)
            useSkill(2);
    }

    // MEKANISME PENGUNAAN SKILL PLAYER
    public void useSkill(int i) {

        if (powerSlot1[i] == "BLINK") {
            usingBlinkP1 = true;
            Objects.message = "PLAYER 1 USED BLINK!";

        } else if (powerSlot1[i] == "HOOK") {
            if (PLAYER1_SCORE < PLAYER2_SCORE) {
                PLAYER2_X = PLAYER1_X;
                PLAYER2_SCORE = PLAYER1_SCORE;
                Objects.message = "PLAYER 1 USED HOOK ON PLAYER 2!";
            } else
                Objects.message = "PLAYER 1 FAILED TO USE HOOK!";

        } else if (powerSlot1[i] == "INVINCIBLE") {
            P1_IS_INVINCIBLE = true;
            Objects.message = "PLAYER 1 USED INVINCIBLE!";

        }
        isTrapped = false;
        powerSlot1[i] = null;
    }

    // MEKANISME PENGUNAAN SKILL KOMPUTER
    public void computerUseSkill(int i) {

        if (powerSlot2[i] == "BLINK") {
            usingBlinkP2 = true;
            Objects.message = "PLAYER 2 USED BLINK!";

        } else if (powerSlot2[i] == "HOOK") {
            if (PLAYER2_SCORE < PLAYER1_SCORE) {
                PLAYER1_X = PLAYER2_X;
                PLAYER1_SCORE = PLAYER2_SCORE;
                Objects.message = "PLAYER 2 USED HOOK ON PLAYER 1!";
            } else
                Objects.message = "PLAYER 2 FAILED TO USE HOOK!";

        } else if (powerSlot2[i] == "INVINCIBLE") {
            P2_IS_INVINCIBLE = true;
            Objects.message = "PLAYER 2 USED INVINCIBLE!";
        }
        isTrapped = false;
        powerSlot2[i] = null;
    }
}
