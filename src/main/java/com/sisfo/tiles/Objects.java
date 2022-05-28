package com.sisfo.tiles;

import com.sisfo.sprites.Entity;
import com.sisfo.sprites.Player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Objects {

    public Image dice[] = new Image[6];
    public Image tree[] = new Image[3];
    public Image diceView = null;
    public int dicePosX, dicePosY;

    public Objects() {

        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Image(getClass().getResourceAsStream("objects/d" + (i + 1) + ".png"));
        }
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Image(getClass().getResourceAsStream("objects/tree" + (i + 1) + ".png"));
        }
    }

    public void renderDice(GraphicsContext g, Player p) {

        if (p.move == 1) {
            diceView = dice[0];
        }
        if (p.move == 2) {
            diceView = dice[1];
        }
        if (p.move == 3) {
            diceView = dice[2];
        }
        if (p.move == 4) {
            diceView = dice[3];
        }
        if (p.move == 5) {
            diceView = dice[4];
        }
        if (p.move == 6) {
            diceView = dice[5];
        }

        String dice = (Entity.playerID == 1) ? "PLAYER 1" : "PLAYER 2";
        String turnInfo = (Entity.playerID == 1) ? "Your Turn!\nPress [SPACE] to Roll the Dice."
                : "Computer's Turn.\nPlease be Patient...";
                
        var color = (Entity.playerID == 1) ? Color.DARKGREEN : Color.DARKRED;

        g.setFont(new Font("Comic Sans MS", 12));
        g.fillText(turnInfo, 10, 3 * 48);
        g.fillText("Status :", 10, 3 * 48 - 20);
        g.setFill(color);
        g.fillRect(7 * 48 + 22, 5 * 48 - 2, 52, 52);
        g.fillText(dice, 7 * 48 + 20, 5 * 48 - 5);
        g.drawImage(diceView, 7 * 48 + 24, 5 * 48);

    }

    public void renderPosition(GraphicsContext g) {

        g.setFill(Color.BLACK);
        g.fillText(String.valueOf(Entity.PLAYER1_SCORE), Entity.PLAYER1_X + 10, Entity.PLAYER1_Y - 2);
        g.fillText(String.valueOf(Entity.PLAYER2_SCORE), Entity.PLAYER2_X + 10, Entity.PLAYER2_Y - 2);
    }
}
