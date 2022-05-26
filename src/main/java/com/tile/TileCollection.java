package com.tile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.sisfo.GPanel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class TileCollection {

    GPanel gp;
    Tile[] tile;
    int[][] mapTileNum;
    int tileAssets = 10;

    public TileCollection(GPanel gp) {
        this.gp = gp;
        this.tile = new Tile[tileAssets]; // Banyaknya jenis tile yang akan dipakai = 10

        mapTileNum = new int[gp.maxPanelCol][gp.maxPanelRow];
        getTileView();
        loadMap();
    }

    public void getTileView()  {

        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
        }

        tile[0].image = new Image("file:src/main/resources/com/tiles/ground1_0.png");
        tile[1].image = new Image("file:src/main/resources/com/tiles/sky1_0.png");
        tile[2].image = new Image("file:src/main/resources/com/tiles/sky1_1.png");
        tile[3].image = new Image("file:src/main/resources/com/tiles/sky1_2.png");
        tile[4].image = new Image("file:src/main/resources/com/tiles/sky1_3.png");
        tile[5].image = new Image("file:src/main/resources/com/tiles/sky2_0.png");
        tile[6].image = new Image("file:src/main/resources/com/tiles/sky2_1.png");
        tile[7].image = new Image("file:src/main/resources/com/tiles/sky2_2.png");
        tile[8].image = new Image("file:src/main/resources/com/tiles/sky2_3.png");
        tile[8].image = new Image("file:src/main/resources/com/tiles/sky2_4.png");
        tile[8].image = new Image("file:src/main/resources/com/tiles/sky3_0.png");


    }

    public void loadMap() {
        try {
            InputStream mapStream = getClass().getResourceAsStream("map01.txt");
            BufferedReader read = new BufferedReader(new InputStreamReader(mapStream));
            
            int col = 0;
            int row = 0;

            while(col < gp.maxPanelCol && row < gp.maxPanelRow) {
                String line = read.readLine();

                while (col < gp.maxPanelCol) {
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxPanelCol) {
                    col=0;
                    row++;
                }
            }
            read.close();

        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void draw(GraphicsContext t) {
        //t.drawImage(tile[0].image, 0, 0, gp.spriteSize, gp.spriteSize);
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
