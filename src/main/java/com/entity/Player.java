package com.entity;

import com.sisfo.GPanel;
import com.sisfo.GameEvent;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Player {

    public int PLAYER1_X, PLAYER1_Y, PLAYER2_X, PLAYER2_Y;

    public long start = System.currentTimeMillis();
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int playerID = 1;

    private Image[] walk = new Image[6];
    private Image[] idle = new Image[4];

    private Boolean idling = false;

    GameEvent dice;
    GPanel opt;

    public Player(GPanel opt, GameEvent dice) {
        this.opt = opt;
        this.dice = dice;
        getPlayer(1); // set player (nanti manipulasikan lagi player1 dan player2 sendiri-sendiri) ############################################
        setPlayer1Pos();
        setPlayer2Pos();
        dice.diceRoll();
    }

    public void setPlayer1Pos() {
        PLAYER1_X = opt.panelWidth - (15 * opt.tileSize);
        PLAYER1_Y = opt.panelHeigth - (5 * opt.tileSize);

    }

    public void setPlayer2Pos() {
        PLAYER2_X = opt.panelWidth - (15 * opt.tileSize);
        PLAYER2_Y = opt.panelHeigth - (5 * opt.tileSize);
    }

    public void update() {

        double limit = timePassed();

        if (!dice.back1 || !dice.back2 || !dice.back3 || !dice.back4 || !dice.back5 || !dice.back6) {

            if (dice.step1 == true) {

                PLAYER1_X += playerID < 1 ? 0 : 1;
                PLAYER2_X += playerID > 1 ? 0 : 2;

                dice.step1 = (limit == 2) ? false : true;

            } else if (dice.step2 == true) {

                PLAYER1_X += playerID < 1 ? 0 : 2;
                PLAYER2_X += playerID > 1 ? 0 : 1;

                dice.step2 = (limit == 2) ? false : true;

            } else if (dice.step3 == true) {

                PLAYER1_X += playerID < 1 ? 0 : 2;
                PLAYER2_X += playerID > 1 ? 0 : 1;

                dice.step3 = (limit == 3) ? false : true;

            } else if (dice.step4 == true) {

                PLAYER1_X += playerID < 1 ? 0 : 1;
                PLAYER2_X += playerID > 1 ? 0 : 2;

                dice.step4 = (limit == 3) ? false : true;

            } else if (dice.step5 == true) {

                PLAYER1_X += playerID < 1 ? 0 : 2;
                PLAYER2_X += playerID > 1 ? 0 : 1;

                dice.step5 = (limit == 4) ? false : true;

            } else if (dice.step6 == true) {

                PLAYER1_X += playerID < 1 ? 0 : 2;
                PLAYER2_X += playerID > 1 ? 0 : 1;

                dice.step6 = (limit == 4) ? false : true;

            } else {
                idling = true;
            }

            spriteCounter++;

            if (spriteCounter > 12) { // Delay per animasi Karakter

                if (!idling) {
                    if (spriteNum < walk.length) {
                        spriteNum++;
                    } else if (spriteNum == walk.length) {
                        spriteNum = 1;
                    }

                } else if (idling) {
                    if (spriteNum < idle.length) {
                        spriteNum++;
                    } else if (spriteNum == idle.length) {
                        spriteNum = 1;
                    }
                }
                spriteCounter = 0;
            }
            System.out.println(spriteCounter + " " + spriteNum);
        }

    }

    public void getPlayer(int x) {

        if (x == 1) { // Character : Old Man
            for (int i = 0, t = 0; i < walk.length; i++, t++) {
                this.walk[i] = new Image("file:src/main/resources/com/sprites/old_walk" + i + ".png");
                if (t < idle.length)
                    this.idle[t] = new Image("file:src/main/resources/com/sprites/old_idle" + t + ".png");
            }

        } else if (x == 2) { // Character : Girl
            for (int i = 0, t = 0; i < walk.length; i++, t++) {
                this.walk[i] = new Image("file:src/main/resources/com/sprites/girl_walk" + i + ".png");
                if (t < idle.length)
                    this.idle[t] = new Image("file:src/main/resources/com/sprites/old_idle" + t + ".png");
            }

        } else if (x == 3) { // Character : Man
            for (int i = 0, t = 0; i < walk.length; i++, t++) {
                this.walk[i] = new Image("file:src/main/resources/com/sprites/man_walk" + i + ".png");
                if (t < idle.length)
                    this.idle[t] = new Image("file:src/main/resources/com/sprites/old_idle" + t + ".png");
            }

        } else if (x == 4) {
            for (int i = 0, t = 0; i < walk.length; i++, t++) { // Character : punk
                this.walk[i] = new Image("file:src/main/resources/com/sprites/punk_walk" + i + ".png");
                if (t < idle.length)
                    this.idle[t] = new Image("file:src/main/resources/com/sprites/old_idle" + t + ".png");
            }

        }
    }

    public void drawP1(GraphicsContext render) {

        Image player1 = null;

        if (!idling) {

            if (spriteNum == 1) {
                player1 = walk[0];

            } else if (spriteNum == 2) {
                player1 = walk[1];

            } else if (spriteNum == 3) {
                player1 = walk[2];

            } else if (spriteNum == 4) {
                player1 = walk[3];

            } else if (spriteNum == 5) {
                player1 = walk[4];

            } else if (spriteNum == 6) {
                player1 = walk[5];

            }

        } else if (idling) {

            if (spriteNum == 1) {
                player1 = idle[0];

            } else if (spriteNum == 2) {
                player1 = idle[1];

            } else if (spriteNum == 3) {
                player1 = idle[2];

            } else if (spriteNum == 4) {
                player1 = idle[3];

            }
        }

        render.drawImage(player1, PLAYER1_X, PLAYER1_Y, opt.spriteSize, opt.spriteSize);
    }

    public void drawP2(GraphicsContext render) {

        Image player2 = null;

        if (!idling) {

            if (spriteNum == 1) {
                player2 = walk[0];

            } else if (spriteNum == 2) {
                player2 = walk[1];

            } else if (spriteNum == 3) {
                player2 = walk[2];

            } else if (spriteNum == 4) {
                player2 = walk[3];

            } else if (spriteNum == 5) {
                player2 = walk[4];

            } else if (spriteNum == 6) {
                player2 = walk[5];

            }

        } else if (idling) {

            if (spriteNum == 1) {
                player2 = idle[0];

            } else if (spriteNum == 2) {
                player2 = idle[1];

            } else if (spriteNum == 3) {
                player2 = idle[2];

            } else if (spriteNum == 4) {
                player2 = idle[3];

            }
        }

        render.drawImage(player2, PLAYER2_X, PLAYER2_Y, opt.spriteSize, opt.spriteSize);
    }

    public double timePassed() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000;

    }

}
