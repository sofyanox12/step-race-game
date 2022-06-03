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
    public final int tileSize = 48; // 48x48 Ukuran Tile
    public final int charScale = 1; // Skala besarnya Tile

    public final int spriteSize = tileSize * charScale; // 48x1 = 48x48 Ukuran pixel

    public final int maxPanelCol = 16; // 16 tile melebar kekanan (horizontal)
    public final int maxPanelRow = 12; // 12 tile melebar kebawah (vertikal)

    public final int panelWidth = spriteSize * maxPanelCol; // 48x16 = 768 Ukuran pixel
    public final int panelHeigth = spriteSize * maxPanelRow; // 48x12 = 576 Ukuran pixel

    // FPS
    public final int FPS = 60;
    public Thread gameRunning;

    // PANGGIL KELAS LAIN KE LAYAR
    private final long FIRST_SECOND = System.currentTimeMillis();
    private TileCollection map = new TileCollection(this);
    private GraphicsContext graphic = getGraphicsContext2D();
    private GameEvent gameEvent = new GameEvent();
    private Player player = new Player(this, gameEvent);
    private Objects object = new Objects();
    private Controller ctrl = new Controller();

    private int scrollSpeed = 12;
    private Boolean running = true;

    // PENGATURAN MAP
    public final int maxWorldCol = 51;
    public final int maxWorldRow = 12;
    public final int worldWidth = spriteSize * maxPanelCol;
    public final int worldHeigth = spriteSize * maxPanelRow;

    // PANEL CONSTRUCTOR
    public GPanel() {

        this.setHeight(panelHeigth);
        this.setWidth(panelWidth);
        this.setFocusTraversable(true);

        // KEYBOARD IN-GAME NYA
        this.setOnKeyPressed(key -> { 
            switch (key.getCode()) {
                case LEFT:
                    if (Player.mapShift > 0 && Entity.playerID == 0) {
                        Player.mapShift -= scrollSpeed;
                        Player.PLAYER1_X += scrollSpeed;
                        Player.PLAYER2_X += scrollSpeed;
                        Objects.objectsX += scrollSpeed;
                    }
                    break;

                case RIGHT:
                    if ((Player.mapShift < (maxWorldCol * spriteSize) - panelWidth) && Entity.playerID == 0) {
                        Player.mapShift += scrollSpeed;
                        Player.PLAYER1_X -= scrollSpeed;
                        Player.PLAYER2_X -= scrollSpeed;
                        Objects.objectsX -= scrollSpeed;
                    }
                    break;

                case SPACE:
                    if ((Player.idlingP1 && Player.idlingP2) && !player.computerPlay && !Player.winner) {
                        Entity.playerID = 1;
                        player.diceRoll();
                    }
                    break;
                    
                case ESCAPE:
                    try {
                        running = false;
                        ctrl.toMainMenu();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                    break;

                case Q:
                    player.useQ();
                    break;

                case W:
                    player.useW();
                    break;

                case E:
                    player.useE();
                    break;

                default:
                    break;
            }
        });

        // PENGGUNAAN THREAD UNTUK ENGINE DARI GAME
        Thread thread = new Thread(() -> { 

            double delta = 0;
            double timer = 0;
            double drawCount = 0;
            double drawInterval = 1000000000 / FPS;
            long lastTime = System.nanoTime();
            long currentTime;

            // MENGUPDATE INFORMASI SESUAI THREAD YANG BERJALAN
            Runnable updater = () -> { 
                update();
                render(graphic);
            };

            // MEMBATASI THREAD DENGAN MEKANISME FRAME PER SECOND
            while (running) {

                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if (delta > 0) {
                    drawCount++;
                    delta--;
                    Platform.runLater(updater);
                }

                if (timer >= 1000000000) { // Output ke terminal
                    System.out.printf("\n[FPS:%s] [Time:%ss]", drawCount, getSecond());
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

    public void update() { // UPDATE INFORMASI DI BELAKANG LAYAR
        player.checkWinner();
        player.updateP1();
        player.updateP2();
        player.detectEvent();
    }

    public void render(GraphicsContext render) { // MENGGAMBAR BERBAGAI INFORMASI DI LAYAR
        // MAP
        map.draw(render);

        // OBJECT
        object.renderMapObject(render);
        object.renderDice(render, player);
        object.renderItems(render);
        object.renderGUI(render);
        object.renderPlayerUI(render);

        // PLAYER
        player.drawP1(render);
        player.drawP2(render);

        // CACHE
        render.restore();

    }

}
