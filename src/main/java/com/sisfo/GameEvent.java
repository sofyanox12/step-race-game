package com.sisfo;

import java.util.Random;
import java.util.Scanner;

import com.sisfo.sprites.Entity;
import com.sisfo.sprites.Player;
import com.sisfo.tiles.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/*
    GameEvent adalah Kelas Kecil yang berguna
    untuk memberikan layanan untuk mendapatkan
    angka dadu secara acak
*/

public class GameEvent {

    public int moves = 0;

    private Boolean wasTrappedP1 = false;
    private Boolean wasTrappedP2 = false;
    private Image[] trapped = new Image[28];

    private int animationCounter = 0;
    private int animationNum = 1;
    private int posX;
    private Boolean animate = false;
    
    public GameEvent() {

        for (int i = 0; i < trapped.length; i++) {
            trapped[i] = new Image(getClass().getResourceAsStream("effects/1_" + i + ".png"));
        }
    }

    public void diceRoll() {
        Random random = new Random();
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
            g.drawImage(onTrap, posX, Player.PLAYER1_Y, 48, 48);
        }
    }

    

    public void detectTrap() {
        if (moves != 6) {
            for (int i = 0; i < Entity.traps.length; i++) {
                if (Player.PLAYER1_SCORE == Entity.traps[i] && !Player.P1_IS_INVINCIBLE) {
                    Player.PLAYER1_SCORE -= 3;
                    wasTrappedP1 = true;
                    animate = true;
                    animationCounter = 0;
                    animationNum = 1;
                }

                if (Player.PLAYER2_SCORE == Entity.traps[i] && !Player.P2_IS_INVINCIBLE) {
                    Player.PLAYER2_SCORE -= 3;
                    wasTrappedP2 = true;
                    animate = true;
                    animationCounter = 0;
                    animationNum = 1;
                }
            }

            if (wasTrappedP1 || wasTrappedP2) {
                if (wasTrappedP1) {
                    
                    posX = Player.PLAYER1_X - 12;
                    

                } else if (wasTrappedP2) {
                    
                    posX = Player.PLAYER2_X - 12;
                    

                }

                if (animationCounter > 2) {
                    animationNum += (animationNum < trapped.length) ? 1 : 0;
                    if (animationNum == trapped.length) {
                        animationNum = 1;
                        wasTrappedP2 = false;
                        wasTrappedP1 = false;
                        animate = false;
                    }
                    animationCounter = 0;
                }
                animationCounter++;

            } 
        }

    }
}
