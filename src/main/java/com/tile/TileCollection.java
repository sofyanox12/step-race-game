package com.tile;

import java.nio.Buffer;

import com.sisfo.GPanel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;


public class TileCollection {

    GPanel gp;
    Tile[] tile;

    public TileCollection(GPanel gp) {
        this.gp = gp;
        this.tile = new Tile[10];
        getTileView();
    }

    public void getTileView()  {
        tile[0] = new Tile();
        Image test = new Image("file:src/main/resources/com/tiles/ground.png");
        //tile[0].image = new WritableImage(test.getPixelReader(), 0, 0);

    }

    public void draw(GraphicsContext t) {
        //t.drawImage(tile[0].image, 0, 0, gp.spriteSize, gp.spriteSize);

    }
}
