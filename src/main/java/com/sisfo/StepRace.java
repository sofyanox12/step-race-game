/* 
    Step Race adalah Mini-Game yang menggunakan prinsip permainan dari Ular Tangga dengan terapan
    Dadunya. Perbedaannya berada pada penggunaan Power UP untuk mendapatkan Skill dan Jebakan untuk
    mengurangi langkah pemain.

    @author Muhammad Sofyan Daud Pujas - H071211045
    @author Chindy Febryan Erwana Putri - H071211038
    @version 0.1-Beta

*/

package com.sisfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/*
    KELAS UTAMA - STEP RACE
*/

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