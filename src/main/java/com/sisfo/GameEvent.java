package com.sisfo;

import com.entity.Player;

public class GameEvent {

    public Boolean step1 = false, step2 = false, step3 = false; 
    public Boolean step4 = false, step5 = false, step6 = false;

    public Boolean back1 = false, back2 = false, back3 = false; 
    public Boolean back4 = false, back5 = false, back6 = false;

    public String powerUP;

    public void diceRoll() {

        int number = (int)(Math.random()*6+1); // Menyetel angka dadu (random)

        if (number == 6) {
            step6 = true;
        }
        if (number == 5) {
            step5 = true;
        }
        if (number == 4) { 
            step4 = true;
        }
        if (number == 3) {
            step3 = true;
        }
        if (number == 2) {
            step2 = true;
        }
        if (number == 1) {
            step1 = true;
        }
    }

    public void getTrapped() {

        

    }

}
