package com.sisfo;

import com.sisfo.sprites.Player;

/*
    GameEvent adalah Kelas Kecil yang berguna
    untuk memberikan layanan untuk mendapatkan
    angka dadu secara acak
*/

public class GameEvent {

    public int moves;

    public void diceRoll()  {
        moves = (int) (Math.random() * 6 + 1);
        Player.moves++;

    }

    
}
