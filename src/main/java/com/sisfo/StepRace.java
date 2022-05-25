package com.sisfo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StepRace extends Application {
    
    public static void main(String[] game) {
        launch(game);
    }


    public void start(Stage stage) {
        
        Image icon = new Image("file:src/main/resources/com/sisfo/icon.png");

        GPanel window = new GPanel();
        
        Pane root = new Pane(window);

        root.setStyle("-fx-background-color: #000000;");
        stage.setTitle("Step Race");

        Button testButton = new Button("Test");
        root.getChildren().add(testButton);
        //root.setStyle("-fx-background-color: #000000;");

        //window.startGameThread(); // Jalankan Thread


        Scene scene = new Scene(root, window.panelWidth, window.panelHeigth);

        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


}