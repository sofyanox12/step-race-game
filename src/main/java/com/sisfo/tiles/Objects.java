package com.sisfo.tiles;

import com.sisfo.sprites.Entity;
import com.sisfo.sprites.Player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Objects {

    private Image dice[] = new Image[6];
    private Image tree[] = new Image[3];
    private Image rocks[] = new Image[2];
    private Image misc[] = new Image[2];
    private Image btn[] = new Image[2];

    public Image diceView = null;

    public int objectsX = 0;

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
    }

    private int spriteCounter = 0;
    private int spriteNum = 1;
    private int duration = 0;

    public void resetDice() {
        spriteCounter = 0;
        spriteNum = 1;
        duration = 0;
        
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

        } else  {

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

        var color = (Entity.playerID == 1) ? Color.DARKGREEN : Color.DARKRED;
        var diceBackground = (Player.moves == 0) ? Color.GRAY : color;

        g.setFont(new Font("Comic Sans MS", 12));

        g.fillText(turnInfo, 10, 3 * 48);
        g.fillText("Status :", 10, 3 * 48 - 20);
        g.setFill(diceBackground);
        g.fillRect(7 * 48 + 22, 5 * 48 - 2, 52, 52);
        g.fillText(dice, 7 * 48 + 20, 5 * 48 - 5);
        g.drawImage(diceView, 7 * 48 + 24, 5 * 48);

    }

    public void renderGUI(GraphicsContext g) {

        g.setFill(Color.BLACK);
        g.fillText("P1: " + String.valueOf(Entity.PLAYER1_SCORE), Entity.PLAYER1_X, Entity.PLAYER1_Y - 4);
        g.fillText("P2: " + String.valueOf(Entity.PLAYER2_SCORE), Entity.PLAYER2_X, Entity.PLAYER2_Y - 4);

        if (Player.winner) {

            String winner = (Player.playerID == 1) ? "PLAYER 1" : "PLAYER 2";
            g.setFont(new Font("Comic Sans MS", 25));
            g.fillText(winner + " WINS!", 6 * 48, 4 * 48);
        }

        if (Player.mapShift > 0 && Entity.playerID == 0) {
            g.drawImage(btn[0], 6 * 48 , 5 * 48);
        }
        if ((Player.mapShift < (50 * 48) - (16 * 48)) && Entity.playerID == 0){
            g.drawImage(btn[1], 9 * 48 , 5 * 48);
        }
        
    }

    public Boolean render = true;
    public Boolean randomized = false;
    public int randomPosX_tree[] = new int[30];
    public int random1;

    public void renderMapObject(GraphicsContext g) {
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
