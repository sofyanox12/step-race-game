package com.sisfo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class StepRace extends Application {

    private Pane root;

    

    public static void main(String[] game) {
        launch(game);
    }

    public void start(Stage stage) {

        stage.setTitle("Step Race");

        GPanel window = new GPanel();

        //GraphicsContext graphicsContext = window.getGraphicsContext2D();

        root = new Pane(window);

        Scene scene = new Scene(root, window.panelWidth, window.panelHeigth);

        stage.setScene(scene);
        stage.show();
    }



}