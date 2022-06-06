package com.sisfo;

import java.util.Random;

import com.sisfo.sprites.Entity;
import com.sisfo.sprites.Player;
import com.sisfo.tiles.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/*
    GameEvent adalah Kelas Kecil yang berguna
    untuk memengaruh angka dadu dan animasi
    saat terjadi beberapa hal.
*/

public class GameEvent {

    public int moves = 0;

    public Boolean wasTrappedP1 = false;
    public Boolean wasTrappedP2 = false;
    private Image[] trapped = new Image[28];

    private int animationCounter = 0;
    private int animationNum = 1;
    private int posX;
    public Boolean animate = false;
    private Font fontStatus = Font.loadFont(getClass().getResourceAsStream("fonts/sisfo.ttf"), 18);
    private Random random = new Random();

    public GameEvent() {

        for (int i = 0; i < trapped.length; i++) {
            trapped[i] = new Image(getClass().getResourceAsStream("effects/1_" + i + ".png"));
        }
    }

    public void diceRoll() {
        moves = random.nextInt((6 - 1) + 1) + 1;
        Player.moves++;
    }

    public void backToMainMenu() {
        Player.winner = false;
        Player.idlingP1 = true;
        Player.idlingP2 = true;
        Player.PLAYER1_X = 12;
        Player.PLAYER2_X = 12;
        Player.PLAYER1_SCORE = 0;
        Player.PLAYER2_SCORE = 0;
        Player.mapShift = 0;
        Player.moves = 0;
        Objects.message = "(No Event)";

        for (int i = 0; i < 3; i++) {
            Player.powerSlot1[i] = null;
            Player.powerSlot2[i] = null;
        }
    }

    public void drawAnimation(GraphicsContext g) {
        Image onTrap = null;

        if (animationNum < trapped.length && animate) {
            animationNum = (animationNum < 1) ? 1 : animationNum;
            onTrap = trapped[animationNum - 1];
            g.drawImage(onTrap, posX, Player.PLAYER1_Y, 52, 52);
        }
    }

    public void detectTrap() {

        if (moves != 6) {
            for (int i = 0; i < Entity.traps.length; i++) {
                if (Player.PLAYER1_SCORE == Entity.traps[i] && !Player.P1_IS_INVINCIBLE) {

                    if (!Player.isTrapped) {
                        Objects.message = informEvent(1);
                        Player.PLAYER1_SCORE -= 3;
                        Player.PLAYER1_X -= 3 * 48;
                        posX = Player.PLAYER1_X - 12;
                        resetCounter();
                        Player.isTrapped = true;
                    }
                }
                if (Player.PLAYER2_SCORE == Entity.traps[i] && !Player.P2_IS_INVINCIBLE) {

                    if (!Player.isTrapped) {
                        Objects.message = informEvent(2);
                        Player.PLAYER2_SCORE -= 3;
                        Player.PLAYER2_X -= 3 * 48;
                        posX = Player.PLAYER2_X - 12;
                        resetCounter();
                        Player.isTrapped = true;
                    }
                }
            }
        }

        if (animate) {
            if (animationCounter > 1) {
                animationNum += (animationNum < trapped.length) ? 1 : 0;
                if (animationNum == trapped.length) {
                    animationNum = 1;
                    animationCounter = 0;
                    wasTrappedP2 = false;
                    wasTrappedP1 = false;
                    animate = false;
                }
                animationCounter = 0;
            }
            animationCounter++;
        }
    }

    public void skillEffect() {
        if (Player.usingBlinkP1) {
            Player.PLAYER1_X += (Player.PLAYER1_X < (Player.PLAYER1_SCORE + 3) * 48 + 12) ? 5 : 0;
            if (animationNum == 10) {
                Player.usingBlinkP1 = false;
                Player.PLAYER1_SCORE +=3;
                resetCounter();
            }
            delayCount();
        }
        if (Player.usingBlinkP2) {
            Player.PLAYER2_X += (Player.PLAYER2_X < (Player.PLAYER2_SCORE + 3) * 48 + 12) ? 5 : 0;
            if (animationNum == 10) {
                Player.usingBlinkP2 = false;
                Player.PLAYER2_SCORE +=3;
                resetCounter();
            }
            delayCount();
        }
    }

    public void gameFinished(GraphicsContext g) {
        g.setFont(fontStatus);
        g.setFill(Color.BLACK);
        g.fillText("Press [ESC] to return to Main Menu", 6 * 48 - 14, 7 * 48 - 24);
    }

    public String informEvent(int x) {
        String[] info = { "LOOKS LIKE PLAYER " + x + " IS HAVING AN UNLUCKY DAY.", "OUCH. PLAYER " + x + " HIT A TRAP!",
                "LET'S PRAY TO PLAYER " + x + " FOR STEPPING A TRAP.", "IS PLAYER " + x + " STILL OKAY?",
                "PLAYER " + x + " SHOULD GO TO A HOSPITAL SOON.", "DAMN. PLAYER " + x + " HIT A TRAP SO HARD!",
                "CAN WE IMAGINE A DAY PLAYER " + x + " WITH SOME LUCK?" };
        return info[random.nextInt((info.length - 1 - 0) + 1) + 0];
    }

    private void resetCounter() {
        animate = true;
        animationCounter = 0;
        animationNum = 1;
    }

    private void delayCount() {
        if (animationCounter > 2) {
            animationNum++;
            animationCounter = 0;
        }
        animationCounter++;
    }
}