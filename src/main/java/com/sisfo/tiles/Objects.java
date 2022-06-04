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
    public static Boolean showSpaceBar = true;

    private Font fontStatus, fontWinner;
    private Image dice[] = new Image[6];
    private Image tree[] = new Image[3];
    private Image rocks[] = new Image[2];
    private Image misc[] = new Image[2];
    private Image btn[] = new Image[2];
    private Image skill[] = new Image[3];
    private Image slot, spacebar, grass;
    private Image diceView = null;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private int duration = 0;

    private int tree1PosX[] = { 6, 7, 9, 11, 14, 19, 20, 30, 31, 37, 42, 48 };
    private int tree2PosX[] = { 5, 8, 13, 18, 22, 23, 29, 32, 38, 39, 45 };
    private int tree3PosX[] = { 4, 10, 15, 17, 21, 26, 34, 35, 36, 41, 44 };

    private int rocks1PosX[] = { 3, 11, 12, 23, 29, 32, 37 };
    private int rocks2PosX[] = { 4, 8, 15, 26, 35, 43, 46 };

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

        grass = new Image(getClass().getResourceAsStream("objects/grass.png"));
        slot = new Image(getClass().getResourceAsStream("objects/" + "slot" + ".png"));
        fontStatus = Font.loadFont(getClass().getResourceAsStream("fonts/sisfo.ttf"), 18);
        fontWinner = Font.loadFont(getClass().getResourceAsStream("fonts/sisfo.ttf"), 35);
        spacebar = new Image(getClass().getResourceAsStream("objects/spacebar.png"));

        for (int i = 0; i < tree1PosX.length; i++)
            tree1PosX[i] *= 48;

        for (int i = 0; i < tree2PosX.length; i++)
            tree2PosX[i] *= 48;

        for (int i = 0; i < tree3PosX.length; i++)
            tree3PosX[i] *= 48;

        for (int i = 0; i < rocks1PosX.length; i++)
            rocks1PosX[i] *= 48;

        for (int i = 0; i < rocks2PosX.length; i++)
            rocks2PosX[i] *= 48;
    }

    public void resetDice() {
        spriteCounter = 0;
        spriteNum = 1;
        duration = 0;
    }

    public void renderDice(GraphicsContext g, Player p) { // ME-RENDER DADU & ANIMASINYA

        if (p.diceRolling) {
            showSpaceBar = false;
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

        g.setFont(fontStatus);

        String dice = (Entity.playerID != 2) ? "PLAYER 1" : "PLAYER 2";
        String turnInfo = (Entity.playerID != 2) ? "Your Turn!\nPress [SPACE] to Roll the Dice."
                : "Computer's Turn.\nPlease be Patient...";

        var color = (Entity.playerID != 2) ? Color.DARKGREEN : Color.DARKRED;

        if (!Player.winner && Entity.playerID != 2 && showSpaceBar) {
            g.drawImage(spacebar, 7 * 48 + 5, 9 * 48 - 10);
            g.setFill(Color.BLACK);
            g.fillText("SPACE", 7 * 48 + 32, 9 * 48 + 20);
        }
        if (!Player.winner) {
            g.setFill(Color.BLACK);
            g.fillText(turnInfo, 20, 3 * 48);
            g.fillText("Status :", 20, 3 * 48 - 20);
            g.fillRect(7 * 48 + 22, 5 * 48 - 2, 52, 52);
            g.setFill(color);
            g.fillText(dice, 7 * 48 + 20, 5 * 48 - 5);
        }
        g.drawImage(diceView, 7 * 48 + 24, 5 * 48);
    }

    public void renderGUI(GraphicsContext g) { // MERENDER GUI

        g.setFill(Color.BLACK);
        g.fillText("P1: " + String.valueOf(Entity.PLAYER1_SCORE), Entity.PLAYER1_X, Entity.PLAYER1_Y - 4);
        g.fillText("P2: " + String.valueOf(Entity.PLAYER2_SCORE), Entity.PLAYER2_X, Entity.PLAYER2_Y - 4);

        if (Player.winner) {
            g.setFont(fontWinner);
            String winner = (Player.PLAYER2_SCORE >= 50) ? "PLAYER 2" : "PLAYER 1";
            g.fillText(winner + " WINS!", 6 * 48, 4 * 48);

        } else {
            g.setFill(Color.WHITE);
            for (int i = 0; i <= 50; i++)
                g.fillText("" + i, (i * 48 + 20) + objectsX, 576 - 1 * 48 - 20);
        }

        if (Player.mapShift > 0 && Entity.playerID == 0)
            g.drawImage(btn[0], 6 * 48, 5 * 48);

        if ((Player.mapShift < (50 * 48) - (16 * 48)) && Entity.playerID == 0)
            g.drawImage(btn[1], 9 * 48, 5 * 48);
    }

    public void renderItems(GraphicsContext g) { // MENGGAMBAR TILE YANG BERWARNA
        for (int i = 0; i < Entity.traps.length; i++) {
            g.setFill(Color.rgb(128, 0, 0, 0.5));
            g.fillRect(Entity.traps[i] * 48 + objectsX + 1, 576 - 2 * 48 + 1, 46, 46);
        }

        for (int i = 0; i < Entity.powerUps.length; i++) {
            g.setFill(Color.rgb(0, 0, 255, 0.5));
            g.fillRect(Entity.powerUps[i] * 48 + objectsX + 1, 576 - 2 * 48 + 1, 46, 46);
        }
        g.setFill(Color.rgb(128, 128, 128, 0.5));
        g.fillRect(objectsX + 1, 576 - 2 * 48 + 1, 46, 46);
        g.fillRect(50 * 48 + objectsX + 1, 576 - 2 * 48 + 1, 46, 46);

    }

    public void renderPlayerUI(GraphicsContext g) { // ME-RENDER TAMPILAN UI PLAYER

        g.drawImage(slot, 20, 20, 240, 68);

        for (int i = 0, t = 48; i < skill.length; i++, t += 68) {

            if (Entity.powerSlot1[i] == "BLINK")
                g.drawImage(skill[0], t, 31, 44, 46);
            else if (Entity.powerSlot1[i] == "HOOK")
                g.drawImage(skill[1], t, 31, 44, 46);
            else if (Entity.powerSlot1[i] == "INVINCIBLE")
                g.drawImage(skill[2], t, 31, 44, 46);
        }

        g.setFont(fontStatus);
        g.fillText("[" + "Q" + "]", 1 * 68 - 8, 55);
        g.fillText("[" + "W" + "]", 2 * 68 - 8, 55);
        g.fillText("[" + "E" + "]", 3 * 68 - 4, 55);

        if (!Player.winner)
            g.fillText("EVENT LOG : " + message, 20, 4 * 48);

    }

    public void renderMapObject(GraphicsContext g) { // ME-RENDER OBJEK" DI MAP

        g.drawImage(misc[1], (2 * 48) + objectsX, 576 - 5 * 48);

        g.drawImage(misc[1], (49 * 48) + objectsX, 576 - 5 * 48);

        for (int i = 0; i < tree1PosX.length; i++)
            g.drawImage(tree[0], tree1PosX[i] + objectsX, 576 - 5 * 48);

        for (int i = 0; i < tree2PosX.length; i++)
            g.drawImage(tree[1], tree2PosX[i] + objectsX, 576 - 5 * 48);

        for (int i = 0; i < tree3PosX.length; i++)
            g.drawImage(tree[2], tree3PosX[i] + objectsX, 576 - 5 * 48);

        for (int i = 0; i < rocks1PosX.length; i++)
            g.drawImage(rocks[0], rocks1PosX[i] + objectsX, 576 - 5 * 48);

        for (int i = 0; i < rocks2PosX.length; i++)
            g.drawImage(rocks[1], rocks2PosX[i] + objectsX, 576 - 5 * 48);

        for (int i = 0; i < 150; i++) {
            g.drawImage(grass, i * 20 + objectsX, 576 - (4 * 48 + 18), 20, 32);
        }
    }

    public void renderAnimation(GraphicsContext g) {

    }
}
