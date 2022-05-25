package com.sisfo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/*
    Peran Kelas GPanel disini adalah sebagai konfigurasi
    utama dan juga sebagai ekstender dari library Canvas
    sebagai jendela untuk gamenya.
*/

public class GPanel extends Canvas implements Runnable {

    /* ## Konfigurasikan ## */
    
    final int tileSize = 16; // 16x16 Ukuran Tile - sesuaikan
    final double charScale = 3;

    final double spriteSize = tileSize * charScale; // 32x2 = 64 Ukuran pixel

    final int maxPanelCol = 16;
    final int maxPanelRow = 12;

    final double panelWidth = spriteSize * maxPanelCol; // 64x16 = 1024 Ukuran pixel
    final double panelHeigth = spriteSize * maxPanelRow; // 64x12 = 768 Ukuran pixel

    private Thread gameRunning;

    public GPanel() {

        this.setHeight(panelHeigth);
        this.setWidth(panelWidth);
        
    }

    public void startGameThread() {
        gameRunning = new Thread(this); // Menyatakan class "GPanel" sebagai Thread
        gameRunning.start();
        
    }

    @Override
    public void run() { // Isi game :
        
        while(gameRunning != null) {

            System.out.println("Game sedang berjalan");

        }
    }

    public void update() {

    }

    public void render(GraphicsContext g) {
        
    }
}
