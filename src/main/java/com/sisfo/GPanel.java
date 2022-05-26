package com.sisfo;

import com.tile.TileCollection;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*
    Peran Kelas GPanel disini adalah sebagai Engine Game
    utama dan juga sebagai ekstender dari library Canvas
    sebagai jendela untuk gamenya.
*/

public class GPanel extends Canvas {

    final Canvas canvas;

    /* ##--- Konfigurasi Ukuran Engine ---## */

    public final int tileSize = 16; // 16x16 Ukuran Tile - sesuaikan
    public final int charScale = 3;

    public final int FPS = 60;

    public final int spriteSize = tileSize * charScale; // 16x3 = 48 Ukuran pixel

    public final int maxPanelCol = 16; // 16 tile melebar kekanan (horizontal)
    public final int maxPanelRow = 12; // 12 tile melebar kebawah (vertikal)

    public final int panelWidth = spriteSize * maxPanelCol; // 48x16 = 768 Ukuran pixel
    public final int panelHeigth = spriteSize * maxPanelRow; // 48x12 = 576 Ukuran pixel

    /* ##-------------------------------## */
    
    // Posisi mula-mula player
    int playerPosX = 100;
    int playerPosY = 100;
    int playerSpeed = 4;

    private TileCollection map = new TileCollection(this);

    private GraphicsContext graphic = getGraphicsContext2D();
    
    private final long FIRST_SECOND = System.currentTimeMillis();
   
    public Thread gameRunning;
 
    public GPanel() {

        this.canvas = new Canvas();
        this.setHeight(panelHeigth);
        this.setWidth(panelWidth);
        this.setFocusTraversable(true);

        Thread thread = new Thread(() -> { // = public void run() - ini sebagai Engine Game :

            double delta = 0;
            double timer = 0;
            double drawCount = 0;
            double drawInterval = 1000000000 / FPS;
            long lastTime = System.nanoTime();
            long currentTime;

            Runnable updater = () -> { // Isi Method untuk mengupdate window disini :

                update();

                render(graphic);
                
            };
            
            while (true) { // Isi Method Background Disini :
                
                // Merender sesuai FPS agar tidak memakan CPU:
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta > 0) {
                    drawCount++;
                    delta--;
                    Platform.runLater(updater);
                }

                if (timer >= 1000000000) {
                    System.out.printf("\n[FPS:%s] waktu = %s d", drawCount, getSecond());
                    drawCount = 0;
                    timer = 0;
                }
                
            }
        });
        
        thread.setDaemon(true);
        thread.start();
    }

    public int getSecond() {
        long now = System.currentTimeMillis();
        return (int)((now - FIRST_SECOND) / 1000);
    }

    public void update() {

    }

    public void render(GraphicsContext g) {

        g.setFill(Color.WHITE);
        g.fillRect(100, 100, spriteSize, spriteSize);
       // map.draw(g); // Lagi Dalam Perbaikan

        //player.draw(g)

    }

    

}
