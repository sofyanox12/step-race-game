package com.sisfo;

import com.sisfo.sprites.Player;

public class GameEvent {

    public int moves;

    public Boolean player1, player2;

    public String powerUP;

    public void diceRoll() {

        moves = (int) (Math.random() * 6 + 1); // Menyetel angka dadu secara acak (1-6)
        System.out.println("Langkah : " + moves);
        Player.moves++;

        if (Player.playerID == 1) {
            Player.idlingP1 = false;
        } else if (Player.playerID == 2) {
            Player.idlingP2 = false;
        }

    }

    public void getTrapped() {

    }



}
