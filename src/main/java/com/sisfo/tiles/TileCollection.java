package com.sisfo.tiles;

import com.sisfo.GPanel;
import com.sisfo.sprites.Player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
    Peran Kelas TileCollection di sini adalah untuk menyediakan
    berbagai informasi tentang bahan-bahan dari path yang disediakan
    untuk dinilai sebagai resources agar dapat dibaca oleh
    program.
*/

public class TileCollection {

    GPanel gp;
    Tile[] tile;
    int[][] mapTileNum;
    int tileAssets = 15; // Banyaknya jenis tile yang akan dipakai = 12

    public TileCollection(GPanel gp) {
        this.gp = gp;
        this.tile = new Tile[tileAssets];

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileView();
        loadMap("map01.txt");
    }

    public void getTileView() {

        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
        }

        /*
         * Berikut adalah tile yang di set menggunakan angka nya
         * untuk bisa ditulis dalam bentuk .txt
         */
        try {
            tile[0].image = new Image(getClass().getResourceAsStream("ground1_0.png")); // 0
            tile[1].image = new Image(getClass().getResourceAsStream("ground1_1.png")); // 1
            tile[2].image = new Image(getClass().getResourceAsStream("sky1_0.png")); // 2
            tile[3].image = new Image(getClass().getResourceAsStream("sky1_1.png")); // 3
            tile[4].image = new Image(getClass().getResourceAsStream("sky1_2.png")); // 4
            tile[5].image = new Image(getClass().getResourceAsStream("sky1_3.png")); // 5
            tile[6].image = new Image(getClass().getResourceAsStream("sky2_0.png")); // 6
            tile[7].image = new Image(getClass().getResourceAsStream("sky2_1.png")); // 7
            tile[8].image = new Image(getClass().getResourceAsStream("sky2_2.png")); // 8
            tile[9].image = new Image(getClass().getResourceAsStream("sky2_3.png")); // 9
            tile[10].image = new Image(getClass().getResourceAsStream("sky2_4.png")); // 10
            tile[11].image = new Image(getClass().getResourceAsStream("sky3_0.png")); // 11
            tile[12].image = new Image(getClass().getResourceAsStream("ground1_2.png")); // 12
            tile[13].image = new Image(getClass().getResourceAsStream("ground1_3.png")); // 13

        } catch (Exception e) {
            System.out.println("Ouch! ::> Ada file tile yang gagal di tinjau!");
            System.exit(0);
        }

    }

    public void loadMap(String map) { // Method untuk meng-load file .txt mapnya
        try {
            InputStream mapStream = getClass().getResourceAsStream(map);
            BufferedReader read = new BufferedReader(new InputStreamReader(mapStream));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = read.readLine();

                while (col < gp.maxWorldCol) {
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            read.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void draw(GraphicsContext t) { // Menentukan Posisi setiap penaruhan tilenya

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            
            int tileNum = mapTileNum[worldCol][worldRow]; // Import satu persatu angka dari map01.txt
           
            // POSISI TILE YANG BERADA DI MAP
            int worldX = worldCol * gp.spriteSize; 
            int worldY = worldRow * gp.spriteSize;
            
            // POSISI TILE YANG DIRENDER
            int screenX = worldX - Player.mapShift; 
            int screenY = worldY;

            t.drawImage(tile[tileNum].image, screenX, screenY, gp.spriteSize, gp.spriteSize);
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
   
                worldRow++;

            }
        }
    }
}
