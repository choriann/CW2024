package com.example.demo.ui;

import com.example.demo.audio.AudioManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsMenu {

    private final Stage primaryStage;
    private final MainMenu mainMenu;

    public SettingsMenu(Stage primaryStage, MainMenu mainMenu) {
        this.primaryStage = primaryStage;
        this.mainMenu = mainMenu;
    }

    public void show() {
        // Music volume slider
        Label musicLabel = new Label("Music Volume");
        musicLabel.getStyleClass().add("label");
        Slider musicVolumeSlider = new Slider(0, 1, AudioManager.getMusicVolume());
        musicVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            AudioManager.setMusicVolume(newVal.doubleValue());
        });

        // Sound effects volume slider
        Label sfxLabel = new Label("Sound Effects Volume");
        sfxLabel.getStyleClass().add("label");
        Slider sfxVolumeSlider = new Slider(0, 1, AudioManager.getSfxVolume());
        sfxVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            AudioManager.setSfxVolume(newVal.doubleValue());
        });

        // Back button
        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> {
            AudioManager.playSoundEffect("/sounds/buttonclick.mp3");
            mainMenu.show();
        });

        // Layout for settings
        VBox settingsLayout = new VBox(20, musicLabel, musicVolumeSlider, sfxLabel, sfxVolumeSlider, backButton);
        settingsLayout.getStyleClass().add("settings-layout");

        // Create the scene and set it to the stage
        Scene settingsScene = new Scene(settingsLayout, primaryStage.getWidth(), primaryStage.getHeight());
        settingsScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(settingsScene);
    }
}
