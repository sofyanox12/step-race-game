package com.sisfo;

public class GameEvent {

    public Boolean step1 = false, step2 = false, step3 = false;
    public Boolean step4 = false, step5 = false, step6 = false;

    public Boolean back1 = false, back2 = false, back3 = false;
    public Boolean back4 = false, back5 = false, back6 = false;

    public int moves;

    public Boolean player1, player2;

    public String powerUP;

    public void diceRoll() {

        moves = (int) (Math.random() * 6 + 1); // Menyetel angka dadu secara acak (1-6)

        step6 = (moves == 6) ? true : false;
        step5 = (moves == 5) ? true : false;
        step4 = (moves == 4) ? true : false;
        step3 = (moves == 3) ? true : false;
        step2 = (moves == 2) ? true : false;
        step1 = (moves == 1) ? true : false;
        
        System.out.println("Langkah : " + moves);

    }

    public void getTrapped() {

    }

}
