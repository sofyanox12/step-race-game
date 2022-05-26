package com.tile;

import com.sisfo.GPanel;

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

        mapTileNum = new int[gp.maxPanelCol][gp.maxPanelRow];
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

        tile[0].image = new Image("file:src/main/resources/com/tiles/ground1_0.png"); // 0 
        tile[1].image = new Image("file:src/main/resources/com/tiles/ground1_1.png"); // 1
        tile[2].image = new Image("file:src/main/resources/com/tiles/sky1_0.png"); // 2
        tile[3].image = new Image("file:src/main/resources/com/tiles/sky1_1.png"); // 3
        tile[4].image = new Image("file:src/main/resources/com/tiles/sky1_2.png"); // 4
        tile[5].image = new Image("file:src/main/resources/com/tiles/sky1_3.png"); // 5
        tile[6].image = new Image("file:src/main/resources/com/tiles/sky2_0.png"); // 6
        tile[7].image = new Image("file:src/main/resources/com/tiles/sky2_1.png"); // 7
        tile[8].image = new Image("file:src/main/resources/com/tiles/sky2_2.png"); // 8
        tile[9].image = new Image("file:src/main/resources/com/tiles/sky2_3.png"); // 9
        tile[10].image = new Image("file:src/main/resources/com/tiles/sky2_4.png"); // 10
        tile[11].image = new Image("file:src/main/resources/com/tiles/sky3_0.png"); // 11
        tile[12].image = new Image("file:src/main/resources/com/tiles/ground1_2.png"); // 12
        tile[13].image = new Image("file:src/main/resources/com/tiles/ground1_3.png"); // 13

    }

    public void loadMap(String map) { // Method untuk meng-load file .txt mapnya
        try {
            InputStream mapStream = getClass().getResourceAsStream(map);
            BufferedReader read = new BufferedReader(new InputStreamReader(mapStream));

            int col = 0;
            int row = 0;

            while (col < gp.maxPanelCol && row < gp.maxPanelRow) {
                String line = read.readLine();

                while (col < gp.maxPanelCol) {
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxPanelCol) {
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

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxPanelCol && row < gp.maxPanelRow) {

            int tileNum = mapTileNum[col][row]; // Import satu persatu angka dari map01.txt

            t.drawImage(tile[tileNum].image, x, y, gp.spriteSize, gp.spriteSize);
            col++;
            x += gp.spriteSize;

            if (col == gp.maxPanelCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
