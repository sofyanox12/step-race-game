package com.sisfo;

public class GameEvent {

    public Boolean back1 = false, back2 = false, back3 = false;
    public Boolean back4 = false, back5 = false, back6 = false;

    public int moves;

    public Boolean player1, player2;

    public String powerUP;

    public void diceRoll() {

        moves = (int) (Math.random() * 6 + 1); // Menyetel angka dadu secara acak (1-6)
        moves = 1;
        System.out.println("Langkah : " + moves);


    }

    public void getTrapped() {
        moves += (back1) ? -1 : moves;
        moves += (back2) ? -2 : moves;
        moves += (back3) ? -3 : moves;
        moves += (back4) ? -4 : moves;
        moves += (back5) ? -5 : moves;
        moves += (back6) ? -6 : moves;
    }

}
