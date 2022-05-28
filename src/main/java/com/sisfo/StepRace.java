package com.sisfo;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import java.io.IOException;

public class StepRace extends Application {

    public static Stage stage;
    
    public static void main(String[] game) {
        
        launch(game);
    }

    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("menubg1.fxml"));
        
        StepRace.stage = stage;
        StepRace.stage.setTitle("Step Race");
        StepRace.stage.getIcons().add(new Image(getClass().getResource("icon.png").toString()));
        StepRace.stage.setResizable(false);
        StepRace.stage.setScene(new Scene(root));
        StepRace.stage.show();
    }


}