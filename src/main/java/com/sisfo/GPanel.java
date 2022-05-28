package com.sisfo;

import com.sisfo.sprites.Entity;
import com.sisfo.sprites.Player;
import com.sisfo.tiles.Objects;
import com.sisfo.tiles.TileCollection;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/*
    Peran Kelas GPanel disini adalah sebagai Engine Game
    utama dan juga sebagai ekstender dari library Canvas
    sebagai jendela untuk gamenya.
*/

public class GPanel extends Canvas {

    // PENGATURAN LAYAR
    public final int tileSize = 48; // 48x48 Ukuran Tile - sesuaikan
    public final int charScale = 1;

    public final int spriteSize = tileSize * charScale; // 48x1 = 48x48 Ukuran pixel

    public final int maxPanelCol = 16; // 16 tile melebar kekanan (horizontal)
    public final int maxPanelRow = 12; // 12 tile melebar kebawah (vertikal)

    public final int panelWidth = spriteSize * maxPanelCol; // 48x16 = 768 Ukuran pixel
    public final int panelHeigth = spriteSize * maxPanelRow; // 48x12 = 576 Ukuran pixel

    // FPS
    public final int FPS = 60;
    public Thread gameRunning;

    // PANGGIL KELAS LAIN KE LAYAR
    private TileCollection map = new TileCollection(this);

    private GraphicsContext graphic = getGraphicsContext2D();

    private final long FIRST_SECOND = System.currentTimeMillis();

    private GameEvent gameEvent = new GameEvent();

    public Player player = new Player(this, gameEvent);

    private Objects object = new Objects();


    // PENGATURAN MAP
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 12;
    public final int worldWidth = spriteSize * maxPanelCol;
    public final int worldHeigth = spriteSize * maxPanelRow;
    public Boolean leftPressed, rightPressed;

    // Rendering
    public GPanel() {
        this.setHeight(panelHeigth);
        this.setWidth(panelWidth);
        this.setFocusTraversable(true);
        this.setOnKeyPressed(key -> {
            switch (key.getCode()) {
                case LEFT:
                    leftPressed = true;
                    Player.mapShift += 5;
                    break;

                case RIGHT:
                    rightPressed = true;
                    Player.mapShift -= 5;
                    break;

                case SPACE:
                    if ((Player.idlingP1 && Player.idlingP2) && !player.computerPlay) {
                        Entity.playerID = 1;
                        player.diceRoll();
                    }

                default:
                    break;
            }
        });

        Thread thread = new Thread(() -> { // = public void run() - ini sebagai Engine Game :

            double delta = 0;
            double timer = 0;
            double drawCount = 0;
            double drawInterval = 1000000000 / FPS;
            long lastTime = System.nanoTime();
            long currentTime;

            Runnable updater = () -> { // Thread yang mampu memberikan efek ke GUI javaFX :

                update();
                render(graphic);

            };

            while (true) { // Thread yang hanya dijalankan di background :

                // Merender sesuai FPS agar tidak memakan CPU:
                currentTime = System.nanoTime(); //System.currentmillis
                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta > 0) {
                    drawCount++;
                    delta--;
                    Platform.runLater(updater);
                }

                if (timer >= 1000000000) { // Output ke terminal
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
        return (int) ((now - FIRST_SECOND) / 1000);
    }

    public void update() { // Mengupdate informasi yang berjalan

        player.updateP1();
        player.updateP2();

    }

    public void render(GraphicsContext render) { // Pemanggilan apa saja yang akan di gambar disini :

        map.draw(render); // Meng-render map
        
        player.drawP1(render); // Meng-render pemain
        player.drawP2(render);
        
        object.renderDice(render, player);
        object.renderPosition(render);

        render.restore(); // Mereset gambar (agar tidak tumpang tindih)
        

    }




}
