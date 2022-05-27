package com.entity;

import com.sisfo.GPanel;
import com.sisfo.GameEvent;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {

    public int PLAYER1_X, PLAYER1_Y, PLAYER2_X, PLAYER2_Y;

    public final int mapView1_X;

    public long start = System.currentTimeMillis();

    public int spriteCounterP1 = 0;
    public int spriteNumP1 = 1;

    public int spriteCounterP2 = 0;
    public int spriteNumP2 = 1;

    public int playerID;

    private Image[] walkP1 = new Image[6];
    private Image[] idleP1 = new Image[4];

    private Image[] walkP2 = new Image[6];
    private Image[] idleP2 = new Image[4];

    private Boolean idlingP1 = false;
    private Boolean idlingP2 = false;

    private GameEvent event;
    private GPanel opt;

    public Player(GPanel opt, GameEvent event) {
        this.opt = opt;
        this.event = event;

        mapView1_X = (opt.panelWidth - opt.spriteSize) / 2;

        getPlayer(1, 1); // p1
        getPlayer(4, 2); // p2
        setPlayerStartingPos();
        event.diceRoll();
    }

    public void setPlayerStartingPos() {
        // Posisi awal Player 1
        PLAYER1_X = 1 * opt.tileSize;
        PLAYER1_Y = opt.panelHeigth - (5 * opt.tileSize);

        // Posisi awal Player 2
        PLAYER2_X = 1 * opt.tileSize;
        PLAYER2_Y = opt.panelHeigth - (5 * opt.tileSize);

    }

    public int getPlayerID() {
        return playerID;
    }

    public void update() {

        double limit = timePassed();

        if (!event.back1 || !event.back2 || !event.back3 || !event.back4 || !event.back5 || !event.back6) {

            if (event.step1 == true) {

                PLAYER1_X += playerID == 1 ? 0 : 1;
                PLAYER2_X += playerID == 2 ? 0 : 2;

                event.step1 = (limit == 1) ? false : true;

            } else if (event.step2 == true) {

                PLAYER1_X += playerID == 1 ? 0 : 2;
                PLAYER2_X += playerID == 2 ? 0 : 1;

                event.step2 = (limit == 2) ? false : true;

            } else if (event.step3 == true) {

                PLAYER1_X += playerID == 1 ? 0 : 2;
                PLAYER2_X += playerID == 2 ? 0 : 1;

                event.step3 = (limit == 3) ? false : true;

            } else if (event.step4 == true) {

                PLAYER1_X += playerID == 1 ? 0 : 1;
                PLAYER2_X += playerID == 2 ? 0 : 2;

                event.step4 = (limit == 3) ? false : true;

            } else if (event.step5 == true) {

                PLAYER1_X += playerID == 1 ? 0 : 2;
                PLAYER2_X += playerID == 2 ? 0 : 1;

                event.step5 = (limit == 4) ? false : true;

            } else if (event.step6 == true) {

                PLAYER1_X += playerID == 1 ? 0 : 2;
                PLAYER2_X += playerID == 2 ? 0 : 1;

                event.step6 = (limit == 4) ? false : true;

            } else {
                idlingP1 = true;
                idlingP2 = true;
            }

            spriteCounterP1++;
            spriteCounterP2++;

            if (spriteCounterP1 > 12) { // Delay per animasi Karakter

                if (!idlingP1) {
                    if (spriteNumP1 < walkP1.length) {
                        spriteNumP1++;
                    } else if (spriteNumP1 == walkP1.length) {
                        spriteNumP1 = 1;
                    }

                } else if (idlingP1) {
                    if (spriteNumP1 < idleP1.length) {
                        spriteNumP1++;
                    } else if (spriteNumP1 == idleP1.length) {
                        spriteNumP1 = 1;
                    }
                }
                spriteCounterP1 = 0;
            }

            if (spriteCounterP2 > 12) { // Delay per animasi Karakter

                if (!idlingP2) {
                    if (spriteNumP2 < walkP2.length) {
                        spriteNumP2++;

                    } else if (spriteNumP2 == walkP2.length) {
                        spriteNumP2 = 1;
                    }

                } else if (idlingP2) {
                    if (spriteNumP2 < idleP2.length) {
                        spriteNumP2++;

                    } else if (spriteNumP2 == idleP2.length) {
                        spriteNumP2 = 1;
                          
                    }
                }
                spriteCounterP2 = 0;
            }
            System.out.println("[P1] : " + spriteCounterP1 + " " + spriteNumP1);
            System.out.println("[P2] : " + spriteCounterP2 + " " + spriteNumP2);

        }

    }

    public void getPlayer(int x, int playerID) {

        if (playerID == 1) { // Karakter untuk Player 1

            if (x == 1) { // Character : Old Man
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    this.walkP1[i] = new Image("file:src/main/resources/com/sprites/old_walk" + i + ".png");
                    if (t < idleP1.length)
                        this.idleP1[t] = new Image("file:src/main/resources/com/sprites/old_idle" + t + ".png");
                }
    
            } else if (x == 2) { // Character : Girl
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    this.walkP1[i] = new Image("file:src/main/resources/com/sprites/girl_walk" + i + ".png");
                    if (t < idleP1.length)
                        this.idleP1[t] = new Image("file:src/main/resources/com/sprites/girl_idle" + t + ".png");
                }
    
            } else if (x == 3) { // Character : Man
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) {
                    this.walkP1[i] = new Image("file:src/main/resources/com/sprites/man_walk" + i + ".png");
                    if (t < idleP1.length)
                        this.idleP1[t] = new Image("file:src/main/resources/com/sprites/mman_idle" + t + ".png");
                }
    
            } else if (x == 4) {
                for (int i = 0, t = 0; i < walkP1.length; i++, t++) { // Character : punk
                    this.walkP1[i] = new Image("file:src/main/resources/com/sprites/punk_walk" + i + ".png");
                    if (t < idleP1.length)
                        this.idleP1[t] = new Image("file:src/main/resources/com/sprites/punk_idle" + t + ".png");
                }
    
            }

        } else if (playerID == 2) { // Karakter untuk Player 2

            if (x == 1) { // Character : Old Man
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    this.walkP2[i] = new Image("file:src/main/resources/com/sprites/old_walk" + i + ".png");
                    if (t < idleP2.length)
                        this.idleP2[t] = new Image("file:src/main/resources/com/sprites/old_idle" + t + ".png");
                }
    
            } else if (x == 2) { // Character : Girl
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    this.walkP2[i] = new Image("file:src/main/resources/com/sprites/girl_walk" + i + ".png");
                    if (t < idleP2.length)
                        this.idleP2[t] = new Image("file:src/main/resources/com/sprites/girl_idle" + t + ".png");
                }
    
            } else if (x == 3) { // Character : Man
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) {
                    this.walkP2[i] = new Image("file:src/main/resources/com/sprites/man_walk" + i + ".png");
                    if (t < idleP2.length)
                        this.idleP2[t] = new Image("file:src/main/resources/com/sprites/mman_idle" + t + ".png");
                }
    
            } else if (x == 4) {
                for (int i = 0, t = 0; i < walkP2.length; i++, t++) { // Character : punk
                    this.walkP2[i] = new Image("file:src/main/resources/com/sprites/punk_walk" + i + ".png");
                    if (t < idleP2.length)
                        this.idleP2[t] = new Image("file:src/main/resources/com/sprites/punk_idle" + t + ".png");
                }
    
            } else {
                System.out.println("Invalid Player ID!");
            }
        }
        
    }

    public void drawP1(GraphicsContext render) {

        Image player1 = null;

        if (!idlingP1) {

            if (spriteNumP1 == 1) {
                player1 = walkP1[0];

            } else if (spriteNumP1 == 2) {
                player1 = walkP1[1];

            } else if (spriteNumP1 == 3) {
                player1 = walkP1[2];

            } else if (spriteNumP1 == 4) {
                player1 = walkP1[3];

            } else if (spriteNumP1 == 5) {
                player1 = walkP1[4];

            } else if (spriteNumP1 == 6) {
                player1 = walkP1[5];

            }

        } else if (idlingP1) {

            if (spriteNumP1 == 1) {
                player1 = idleP1[0];

            } else if (spriteNumP1 == 2) {
                player1 = idleP1[1];

            } else if (spriteNumP1 == 3) {
                player1 = idleP1[2];

            } else if (spriteNumP1 == 4) {
                player1 = idleP1[3];

            }
        }

        render.drawImage(player1, PLAYER1_X, PLAYER1_Y, opt.spriteSize, opt.spriteSize);
    }

    public void drawP2(GraphicsContext render) {

        Image player2 = null;

        if (!idlingP2) {

            if (spriteNumP1 == 1) {
                player2 = walkP2[0];

            } else if (spriteNumP1 == 2) {
                player2 = walkP2[1];

            } else if (spriteNumP1 == 3) {
                player2 = walkP2[2];

            } else if (spriteNumP1 == 4) {
                player2 = walkP2[3];

            } else if (spriteNumP1 == 5) {
                player2 = walkP2[4];

            } else if (spriteNumP1 == 6) {
                player2 = walkP2[5];

            }

        } else if (idlingP2) {

            if (spriteNumP1 == 1) {
                player2 = idleP2[0];

            } else if (spriteNumP1 == 2) {
                player2 = idleP2[1];

            } else if (spriteNumP1 == 3) {
                player2 = idleP2[2];

            } else if (spriteNumP1 == 4) {
                player2 = idleP2[3];

            }
        }

        render.drawImage(player2, PLAYER2_X, PLAYER2_Y, opt.spriteSize, opt.spriteSize);
    }

    public double timePassed() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000;

    }

}
