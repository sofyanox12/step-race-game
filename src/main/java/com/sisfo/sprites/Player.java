package com.sisfo.sprites;

import com.sisfo.GPanel;
import com.sisfo.GameEvent;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Entity {

    public final int mapView1_X;

    private final Image[] walkP1 = new Image[6];
    private final Image[] idleP1 = new Image[4];

    private final Image[] walkP2 = new Image[6];
    private final Image[] idleP2 = new Image[4];

    private final GameEvent event;
    private final GPanel window;

    private Boolean idlingP1 = false;
    private Boolean idlingP2 = true;

    public Boolean computerPlay = false;

    public int move, maxMove;

    public Player(GPanel window, GameEvent event) {

        this.window = window;
        this.event = event;

        mapView1_X = (window.panelWidth - window.spriteSize) / 2;

        event.diceRoll();

        setPlayerStartingPos(window);
        generateTraps();
        getPlayer(3, 1); // p1
        getPlayer(4, 2); // p2

        this.move = event.moves;

        maxMove = PLAYER1_X + (this.move * window.spriteSize);

    }

    // .. Akhirnya jadi juga frame updaternya wkwkwk

    public void updateP1() {

        if (!idlingP1) {

            if (PLAYER1_X < maxMove) {

                PLAYER1_X++;

                if (spriteCounterP1 > 5 && !idlingP1) { // Delay per animasi Karakter
                    spriteNumP1 += (spriteNumP1 < walkP1.length) ? 1 : 0;
                    spriteNumP1 = (spriteNumP1 == walkP1.length) ? 1 : spriteNumP1;
                    spriteCounterP1 = 0;
                }
                spriteCounterP1++;

            } else {
                spriteNumP1 --;
                stopPlayer();
            }
        }

        else  {
            if (spriteCounterP1 > 5) { // Delay per animasi Karakter
                spriteNumP1 += (spriteNumP1 < idleP1.length) ? 1 : 0;
                spriteNumP1 = (spriteNumP1 == idleP1.length) ? 1 : spriteNumP1;
                spriteCounterP1 = 0;
            }
            spriteCounterP1++;
        }

        System.out.println("\tPlayer1 ::> " + spriteNumP1 + " " + idlingP1);

    }

    public void updateP2() {

        if (!idlingP2) {

            if (PLAYER1_X < maxMove) {

                PLAYER1_X++;

                if (spriteCounterP2 > 5 && !idlingP2) { // Delay per animasi Karakter
                    spriteNumP2 += (spriteNumP2 < walkP2.length) ? 1 : 0;
                    spriteNumP2 = (spriteNumP2 == walkP2.length) ? 1 : spriteNumP2;
                    spriteCounterP2 = 0;
                }
                spriteCounterP2++;

            } else {
                spriteNumP2 --;
                stopPlayer();
            }
        }

        else  {
            if (spriteCounterP2 > 5) { // Delay per animasi Karakter
                spriteNumP2 += (spriteNumP2 < idleP2.length) ? 1 : 0;
                spriteNumP2 = (spriteNumP2 == idleP2.length) ? 1 : spriteNumP2;
                spriteCounterP2 = 0;
            }
            spriteCounterP2++;
        }


        System.out.print("\nPlayer2 ::> " + spriteNumP2 + " " + idlingP2);
    }

    public void drawP1(GraphicsContext render) {

        Image player1 = null;

        if (idlingP1 && spriteNumP1 < idleP1.length) {
            player1 = idleP1[spriteNumP1 - 1];

        } else if (!idlingP1 && spriteNumP1 < walkP1.length) {
            player1 = walkP1[spriteNumP1 - 1];

        }
        render.drawImage(player1, PLAYER1_X, PLAYER1_Y, window.spriteSize, window.spriteSize);
    }

    public void drawP2(GraphicsContext render) {

        Image player2 = null;

        if (idlingP2 && spriteNumP2 < idleP2.length) {
            player2 = idleP2[spriteNumP2 - 1];

        } else if (!idlingP2 && spriteNumP2 < walkP2.length) {
            player2 = walkP2[spriteNumP2 - 1];

        }

        render.drawImage(player2, PLAYER2_X, PLAYER2_Y, window.spriteSize, window.spriteSize);
    }

    public void stopPlayer() {
        computerPlay = (computerPlay) ? false : true;
        PLAYER1_SCORE += (playerID == 1) ? event.moves : 0;
        PLAYER2_SCORE += (playerID == 2) ? event.moves : 0;
        idlingP1 = true;
        idlingP2 = true;
        playerID = 0;

        // Pesan :
        System.out.println("\nGiliran : " + playerID);
        System.out.println("Score Player 1 : " + PLAYER1_SCORE);
        System.out.println("Score Player 2 : " + PLAYER2_SCORE);

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
