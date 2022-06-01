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
    Image[] tile;
    int[][] mapTileNum;
    int tileAssets = 15;

    public TileCollection(GPanel gp) {
        this.gp = gp;
        tile = new Image[tileAssets];

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileView();
        loadMap("map01.txt");
    }

    public void getTileView() {

        try {
            tile[0] = new Image(getClass().getResourceAsStream("ground1_0.png")); // 0
            tile[1] = new Image(getClass().getResourceAsStream("ground1_1.png")); // 1
            tile[2] = new Image(getClass().getResourceAsStream("sky1_0.png")); // 2
            tile[3] = new Image(getClass().getResourceAsStream("sky1_1.png")); // 3
            tile[4] = new Image(getClass().getResourceAsStream("sky1_2.png")); // 4
            tile[5] = new Image(getClass().getResourceAsStream("sky1_3.png")); // 5
            tile[6] = new Image(getClass().getResourceAsStream("sky2_0.png")); // 6
            tile[7] = new Image(getClass().getResourceAsStream("sky2_1.png")); // 7
            tile[8] = new Image(getClass().getResourceAsStream("sky2_2.png")); // 8
            tile[9] = new Image(getClass().getResourceAsStream("sky2_3.png")); // 9
            tile[10] = new Image(getClass().getResourceAsStream("sky2_4.png")); // 10
            tile[11] = new Image(getClass().getResourceAsStream("sky3_0.png")); // 11
            tile[12] = new Image(getClass().getResourceAsStream("ground1_2.png")); // 12
            tile[13] = new Image(getClass().getResourceAsStream("ground1_3.png")); // 13

        } catch (Exception e) {
            System.exit(0);
        }

    }

    // MEKANISME MENGLOAD MAP DARI DATA MAP BERWUJUD .TXT
    public void loadMap(String map) {
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

    // MENENTUKAN DAN MERENDER POSISI TILE PADA MAP
    public void draw(GraphicsContext t) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow]; // Import satu persatu angka dari map01.txt

            // POSISI TILE YANG BERADA DI MAP
            int worldX = worldCol * gp.spriteSize;
            int worldY = worldRow * gp.spriteSize;

            // POSISI TILE YANG DIRENDER PADA WINDOW
            int screenX = worldX - Player.mapShift;
            int screenY = worldY;

            t.drawImage(tile[tileNum], screenX, screenY, gp.spriteSize, gp.spriteSize);
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
