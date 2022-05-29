package com.sisfo;

import com.sisfo.sprites.Player;

public class GameEvent {

    public int moves, movesValue;

    public Boolean player1, player2;

    public String powerUP;

    public void diceRoll()  {

        // Menyetel angka dadu secara acak (1-6)
        moves = (int) (Math.random() * 6 + 1);
        System.out.println("Langkah : " + moves);
        Player.moves++;

    }

    public void getTrapped() {

    }



}
