package com.example.demo.ui;

import com.example.demo.controller.Controller;
import com.example.demo.audio.AudioManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

    private final Controller controller;
    private final Stage primaryStage;

    public MainMenu(Stage primaryStage, Controller controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;
    }

    public void show() {
        // Create buttons
        Button playButton = new Button("Play Game");
        Button settingsButton = new Button("Settings");
        Button quitButton = new Button("Quit");

        // Add CSS styles to buttons
        playButton.getStyleClass().add("button");
        settingsButton.getStyleClass().add("button");
        quitButton.getStyleClass().add("button");

        // Add button click sound
        playButton.setOnAction(e -> {
            AudioManager.playSoundEffect("/sounds/buttonclick.mp3");
            startGame();
        });
        settingsButton.setOnAction(e -> {
            AudioManager.playSoundEffect("/sounds/buttonclick.mp3");
            openSettings();
        });
        quitButton.setOnAction(e -> {
            AudioManager.playSoundEffect("/sounds/buttonclick.mp3");
            primaryStage.close();
        });

        // Layout for the buttons
        VBox menuLayout = new VBox(20, playButton, settingsButton, quitButton);
        menuLayout.getStyleClass().add("settings-layout");

        // Create the scene and set it to the stage
        Scene menuScene = new Scene(menuLayout, primaryStage.getWidth(), primaryStage.getHeight());
        menuScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(menuScene);

        // Play background music
        AudioManager.playBackgroundMusic("/sounds/bgm.mp3");
    }

    private void startGame() {
        try {
            AudioManager.stopBackgroundMusic(); // Stop BGM before starting the game
            controller.launchGame(); // Use the existing launchGame method
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openSettings() {
        SettingsMenu settingsMenu = new SettingsMenu(primaryStage, this);
        settingsMenu.show();
    }
}
