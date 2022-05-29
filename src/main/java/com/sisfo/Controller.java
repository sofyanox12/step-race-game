package com.sisfo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

import com.sisfo.sprites.Player;

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
    private Button backP1;

    @FXML
    private Button btn_armian;

    @FXML
    private Button btn_kiddo;

    @FXML
    private Button btn_oldie;

    @FXML
    private Button btn_punk;

    @FXML
    private Button backP2;

    @FXML
    private Button btn_armian2;

    @FXML
    private Button btn_kiddo2;

    @FXML
    private Button btn_oldie2;

    @FXML
    private Button btn_punk2;

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
    void menuPlay() throws IOException { // Mulai Game disini
        toSelection();

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
    void gotoGithub() throws IOException { 
        getHostServices().showDocument("https://github.com/sofyanox12/step-race-game");
    }

    @FXML
    void selectP1Armian() {
        Player.player1 = 3;
        gotoP2();
    }

    @FXML
    void selectP1Kiddo() {
        Player.player1 = 2;
        gotoP2();
    }

    @FXML
    void selectP1Oldie() {
        Player.player1 = 1;
        gotoP2();
    }

    @FXML
    void selectP1Punk() {
        Player.player1 = 4;
        gotoP2();
    }
    

    @FXML
    void selectP2Armian() {
        Player.player2 = 3;
        startGame();
    }

    @FXML
    void selectP2Kiddo() {
        Player.player2 = 2;
        startGame();
    }

    @FXML
    void selectP2Oldie() {
        Player.player2 = 1;
        startGame();
    }

    @FXML
    void selectP2Punk() {
        Player.player2 = 4;
        startGame();
    }

    @FXML
    void toSelection() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menuselect1.fxml"));
        StepRace.stage.setScene(new Scene(root));
    }

    void startGame() {
        GPanel window = new GPanel();
        Pane root = new Pane(window);
        Scene scene = new Scene(root, window.panelWidth, window.panelHeigth);
        StepRace.stage.setScene(scene);
    }

    void gotoP2()  {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("menuselect2.fxml"));
            StepRace.stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println("Ouch!");
        }
        
    }
    

}
