package com.sisfo.tiles;

import com.sisfo.sprites.Entity;
import com.sisfo.sprites.Player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/*
    Peran Kelas Objects disini adalah sebagai kelas
    yang mengambil data untuk di baca dan mendeteksi
    kerja data hingga memvisualisasikannya.
*/

public class Objects {

    public static int objectsX = 0;
    public static String message = "(No Event)";

    private Image dice[] = new Image[6];
    private Image tree[] = new Image[3];
    private Image rocks[] = new Image[2];
    private Image misc[] = new Image[2];
    private Image btn[] = new Image[2];
    private Image skill[] = new Image[3];
    private Image slot;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private int duration = 0;

    private Boolean render = true;
    private Boolean randomized = false;
    private int randomPosX_tree[] = new int[30];
    private int random1;
    private Image diceView = null;

    private int player;

    public Objects() {

        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Image(getClass().getResourceAsStream("objects/d" + (i + 1) + ".png"));
        }
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Image(getClass().getResourceAsStream("objects/tree" + (i + 1) + ".png"));
        }
        for (int i = 0; i < rocks.length; i++) {
            rocks[i] = new Image(getClass().getResourceAsStream("objects/rocks" + (i + 1) + ".png"));
            misc[i] = new Image(getClass().getResourceAsStream("objects/misc" + (i + 1) + ".png"));
            btn[i] = new Image(getClass().getResourceAsStream("objects/btn" + (i + 1) + ".png"));
        }
        for (int i = 0; i < skill.length; i++) {
            skill[i] = new Image(getClass().getResourceAsStream("objects/skill" + (i + 1) + ".png"));
        }
        slot = new Image(getClass().getResourceAsStream("objects/" + "slot" + ".png"));

    }

    public void resetDice() {
        spriteCounter = 0;
        spriteNum = 1;
        duration = 0;

        player = (Player.playerID);
    }

    public void renderDice(GraphicsContext g, Player p) {

        if (p.diceRolling) {

            if (spriteCounter > 5) { // Delay per animasi Karakter
                spriteNum += (spriteNum < 6) ? 1 : 0;
                spriteNum = (spriteNum == 6) ? 1 : spriteNum;

                diceView = dice[spriteNum];
                spriteCounter = 0;
                duration++;
            }

            spriteCounter++;

            if (duration > 20) {
                p.diceRolling = false;
                Player.idlingP1 = (Entity.playerID == 1) ? false : Player.idlingP1;
                Player.idlingP2 = (Entity.playerID == 2) ? false : Player.idlingP2;
            }

        } else {
            if (p.move == 1) {
                diceView = dice[0];
                resetDice();
            }
            if (p.move == 2) {
                diceView = dice[1];
                resetDice();
            }
            if (p.move == 3) {
                diceView = dice[2];
                resetDice();
            }
            if (p.move == 4) {
                diceView = dice[3];
                resetDice();
            }
            if (p.move == 5) {
                diceView = dice[4];
                resetDice();
            }
            if (p.move == 6) {
                diceView = dice[5];
                resetDice();
            }
        }

        String dice = (Entity.playerID == 1) ? "PLAYER 1" : "PLAYER 2";
        String turnInfo = (Entity.playerID != 2) ? "Your Turn!\nPress [SPACE] to Roll the Dice."
                : "Computer's Turn.\nPlease be Patient...";

        var color = (player == 1) ? Color.DARKGREEN : Color.DARKRED;
        var diceBackground = (player == 0) ? Color.DARKGREEN : color;

        g.setFont(new Font("Comic Sans MS", 12));
        g.setFill(diceBackground);

        if (!Player.winner) {
            g.fillText(turnInfo, 20, 3 * 48);
            g.fillText("Status :", 20, 3 * 48 - 20);
            g.fillRect(7 * 48 + 22, 5 * 48 - 2, 52, 52);
            g.fillText(dice, 7 * 48 + 20, 5 * 48 - 5);
        }

        g.drawImage(diceView, 7 * 48 + 24, 5 * 48);

    }

    public void renderGUI(GraphicsContext g) {

        g.setFill(Color.BLACK);
        g.fillText("P1: " + String.valueOf(Entity.PLAYER1_SCORE), Entity.PLAYER1_X, Entity.PLAYER1_Y - 4);
        g.fillText("P2: " + String.valueOf(Entity.PLAYER2_SCORE), Entity.PLAYER2_X, Entity.PLAYER2_Y - 4);

        if (Player.winner) {
            String winner = (Player.PLAYER2_SCORE >= 50) ? "PLAYER 2" : "PLAYER 1";
            g.setFont(new Font("Comic Sans MS", 25));
            g.fillText(winner + " WINS!", 6 * 48, 4 * 48);

        } else {
            g.setFill(Color.WHITE);
            for (int i = 0; i <= 50; i++) {
                g.fillText("" + i, (i * 48 + 20) + objectsX, 576 - 1 * 48 - 20);
            }
        }

        if (Player.mapShift > 0 && Entity.playerID == 0) {
            g.drawImage(btn[0], 6 * 48, 5 * 48);
        }
        if ((Player.mapShift < (50 * 48) - (16 * 48)) && Entity.playerID == 0) {
            g.drawImage(btn[1], 9 * 48, 5 * 48);
        }
    }

    public void renderItems(GraphicsContext g) { // Merender tile berwarna untuk perangkap dan powerup
        for (int i = 0; i < Entity.traps.length; i++) {
            g.setFill(Color.rgb(128, 0, 0, 0.5));
            g.fillRect(Entity.traps[i] * 48 + objectsX + 1, 576 - 2 * 48 + 1, 46, 46);
        }

        for (int i = 0; i < Entity.powerUps.length; i++) {
            g.setFill(Color.rgb(0, 0, 255, 0.5));
            g.fillRect(Entity.powerUps[i] * 48 + objectsX + 1, 576 - 2 * 48 + 1, 46, 46);
        }

    }

    public void renderPlayerUI(GraphicsContext g) {

        g.drawImage(slot, 20, 20, 240, 68);

        for (int i = 0, t = 48; i < skill.length; i++, t += 68) {

            if (Entity.powerSlot1[i] == "BLINK")
                g.drawImage(skill[0], t, 31, 44, 46);
            else if (Entity.powerSlot1[i] == "HOOK")
                g.drawImage(skill[1], t, 31, 44, 46);
            else if (Entity.powerSlot1[i] == "INVINCIBLE")
                g.drawImage(skill[2], t, 31, 44, 46);
        }

        g.setFont(new Font("System", 14));
        g.fillText("[" + "Q" + "]", 1 * 68 - 8, 55);
        g.fillText("[" + "W" + "]", 2 * 68 - 8, 55);
        g.fillText("[" + "E" + "]", 3 * 68 - 4, 55);

        if (!Player.winner)
            g.fillText("EVENT LOG : " + message, 20, 4 * 48);

    }

    public void renderMapObject(GraphicsContext g) { // Merender object seperti pohon dkk
        g.drawImage(misc[1], (2 * 48) + objectsX, 576 - 5 * 48);

        if (render) {
            for (int i = 0; i < randomPosX_tree.length; i++) {
                randomPosX_tree[i] = ((int) (Math.random() * 45 + 5)) * 48;
            }

            render = false;
        }

        if (!randomized) {
            random1 = (int) (Math.random() * 3 + 1);
            randomized = true;

        }

        for (int i = 0; i < randomPosX_tree.length - 20; i++) {
            if (random1 == 1) {
                g.drawImage(tree[0], randomPosX_tree[i] + objectsX, 576 - 5 * 48);
            } else if (random1 == 2) {
                g.drawImage(tree[1], randomPosX_tree[i] + objectsX, 576 - 5 * 48);
            } else if (random1 == 3) {
                g.drawImage(tree[2], randomPosX_tree[i] + objectsX, 576 - 5 * 48);
            }
        }

        for (int i = 10; i < randomPosX_tree.length - 10; i++) {
            if (random1 == 1) {
                g.drawImage(tree[1], randomPosX_tree[i] + objectsX, 576 - 5 * 48);
            } else if (random1 == 2) {
                g.drawImage(tree[2], randomPosX_tree[i] + objectsX, 576 - 5 * 48);
            } else if (random1 == 3) {
                g.drawImage(tree[0], randomPosX_tree[i] + objectsX, 576 - 5 * 48);
            }
        }

        for (int i = 20; i < randomPosX_tree.length; i++) {
            if (random1 == 1) {
                g.drawImage(tree[2], randomPosX_tree[i] + objectsX, 576 - 5 * 48);
            } else if (random1 == 2) {
                g.drawImage(tree[0], randomPosX_tree[i] + objectsX, 576 - 5 * 48);
            } else if (random1 == 3) {
                g.drawImage(tree[1], randomPosX_tree[i] + objectsX, 576 - 5 * 48);
            }
        }

    }
}
