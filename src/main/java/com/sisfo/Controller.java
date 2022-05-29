package com.sisfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Controller extends StepRace {

    /* 
        Ini adalah kelas Controller yang menjadi patokan 
        untuk mendeteksi segala bentuk inputan dari GUI
        buatan javaFX seperti tombolnya dan lain-lain;
    */


    
    @FXML
    private Button aboutButton;

    @FXML
    private Button multiButton;

    @FXML
    private Button playButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button backMulti;

    @FXML
    private Button backAbout;

    @FXML
    void menuAbout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menubg2.fxml"));
        StepRace.stage.setScene(new Scene(root));
        
    }

    @FXML
    void menuMulti() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menubg3.fxml"));
        StepRace.stage.setScene(new Scene(root));
    }

    @FXML
    void menuPlay() { // Mulai Game disini
        GPanel window = new GPanel();
        Pane root = new Pane(window);
        Scene scene = new Scene(root, window.panelWidth, window.panelHeigth);
        StepRace.stage.setScene(scene);

    }

    @FXML
    void menuExit() {
        System.exit(0);
    }

    @FXML
    void toMainMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menubg1.fxml"));
        StepRace.stage.setScene(new Scene(root));
    }

    @FXML
    void gotoGithub(ActionEvent event) throws IOException { 
        getHostServices().showDocument("https://github.com/sofyanox12/step-race-game");
    }

}
