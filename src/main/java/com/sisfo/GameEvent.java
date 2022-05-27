package com.sisfo;

public class GameEvent {

    public Boolean step1 = false, step2 = false, step3 = false;
    public Boolean step4 = false, step5 = false, step6 = false;

    public Boolean back1 = false, back2 = false, back3 = false;
    public Boolean back4 = false, back5 = false, back6 = false;

    public String powerUP;

    public void diceRoll() {

        int number = (int) (Math.random() * 6 + 1); // Menyetel angka dadu secara acak (1-6)

        step6 = (number == 6) ? true : false;
        step5 = (number == 5) ? true : false;
        step4 = (number == 4) ? true : false;
        step3 = (number == 3) ? true : false;
        step2 = (number == 2) ? true : false;
        step1 = (number == 1) ? true : false;

    }

    public void getTrapped() {

    }

}
