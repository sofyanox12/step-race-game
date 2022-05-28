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
    private final GPanel opt;

    private Boolean idlingP1 = true;
    private Boolean idlingP2 = true;
    
    private int maxMove;

    public Player(GPanel opt, GameEvent event) {

        this.opt = opt;
        this.event = event;

        mapView1_X = (opt.panelWidth - opt.spriteSize) / 2;

        getPlayer(1, 1); // p1
        getPlayer(4, 2); // p2

        setPlayerStartingPos();
        generateTraps();
        
        event.diceRoll();

        maxMove = PLAYER1_X + (event.moves * opt.spriteSize);

    }

    public void setPlayerStartingPos() {
        // Posisi awal Player 1
        PLAYER1_X = 1 * opt.tileSize;
        PLAYER1_Y = opt.panelHeigth - (5 * opt.tileSize);

        // Posisi awal Player 2
        PLAYER2_X = 1 * opt.tileSize;
        PLAYER2_Y = opt.panelHeigth - (5 * opt.tileSize);

    }

    public void updateP1() {
        if (playerID == 1) {
            idlingP1 = false;
            if (event.step1 || event.step2 || event.step3 || event.step4 || event.step5 || event.step6) {

                if (PLAYER1_X < maxMove) {
                    PLAYER1_X += 1;

                } else {
                    event.step1 = false;
                    event.step2 = false;
                    event.step3 = false;
                    event.step4 = false;
                    event.step5 = false;
                    event.step6 = false;
                }

                if (spriteCounterP1 > 12) { // Delay per animasi Karakter
                    spriteNumP1 += (spriteNumP1 < walkP1.length) ? 1 : 0;
                    spriteNumP1 = (spriteNumP1 == walkP1.length) ? 1 : spriteNumP1;
                    spriteCounterP1 = 0;
                }

                spriteCounterP1++;

            } else {
                stopPlayer();
            }
        }

        if (idlingP1) {
            if (spriteCounterP1 > 12) { // Delay per animasi Karakter
                spriteNumP1 += (spriteNumP1 < idleP1.length) ? 1 : 0;
                spriteNumP1 = (spriteNumP1 == idleP1.length) ? 1 : spriteNumP1;
                spriteCounterP1 = 0;
            }
        }
        spriteCounterP1++;
    }

    public void updateP2() {
        if (playerID == 2) {
            idlingP2 = false;
            if (event.step1 || event.step2 || event.step3 || event.step4 || event.step5 || event.step6) {

                if (PLAYER2_X < maxMove) {
                    PLAYER2_X += 1;

                } else {
                    event.step1 = false;
                    event.step2 = false;
                    event.step3 = false;
                    event.step4 = false;
                    event.step5 = false;
                    event.step6 = false;
                }

                if (spriteCounterP2 > 12) { // Delay per animasi Karakter
                    spriteNumP2 += (spriteNumP2 < walkP2.length) ? 1 : 0;
                    spriteNumP2 = (spriteNumP2 == walkP2.length) ? 1 : spriteNumP2;
                    spriteCounterP2 = 0;
                }

                spriteCounterP2++;

            } else {
                stopPlayer();
            }
        }

        if (idlingP2) {
            if (spriteCounterP2 > 12) { // Delay per animasi Karakter
                spriteNumP2 += (spriteNumP2 < idleP2.length) ? 1 : 0;
                spriteNumP2 = (spriteNumP2 == idleP2.length) ? 1 : spriteNumP2;
                spriteCounterP2 = 0;
            }
        }
        spriteCounterP2++;
    }

    

    public void drawP1(GraphicsContext render) {

        Image player1 = null;

        if (idlingP1 && spriteNumP1 < idleP1.length) {
            player1 = idleP1[spriteNumP1 - 1];

        } else if (!idlingP1 && spriteNumP1 < walkP1.length) {
            player1 = walkP1[spriteNumP1 - 1];

        }
        render.drawImage(player1, PLAYER1_X, PLAYER1_Y, opt.spriteSize, opt.spriteSize);
    }

    public void drawP2(GraphicsContext render) {

        Image player2 = null;

        if (!idlingP2) {

            if (spriteNumP2 == 1) {
                player2 = walkP2[0];

            } else if (spriteNumP2 == 2) {
                player2 = walkP2[1];

            } else if (spriteNumP2 == 3) {
                player2 = walkP2[2];

            } else if (spriteNumP2 == 4) {
                player2 = walkP2[3];

            } else if (spriteNumP2 == 5) {
                player2 = walkP2[4];

            } else if (spriteNumP2 == 6) {
                player2 = walkP2[5];

            }

        } else if (idlingP2 && spriteNumP2 < idleP2.length) {

            if (spriteNumP2 == 1) {
                player2 = idleP2[0];

            } else if (spriteNumP2 == 2) {
                player2 = idleP2[1];

            } else if (spriteNumP2 == 3) {
                player2 = idleP2[2];

            } else if (spriteNumP2 == 4) {
                player2 = idleP2[3];

            }
        }

        render.drawImage(player2, PLAYER2_X, PLAYER2_Y, opt.spriteSize, opt.spriteSize);
    }

    public double timePassed() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000;

    }

    public void stopPlayer() {
        PLAYER1_SCORE += event.moves;
        PLAYER2_SCORE += event.moves;
        idlingP1 = true;
        idlingP2 = true;
        playerID = 0;
        System.out.println("\nGiliran : " + playerID);

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
                        this.idleP1[t] = new Image(getClass().getResourceAsStream("mman_idle" + t + ".png"));
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
                        this.idleP2[t] = new Image(getClass().getResourceAsStream("mman_idle" + t + ".png"));
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
